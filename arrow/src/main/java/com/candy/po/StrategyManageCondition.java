package com.candy.po;

import lombok.Data;

import java.util.List;

/**
 * @author lenovo
 */
@Data
public class StrategyManageCondition {


    private Object name;

    private String componentType;

    private Object value;

    private List<String> options;

    private List<StrategyManageCondition> conditions;



}
