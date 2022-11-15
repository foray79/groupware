package com.foray.gw.Service;

import com.foray.gw.Dto.UserDto;
import com.foray.gw.Entity.DocumentEntity;
import com.foray.gw.Entity.PageVo;
import com.foray.gw.Entity.UserEntity;
import com.foray.gw.Service.Encrypt;
import com.foray.gw.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void add(UserDto userDto)
    {
        UserEntity user = UserDto.Trans(userDto);
        userRepository.save(user);
    }
    public void edit(UserDto userDto)
    {
        UserEntity userEntity = this.get(userDto.getIdx());
        String pwd = userDto.getUserPwd();
        String encryptPwd;
        if(pwd.length()==0) { //패스워드 미수정
            encryptPwd = userEntity.getUserPwd();
        }else { //패스워드 수정
            Encrypt encrypt = new Encrypt();
            try {
                encryptPwd = encrypt.sha256(pwd);
            } catch (Exception e) {
                e.printStackTrace();
                encryptPwd = pwd;
            }
        }
        String deptName = UserDto.getDeptName(userDto);
        userEntity.setUserId(userDto.getUserId());
        userEntity.setUserPwd(encryptPwd);
        userEntity.setUserName(userDto.getUserName());
        userEntity.setDept(deptName);
        userEntity.setDeptCode(userDto.getDeptCode());

    }
    public UserEntity get(Long idx)
    {
        UserEntity user = userRepository.findByIdx(idx);
        return user;
    }
    public Page<UserEntity> List(PageVo pageVo)
    {
        int limit = pageVo.getLimit();
        int start = pageVo.getStart();

        PageRequest pageRequest = PageRequest.of(start,limit, Sort.by(Sort.Direction.ASC,"idx"));

        Page<UserEntity> page = userRepository.findAll(pageRequest);

        return page;
    }
    public List<UserEntity> All()
    {
        List<UserEntity> list = userRepository.findAll();
        return list;
    }
}
