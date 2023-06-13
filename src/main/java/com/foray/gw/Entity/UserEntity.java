package com.foray.gw.Entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name="user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String userId; //id

    private String userName;

    private String userPwd;

    private String dept;

    private Integer deptCode;

    private Integer postion; //직책

    private Integer deptRank; //직급

}
