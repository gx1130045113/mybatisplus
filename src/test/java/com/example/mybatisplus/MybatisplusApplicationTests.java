package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void addUser(){
        User user = new User();
        user.setName("wanger");
        user.setAge(23);
        user.setEmail("123@qq.com");
        int insert=userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void updateUser(){
        //乐观锁 先查询再修改
       User user = userMapper.selectById(3l);
      /*  User user = new User();
        user.setId(7L);*/
        user.setAge(26);
        int insert=userMapper.updateById(user);
        System.out.println(insert);
    }

    @Test
    void deleteUser(){

      int delete= userMapper.deleteById(5l);
        System.out.println(delete);
    }

    @Test
    public void selectPage() {
        // 复杂查询
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "Tom");

        // 配置分页
        Page<User> page = new Page<>(1, 3);

        // 查询
        userMapper.selectPage(page, wrapper);
        System.out.println(page);
    }

}
