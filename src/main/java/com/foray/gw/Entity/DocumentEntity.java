package com.foray.gw.Entity;

import com.foray.gw.Enum.DocuType;
import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Comment;
@Entity
@Table(name="document")
@Data
//@ToString(exclude = "approval")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Comment("문서발행자 소속코드")
    @Column(length =255,nullable = true)
    private String oringCode; //문서발행자 소속코드 - 중앙 API로 코드 조회

    @Comment("받는사람-협조문")
    @Column(length =255,nullable = true)
    private String receiver;

    @Comment("받는사람ID-협조문")
    @Column(length =255,nullable = true)
    private String receiverId;

    @Comment("등록,기안자 이름")
    @Column(length =255,nullable = true)
    private String writer; //기안자 이름

    @Comment("기안자 ID")
    @Column(length =255,nullable = true)
    private String writerId;

    private String title;

    @Lob
    private String content;

    @Column(length =255,nullable = true) //  COMMENT '문서번호'
    private String docNo; //문서 번호 --랜덤 ? 또는 시퀸스 ? (부서별 컬럼 관리필요)
    
    @Column( length =255,nullable = true)
    private String filename;

    @Comment("최초기안일자")
    //@Column(columnDefinition = "datetime(6)")
    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;

    @Enumerated(EnumType.STRING)
    private DocuType docuType;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="document")
    private List<ApprovalEntity> approval = new ArrayList<>();
}
