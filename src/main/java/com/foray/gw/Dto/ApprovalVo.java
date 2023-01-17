package com.foray.gw.Dto;

import com.foray.gw.Enum.ApprovalType;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ApprovalVo {
    @Nullable
    private Long idx;
    @Nullable
    private Long[] delIdx;
    @Nullable
    private Long[] apprIdx;

    @Nullable
    private Long[] userIdx;


    @Nullable
    private String[] sign;

    @Nullable
    private String[] ApprovalType;
}
