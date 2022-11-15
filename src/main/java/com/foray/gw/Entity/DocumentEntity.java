package com.foray.gw.Entity;

import lombok.Data;
import lombok.ToString;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="document")
@Data
//@ToString(exclude = "approval")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(columnDefinition = "varchar(255)  comment '문서발행자 소속코드'")
    private String oringCode; //문서발행자 소속코드 - 중앙 API로 코드 조회
    
    @Column(columnDefinition = "varchar(255)  comment '받는사람-협조문'")
    private String receiver;

    @Column(columnDefinition = "varchar(255)  comment '받는사람ID-협조문'")
    private String receiverId;

    @Column(columnDefinition = "varchar(255)  comment '등록,기안자 이름'")
    private String writer; //기안자 이름

    @Column(columnDefinition = "varchar(255)  comment '기안자 ID'")
    private String writerId;

    private String title;

    @Lob
    private String content;

    @Column(columnDefinition = "varchar(255) NULL comment '문서번호'")
    private String docNo; //문서 번호 --랜덤 ? 또는 시퀸스 ? (부서별 컬럼 관리필요)

    private String file;

    @Column(columnDefinition = "datetime(6) NULL comment '최초기안일자'")
    private Date writeDate;

    @Enumerated(EnumType.STRING)
    private DocuType docuType;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="document")
    private List<ApprovalEntity> approval = new ArrayList<>();
}
