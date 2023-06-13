package com.foray.gw.Entity;

import com.foray.gw.Enum.ApprovalType;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="approval")
@Data
@ToString(exclude = "document")
public class ApprovalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_idx")
    private DocumentEntity document ; //문서코드 (ref)

    private int sorty; //순서

    private Long userIdx;//유저idx

    private String name; //이름
    
    private String sign;

    private Date signDate;

    private Date writeDate;
    
    private ApprovalType approvalType; //결재방식
}
