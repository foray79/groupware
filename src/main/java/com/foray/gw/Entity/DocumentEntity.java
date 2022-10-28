package com.foray.gw.Entity;

import lombok.Data;
import lombok.ToString;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="document")
@Data
//@ToString(exclude = "approval")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String oringCode; //문서발행자 소속코드 - 중앙 API로 코드 조회

    private String receive;

    private String title;

    @Lob
    private String content;

    private String docNo; //문서 번호 --랜덤 ? 또는 시퀸스 ? (부서별 컬럼 관리필요)

    private String file;

    private DocuType docuType;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="document")
    private List<ApprovalEntity> approval = new ArrayList<>();
}
