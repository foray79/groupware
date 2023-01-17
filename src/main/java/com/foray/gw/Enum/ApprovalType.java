package com.foray.gw.Enum;

public enum ApprovalType {
    APPROVAL("APPROVAL", "결재"),
    MAINTAIN("MAINTAIN", "전결"),
    COOPERATION("COOPERATION", "협조"),
    REFERENCE("REFERENCE","참조");

    private String code;
    private String value;

    ApprovalType(String code,String value){
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
    APPROVAL , //결제
    MAINTAIN , //전결
    COOPERATION, //협조
    REFERENCEType //참조
*/
}
