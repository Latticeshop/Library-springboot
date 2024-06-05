package com.cbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cbg.LoginUser;
import com.cbg.commom.Result;
import com.cbg.entity.Book;
import com.cbg.entity.LendRecord;
import com.cbg.entity.User;
import com.cbg.mapper.BookMapper;
import com.cbg.mapper.LendRecordMapper;
import com.cbg.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LendRecordMapper lendRecordMapper;
    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    public Result<?> dashboardrecords() {
        int visitCount = LoginUser.getVisitCount(); //获取访问次数
        QueryWrapper<User> queryWrapper1 = new QueryWrapper();
        int userCount = userMapper.selectCount(queryWrapper1); //获取用户数量
        QueryWrapper<LendRecord> queryWrapper2 = new QueryWrapper();
        int lendRecordCount = lendRecordMapper.selectCount(queryWrapper2); //获取借阅记录数量
        QueryWrapper<Book> queryWrapper3 = new QueryWrapper();
        int bookCount = bookMapper.selectCount(queryWrapper3); //获取书籍数量

        Map<String, Object> map = new HashMap<>();//创建一个map用于存放数据
        map.put("visitCount", visitCount); //将访问次数放入map
        map.put("userCount", userCount);
        map.put("lendRecordCount", lendRecordCount);
        map.put("bookCount", bookCount);

        return Result.success(map); //返回结果
    }


}
