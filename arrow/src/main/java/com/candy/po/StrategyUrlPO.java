package com.candy.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StrategyUrlPO {

    private String url;

    private String requestType;

    private List<Parameters> parameters;


}
