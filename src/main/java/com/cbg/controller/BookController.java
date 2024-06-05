package com.cbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbg.commom.Result;
import com.cbg.entity.Book;
import com.cbg.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 书籍控制器
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 保存书籍
     *
     * @param book 书籍信息
     * @return 成功结果
     */
    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        bookMapper.insert(book);
        return Result.success();
    }

    /**
     * 更新书籍
     *
     * @param book 书籍信息
     * @return 成功结果
     */
    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        bookMapper.updateById(book);
        return Result.success();
    }

    /**
     * 批量删除书籍
     *
     * @param ids 书籍ID列表
     * @return 成功结果
     */
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        bookMapper.deleteBatchIds(ids);
        return Result.success();
    }

    /**
     * 删除书籍
     *
     * @param id 书籍ID
     * @return 成功结果
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        bookMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 分页查询书籍
     *
     * @param pageNum  当前页码
     * @param pageSize 每页记录数
     * @param search1  ISBN搜索关键字
     * @param search2  书名搜索关键字
     * @param search3  作者搜索关键字
     * @return 分页书籍结果
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                              @RequestParam(defaultValue = "") String search2,
                              @RequestParam(defaultValue = "") String search3) {
        LambdaQueryWrapper<Book> wrappers = Wrappers.<Book>lambdaQuery();
        if (StringUtils.isNotBlank(search1)) {
            wrappers.like(Book::getIsbn, search1);
        }
        if (StringUtils.isNotBlank(search2)) {
            wrappers.like(Book::getName, search2);
        }
        if (StringUtils.isNotBlank(search3)) {
            wrappers.like(Book::getAuthor, search3);
        }
        Page<Book> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(bookPage);
    }
}
