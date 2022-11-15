package com.foray.gw.Entity;

import lombok.val;

public enum DocuType {

    INTER_OFFICIAL("INTER_OFFICIAL","내부문서"),
    INTER_GENERAL("INTER_GENERAL","내부공문"),
    OUTER_OFFICIAL("OUTER_OFFICIAL","외부공문");

    private String code;
    private String value;

    DocuType(String code,String value){
        this.code = code;
        this.value = value;
    }
    public String getCode()
    {
        return this.code;
    }
    public String getValue()
    {
        return this.value;
    }
    /*
    INTER_OFFICIAL, //내부문서 - 공문,협조문
    INTER_GENERAL, // 내부문서 - 일반,보고
    OUTER_OFFICIAL // 외부문서 - 공문,협조문
    OUTER_OFFICIAL
         */
}