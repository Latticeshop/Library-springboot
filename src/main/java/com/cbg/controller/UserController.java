package com.cbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbg.LoginUser;
import com.cbg.commom.Result;
import com.cbg.entity.User;
import com.cbg.mapper.UserMapper;
import com.cbg.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户名已重复");
        }
        userMapper.insert(user);
        return Result.success();
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @CrossOrigin
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        System.out.println("正在登录...");
        System.out.println(user);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
//        Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        User res = userMapper.selectOne(wrapper);
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        String token = TokenUtils.genToken(res);
        res.setToken(token);
        LoginUser loginuser = new LoginUser();
        LoginUser.addVisitCount();
        return Result.success(res);
    }

    /**
     * 保存注册用户
     *
     * @param user
     * @return
     */
    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456"); // 默认123456密码
        }
        userMapper.insert(user);
        return Result.success();
    }

    /**
     * 更新用户密码
     *
     * @param id
     * @param password2
     * @return
     */
    @PutMapping("/password")
    public Result<?> update(@RequestParam Integer id,
                            @RequestParam String password2) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        User user = new User();
        user.setPassword(password2);
        userMapper.update(user, updateWrapper);
        return Result.success();
    }

//    @PutMapping
//    public Result<?> password(@RequestBody User user) {
//        userMapper.updateById(user);
//        return Result.success();
//    }

    /**
     * 批量按id删除用户
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }

    /**
     * 按id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> wrappers = Wrappers.<User>lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            wrappers.like(User::getNickName, search);
        }
        wrappers.like(User::getRole, 2);
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(userPage);
    }

    /**
     * 按条件分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param search1
     * @param search2
     * @param search3
     * @param search4
     * @return
     */
    @GetMapping("/usersearch")
    public Result<?> findPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search1,
                               @RequestParam(defaultValue = "") String search2,
                               @RequestParam(defaultValue = "") String search3,
                               @RequestParam(defaultValue = "") String search4) {
        LambdaQueryWrapper<User> wrappers = Wrappers.<User>lambdaQuery();
        if (StringUtils.isNotBlank(search1)) {
            wrappers.like(User::getId, search1);
        }
        if (StringUtils.isNotBlank(search2)) {
            wrappers.like(User::getNickName, search2);
        }
        if (StringUtils.isNotBlank(search3)) {
            wrappers.like(User::getPhone, search3);
        }
        if (StringUtils.isNotBlank(search4)) {
            wrappers.like(User::getAddress, search4);
        }
        wrappers.like(User::getRole, 2);
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(userPage);
    }
}
