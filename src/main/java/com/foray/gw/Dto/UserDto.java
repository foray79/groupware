package com.foray.gw.Dto;

import com.foray.gw.Entity.UserEntity;
import com.foray.gw.Enum.ApprovalType;
import com.foray.gw.Service.Encrypt;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.lang.Nullable;

import java.security.MessageDigest;

@Data
public class UserDto {
    @Nullable
    private Long idx;
    @NotNull
    private String userId; //id
    @NotNull
    private String userName;
    @NotNull
    private String userPwd;
    @Nullable
    private Integer deptCode;
    @Nullable
    private Integer postion; //직책
    @Nullable
    private Integer deptRank; //직급

    private ApprovalType ApprovalType;

    static String[] _dept = new String[4];

    static void getData() {
        UserDto._dept[0]="개발팀";
        UserDto._dept[1]="기획팀";
        UserDto._dept[2]="마케팅팀";
        UserDto._dept[3]="CS팀";
    }

    public static UserEntity Trans(UserDto userDto)
    {
        UserDto.getData();
        String pwd = userDto.getUserPwd();
        String encryptPwd;
        Encrypt encrypt = new Encrypt();
        try {
            encryptPwd = encrypt.sha256(pwd);
        }catch (Exception e){
            e.printStackTrace();
            encryptPwd = pwd;
        }



        UserEntity user = new UserEntity();

        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setUserPwd(encryptPwd);
        user.setDeptCode(userDto.getDeptCode());

        user.setDeptRank(userDto.getDeptRank());
        user.setPostion(userDto.getPostion());

        String deptname = UserDto._dept[userDto.getDeptCode()];
        user.setDept(deptname);

        return user;
    }
    public static String getDeptName(UserDto userDto)
    {
        UserDto.getData();
        String deptname = UserDto._dept[userDto.getDeptCode()];
        return deptname;
    }
}
