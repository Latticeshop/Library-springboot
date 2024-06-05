package com.cbg.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbg.commom.Result;
import com.cbg.entity.LendRecord;
import com.cbg.mapper.LendRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/LendRecord")
public class LendRecordController {
    @Autowired
    LendRecordMapper LendRecordMapper;

    /**
     * 按[isbn:编号]删除
     *
     * @param isbn
     * @return
     */
    @DeleteMapping("/{isbn}")
    public Result<?> delete(@PathVariable String isbn) {
        Map<String, Object> map = new HashMap<>();
        map.put("isbn", isbn);
        LendRecordMapper.deleteByMap(map);
        return Result.success();
    }

    /**
     * 删除一条记录
     *
     * @param LendRecord
     * @return
     */
    @PostMapping("/deleteRecord")
    public Result<?> deleteRecord(@RequestBody LendRecord LendRecord) {
        Map<String, Object> map = new HashMap<>();
        map.put("isbn", LendRecord.getIsbn());
        map.put("borrownum", LendRecord.getBorrownum());
        LendRecordMapper.deleteByMap(map);
        return Result.success();
    }

    /**
     * 批量删除记录
     *
     * @param LendRecords
     * @return
     */
    @PostMapping("/deleteRecords")
    public Result<?> deleteRecords(@RequestBody List<LendRecord> LendRecords) {
        int len = LendRecords.size();
        for (int i = 0; i < len; i++) {
            LendRecord curRecord = LendRecords.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("isbn", curRecord.getIsbn());
            map.put("borrownum", curRecord.getBorrownum());
            LendRecordMapper.deleteByMap(map);
        }
        return Result.success();
    }

    /**
     * 插入记录
     *
     * @param LendRecord
     * @return
     */
    @PostMapping
    public Result<?> save(@RequestBody LendRecord LendRecord) {
        LendRecordMapper.insert(LendRecord);
        return Result.success();
    }

    /**
     * 条件分页查询记录
     *
     * @param pageNum
     * @param pageSize
     * @param search1
     * @param search2
     * @param search3
     * @return
     */
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                              @RequestParam(defaultValue = "") String search2,
                              @RequestParam(defaultValue = "") String search3) {
        LambdaQueryWrapper<LendRecord> wrappers = Wrappers.<LendRecord>lambdaQuery();
        if (StringUtils.isNotBlank(search1)) {
            wrappers.like(LendRecord::getIsbn, search1);
        }
        if (StringUtils.isNotBlank(search2)) {
            wrappers.like(LendRecord::getBookname, search2);
        }
        if (StringUtils.isNotBlank(search3)) {
            wrappers.like(LendRecord::getReaderId, search3);
        }
        Page<LendRecord> LendRecordPage = LendRecordMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(LendRecordPage);
    }

    /**
     * 更新记录
     *
     * @param isbn
     * @param lendRecord
     * @return
     */
    @PutMapping("/{isbn}")
    public Result<?> update(@PathVariable String isbn, @RequestBody LendRecord lendRecord) {
        UpdateWrapper<LendRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("isbn", isbn);
        LendRecord lendrecord = new LendRecord();
        lendrecord.setLendTime(lendRecord.getLendTime());
        lendrecord.setReturnTime(lendRecord.getReturnTime());
        lendrecord.setStatus(lendRecord.getStatus());
        LendRecordMapper.update(lendrecord, updateWrapper);
        return Result.success();
    }

    /**
     * 更新借阅时间
     *
     * @param lendTime
     * @param lendRecord
     * @return
     */
    @PutMapping("/{lendTime}")
    public Result<?> update2(@PathVariable Date lendTime, @RequestBody LendRecord lendRecord) {
        UpdateWrapper<LendRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("lendTime", lendTime);
        LendRecord lendrecord = new LendRecord();
        lendrecord.setReturnTime(lendRecord.getReturnTime());
        lendrecord.setStatus(lendRecord.getStatus());
        LendRecordMapper.update(lendrecord, updateWrapper);
        return Result.success();
    }

}
