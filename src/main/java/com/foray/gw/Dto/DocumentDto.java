package com.foray.gw.Dto;

import com.foray.gw.Entity.DocuType;
import com.foray.gw.Entity.DocumentEntity;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Column;

@Data
public class DocumentDto {
    @Nullable
    private Long idx;
    @NotNull
    private String title;
    @NotNull
    private String type;
    @NotNull
    private String writer; //기안자 이름
    @NotNull
    private String writer_id;
    @NotNull
    private String content;
    @Nullable
    private String file;

    public static DocumentEntity Trans(DocumentDto documentDto)
    {
        String originCode = "self";
        DocumentEntity document = new DocumentEntity();

        document.setTitle(documentDto.getTitle());
        document.setDocuType(DocuType.valueOf(documentDto.getType()));
        document.setWriter(documentDto.getWriter());
        document.setWriterId(documentDto.getWriter_id());
        document.setContent(documentDto.getContent());
        document.setOringCode(originCode);
       return document;
    }
}
