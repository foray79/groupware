package com.foray.gw.Service;

import com.foray.gw.Dto.UserDto;
import com.foray.gw.Entity.PageVo;
import com.foray.gw.Entity.UserEntity;
import com.foray.gw.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Test
    void test(){
        List<UserEntity> user =userService.All();
        System.out.println("user = " + user.toString());
    }
    UserEntity getData()
    {
        UserDto userDto = new UserDto();
        userDto.setUserId("foray");
        userDto.setUserName("나병연");
        userDto.setUserPwd("qaz1!");
        userDto.setDeptCode(1);

        UserEntity user = UserDto.Trans(userDto);
        return user;
    }
    @Test
    void set()
    {
        UserEntity user = this.getData();
        System.out.println("user = " + user.toString());
        userRepository.save(user);
    }
    @Test
    void get()
    {
        Long idx = 1l;
        UserEntity user = userRepository.findByIdx(idx);
        System.out.println("user = " + user.toString());
    }
    @Autowired
    UserService service;
    @Test
    void list()
    {
        PageVo pageVo = new PageVo();
        pageVo.setLimit(10);
        pageVo.setStart(1);

        Page<UserEntity> page = service.List(pageVo);

        System.out.println( " list = "+page.getContent().toString());
    }



}