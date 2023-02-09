package com.yong.redisutil.entity;

import org.apache.poi.ss.formula.functions.T;

@lombok.Data
public class Data<T> {

    private String businessTypeNo;

    private String sysNo;

    private String transmission;

    private String dataFormatNo;

    private String content;

    private T strcontent;

}
