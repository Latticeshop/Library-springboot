package com.cbg.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cbg.commom.Result;
import com.cbg.entity.LendRecord;
import com.cbg.mapper.LendRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/LendRecord1")
public class LendRecordController1 {
    @Autowired
    LendRecordMapper LendRecordMapper;

    @PutMapping
    public Result<?> update2(@RequestBody LendRecord lendRecord) {
        // 创建更新包装器
        UpdateWrapper<LendRecord> updateWrapper = new UpdateWrapper<>();
        // 设置更新条件
        updateWrapper.eq("isbn", lendRecord.getIsbn()).eq("reader_id", lendRecord.getReaderId()).eq("borrownum", lendRecord.getBorrownum());

        // 创建一个新的借阅记录对象
        LendRecord lendrecord = new LendRecord();
        // 设置还书时间
        lendrecord.setReturnTime(lendRecord.getReturnTime());
        // 设置借阅状态
        lendrecord.setStatus(lendRecord.getStatus());

        // 更新借阅记录
        LendRecordMapper.update(lendrecord, updateWrapper);
        return Result.success();
    }


}
