package com.felix.service.impl;

import com.felix.entity.Blog;
import com.felix.mapper.BlogMapper;
import com.felix.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author felix
 * @since 2021-07-20
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
