package com.cbg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbg.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
