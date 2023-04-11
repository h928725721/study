package com.candy.po;


import lombok.Data;

import java.util.List;

/**
 * @author lenovo
 */
@Data
public class StrategyManageBindingRequest {

    private List<StrategyManageCondition> conditions;

}
