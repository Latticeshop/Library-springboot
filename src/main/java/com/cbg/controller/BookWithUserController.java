package com.cbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbg.commom.Result;
import com.cbg.entity.BookWithUser;
import com.cbg.mapper.BookWithUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/bookwithuser")
public class BookWithUserController {
    @Autowired
    BookWithUserMapper BookWithUserMapper;
//
//    @PostMapping
//    public Result<?> save(@RequestBody Book Book){
//        BookMapper.insert(Book);
//        return Result.success();
//    }

    //    //    批量删除
//    @PostMapping("/deleteBatch")
//    public  Result<?> deleteBatch(@RequestBody List<Integer> ids){
//        BookMapper.deleteBatchIds(ids);
//        return Result.success();
//    }
//    @PutMapping
//    public  Result<?> update(@RequestBody Book Book){
//        BookMapper.updateById(Book);
//        return Result.success();
//    }
//    @DeleteMapping("/{id}")
//    public Result<?> delete(@PathVariable Long id){
//        BookMapper.deleteById(id);
//        return Result.success();
//    }

    /**
     * 保存书籍借阅信息
     *
     * @param BookWithUser 书籍借阅信息
     * @return 成功结果
     */
    @PostMapping("/insertNew")
    public Result<?> insertNew(@RequestBody BookWithUser BookWithUser) {
        BookWithUserMapper.insert(BookWithUser);
        return Result.success();
    }

    /**
     * 更新书籍借阅信息
     *
     * @param BookWithUser 书籍借阅信息
     * @return 成功结果
     */
    @PostMapping
    public Result<?> update(@RequestBody BookWithUser BookWithUser) {
        UpdateWrapper<BookWithUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("isbn", BookWithUser.getIsbn()).eq("id", BookWithUser.getId());
        BookWithUserMapper.update(BookWithUser, updateWrapper);
        return Result.success();
    }

    //删除一条记录

    /**
     * 删除书籍借阅信息
     *
     * @param BookWithUser 书籍借阅信息
     * @return 成功结果
     */
    @PostMapping("/deleteRecord")
    public Result<?> deleteRecord(@RequestBody BookWithUser BookWithUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("isbn", BookWithUser.getIsbn());
        map.put("id", BookWithUser.getId());
        BookWithUserMapper.deleteByMap(map);
        return Result.success();
    }

    /**
     * 批量删除书籍借阅信息
     *
     * @param BookWithUsers 书籍借阅信息列表
     * @return 成功结果
     */
    @PostMapping("/deleteRecords")
    public Result<?> deleteRecords(@RequestBody List<BookWithUser> BookWithUsers) {
        int len = BookWithUsers.size();
        for (int i = 0; i < len; i++) {
            BookWithUser curRecord = BookWithUsers.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("isbn", curRecord.getIsbn());
            map.put("id", curRecord.getId());
            BookWithUserMapper.deleteByMap(map);
        }
        return Result.success();
    }

    /**
     * 分页查询书籍借阅信息
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @param search1  ISBN搜索关键字
     * @param search2  书名搜索关键字
     * @param search3  借阅人ID搜索关键字
     * @return 分页书籍借阅信息结果
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                              @RequestParam(defaultValue = "") String search2,
                              @RequestParam(defaultValue = "") String search3) {
        System.out.println("借阅状态...");
        LambdaQueryWrapper<BookWithUser> wrappers = Wrappers.<BookWithUser>lambdaQuery();
        if (StringUtils.isNotBlank(search1)) {
            wrappers.like(BookWithUser::getIsbn, search1);
        }
        if (StringUtils.isNotBlank(search2)) {
            wrappers.like(BookWithUser::getBookName, search2);
        }
        if (StringUtils.isNotBlank(search3)) {
            wrappers.like(BookWithUser::getId, search3);
        }
        Page<BookWithUser> BookPage = BookWithUserMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        System.out.println(BookPage);
        return Result.success(BookPage);
    }
}
