package com.cbg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbg.entity.Book;
import com.cbg.mapper.BookMapper;
import com.cbg.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
