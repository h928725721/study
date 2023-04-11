package com.candu.concurrency.part1.safe;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ComputeWorkingDayUtil {
	//定义两个List，一个存放节假日日期，另一个存放调休的工作日期
    private static List<String> HOLIDAY_LIST = new ArrayList<>();
    private static List<String> SPECIAL_WORKDAY_LIST = new ArrayList<>();

	//静态代码块内调用第三方接口拿到数据存进List中
    static {
        Map<String, Object> param = new HashMap<String, Object>() {{
            put("key", "");//这里key值是注册天行API账号是给的
            put("type", 1);
        }};
        
        //获取当前年份，循环调用3次，拿到3年的数据
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i=0; i<3; i++) {
            param.put("date", currentYear - i);
            String url = "http://api.tianapi.com/jiejiari/index";
            String response = HttpUtil.get(url, param);
            JSONObject resObj = JSONUtil.parseObj(response);
            int code = (int) resObj.get("code");
            if (code == 200) {
                JSONArray newslist = (JSONArray) resObj.get("newslist");
                for (Object listObj : newslist) {
                    JSONObject obj = (JSONObject) listObj;
                    String holidays = (String) obj.get("vacation");
                    String[] holidayArray = holidays.split("\\|");
                    HOLIDAY_LIST.addAll(Arrays.asList(holidayArray));
                    String remark = (String) obj.get("remark");
                    if (StringUtils.isNotEmpty(remark)) {
                        String[] special = remark.split("\\|");
                        SPECIAL_WORKDAY_LIST.addAll(Arrays.asList(special));
                    }
                }
            }
        }
    }
	
	//计算工作日数的方法
    public static int computeWorkingDays(Date start, Date end) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(start);
        endCal.setTime(end);

        int workDays = 0;
    
    	//如果没有严格按照起始结束时间传值，在这里纠正下，可以注释掉
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(end);
            endCal.setTime(start);
        }

        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
        	//控制台打印出来循环情况，方便查看
            System.out.println(DateUtil.format(startCal.getTime(), "yyyy-MM-dd") + "   " + DateUtil.format(endCal.getTime(), "yyyy-MM-dd"));
            String current = DateUtil.format(startCal.getTime(), "yyyy-MM-dd");
            int dayOfWeek = startCal.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) 			
            {
                if (!HOLIDAY_LIST.contains(current)) {
                    System.out.println(true);
                    workDays++;
                }
            }
            if (SPECIAL_WORKDAY_LIST.contains(current)) {
                System.out.println(true);
                workDays++;
            }
            startCal.add(Calendar.DATE, 1);
        }

        return workDays;
    }
}
