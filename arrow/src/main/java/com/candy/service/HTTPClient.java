package com.candy.service;

import com.candy.po.StrategyManageCondition;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HTTPClient {

    private static final String REGEX = "[a-z A-Z]+";
    private static final RestTemplate REST_TEMPLATE;
    public static final String OPENAPI_JSON = "/openapi.json";
    public static final String PATHS = "paths";
    public static final String REQUEST_BODY = "requestBody";
    public static final String PROPERTIES = "properties";
    public static final String INPUT = "input";
    public static final String TYPE = "type";
    public static final String ARRAY = "array";
    public static final String ITEMS = "items";
    public static final String REF = "$ref";
    public static final String ALL_OF = "allOf";
    public static final String DEFAULT = "default";
    public static final String FORMAT = "format";
    public static final String ENUM = "enum";
    public static final String SCHEMA = "schema";


    static {
        REST_TEMPLATE = new RestTemplate();
    }


    public static String testRequestJsonParse(String requestPath, String ip) {
        String openapiString = REST_TEMPLATE.getForObject(ip + OPENAPI_JSON, String.class);
        JsonObject openapiJsonObject = new Gson().fromJson(openapiString, JsonObject.class);
        JsonObject paths = openapiJsonObject.get(PATHS).getAsJsonObject();
        JsonObject path = paths.get(requestPath).getAsJsonObject();
        String requestType = paths.keySet().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("没有找到该接口请求方式!"));

        JsonObject typeJsonObject = path.get(requestType).getAsJsonObject();
        JsonElement requestBody = typeJsonObject.get(REQUEST_BODY);
        String content = getContent(requestBody);

        if (StringUtils.isNotBlank(content)) {
            ArrayList<String> lines = parseComponents(content);
            JsonObject contentBody = lineJsonObject(openapiJsonObject, lines, new JsonObject());
            JsonObject properties = contentBody.get(PROPERTIES).getAsJsonObject();
            Set<String> paramsKey = properties.keySet();

            List<StrategyManageCondition> manageConditions = paramsKey.stream()
                    .map(p -> {
                        StrategyManageCondition condition = new StrategyManageCondition();
                        condition.setName(p);
                        condition.setComponentType(INPUT);
                        JsonObject param = properties.get(p).getAsJsonObject();
                        Set<String> paramKey = param.keySet();

                        String formatDate = containsFormat(param, paramKey);
                        condition.setComponentType(formatDate);

                        String defaultValue = containsDefault(param, paramKey);
                        condition.setValue(defaultValue);

                        List<List<String>> allOfEnum = containsAllOf(openapiJsonObject, param, paramKey);
                        if (CollectionUtils.isNotEmpty(allOfEnum)) {
                            allOfEnum.forEach(condition::setOptions);
                        }

                        JsonObject refBody = containsRef(openapiJsonObject, param, paramKey);
                        if (refBody.size() > 0) {
                            condition.setOptions(getEnumData(refBody));
                        }


                        List<StrategyManageCondition> conditions = containsTypeArray(openapiJsonObject, param, paramKey);
                        condition.setConditions(conditions);


                        return condition;
                    })
                    .collect(Collectors.toList());
            return new Gson().toJson(manageConditions);
        }
        return new Gson().toString();
    }
    private static JsonObject containsRef(JsonObject jsonObject, JsonObject param, Set<String> paramKey) {
        JsonObject allOfBody = new JsonObject();
        if (paramKey.contains("$ref")) {
            JsonElement refElement = param.get("$ref");
            String c = refElement.getAsString();
            ArrayList<String> allOfLines = parseComponents(c);
            allOfBody = lineJsonObject(jsonObject, allOfLines, new JsonObject());
        }
        return allOfBody;
    }


    private static List<StrategyManageCondition> containsTypeArray(JsonObject openapiJsonObject, JsonObject param, Set<String> paramKey) {
        List<StrategyManageCondition> conditions = Lists.newArrayList();
        if (paramKey.contains(TYPE)) {
            String type = param.get(TYPE).getAsJsonPrimitive().getAsString();
            if (StringUtils.equals(type, ARRAY)) {
                JsonObject itemsObject = param.get(ITEMS).getAsJsonObject();
                String c = itemsObject.get(REF).getAsString();
                ArrayList<String> allOfLines = parseComponents(c);
                JsonObject allOfBody = lineJsonObject(openapiJsonObject, allOfLines, new JsonObject());
                JsonObject arrayProperties = allOfBody.get(PROPERTIES).getAsJsonObject();
                Set<String> arrayKeys = arrayProperties.keySet();
                conditions = arrayKeys.stream()
                        .map(k -> {
                            StrategyManageCondition arrayCondition = new StrategyManageCondition();
                            arrayCondition.setName(k);
                            JsonObject a = arrayProperties.get(k).getAsJsonObject();
                            JsonObject arrRef = containsRef(openapiJsonObject, a, a.keySet());
                            List<String> enumData = getEnumData(arrRef);
                            arrayCondition.setOptions(enumData);
                            return arrayCondition;
                        })
                        .collect(Collectors.toList());
            }
        }
        return conditions;
    }

    private static List<List<String>> containsAllOf(JsonObject openapiJsonObject, JsonObject param, Set<String> paramKey) {
        List<List<String>> allOfEnum = Lists.newArrayList();
        if (paramKey.contains(ALL_OF)) {
            JsonArray allOfArray = param.get(ALL_OF).getAsJsonArray();
            for (JsonElement jsonElement : allOfArray) {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                String c = asJsonObject.get(REF).getAsString();
                ArrayList<String> allOfLines = parseComponents(c);
                JsonObject allOfBody = lineJsonObject(openapiJsonObject, allOfLines, new JsonObject());
                List<String> enumData = getEnumData(allOfBody);
                allOfEnum.add(enumData);
            }
        }
        return allOfEnum;
    }

    private static String containsDefault(JsonObject param, Set<String> paramKey) {
        String defaultValue = "";
        if (paramKey.contains(DEFAULT)) {
            defaultValue = param.get(DEFAULT).getAsString();
        }
        return defaultValue;
    }

    private static String containsFormat(JsonObject param, Set<String> paramKey) {
        String formatDate = "date";
        if (paramKey.contains(FORMAT)) {
            formatDate = param.get(FORMAT).getAsString();
        }
        return formatDate;
    }

    private static List<String> getEnumData(JsonObject allOfBody) {
        JsonElement enumElement = allOfBody.get(ENUM);
        List<String> options = Lists.newArrayList();
        if (enumElement != null) {
            JsonArray enumData = enumElement.getAsJsonArray();
            for (int i = 0; i < enumData.size(); i++) {
                options.add(i, enumData.get(i).getAsString());
            }
        }
        return options;
    }

    private static JsonObject lineJsonObject(JsonObject jsonObject, ArrayList<String> lines, JsonObject contentBody) {
        Iterator<String> iterator = lines.iterator();
        if (iterator.hasNext()) {
            String next = iterator.next();
            contentBody = jsonObject.get(next).getAsJsonObject();
            lines.remove(next);
            return lineJsonObject(contentBody, lines, contentBody);
        }
        return contentBody;
    }

    private static ArrayList<String> parseComponents(String content) {
        ArrayList<String> lines = Lists.newArrayList();
        if (StringUtils.isBlank(content)) return lines;
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String group = matcher.group();
            lines.add(group);
        }
        return lines;
    }

    private static String getContent(JsonElement requestBody) {
        String content = "";
        if (requestBody != null) {
            content = requestBody.getAsJsonObject()
                    .get("content").getAsJsonObject()
                    .get("application/json").getAsJsonObject()
                    .get(SCHEMA).getAsJsonObject()
                    .get(REF).getAsJsonPrimitive().toString();
        }
        return content;
    }

}
