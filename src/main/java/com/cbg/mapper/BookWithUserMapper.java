package com.cbg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbg.entity.BookWithUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookWithUserMapper extends BaseMapper<BookWithUser> {

}
