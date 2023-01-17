package com.foray.gw.Dto;

import com.foray.gw.Entity.ApprovalEntity;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Entity.UserEntity;
import com.foray.gw.Enum.ApprovalType;
import com.foray.gw.Service.Encrypt;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApprovalDto {

    @Nullable
    private Long idx;
    @NotNull
    private Long userIdx;

    @Nullable
    private String userName;
    @Nullable
    private String sign;

    private Long sorty;

    private ApprovalType ApprovalType;

    private DocumentEntity document ; //문서코드 (ref)


    public static ApprovalEntity Trans(ApprovalDto approvalDto)
    {
        ApprovalEntity approval = new ApprovalEntity();


        approval.setSign(approvalDto.getSign());
        approval.setApprovalType(approvalDto.getApprovalType());
        approval.setName(approvalDto.getUserName());
        approval.setUserIdx(approvalDto.getUserIdx());
        approval.setDocument(approvalDto.getDocument());
        return approval;
    //    return user;
    }

}
