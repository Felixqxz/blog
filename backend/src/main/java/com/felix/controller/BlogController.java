package com.felix.controller;


import ch.qos.logback.core.joran.util.beans.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.felix.common.lang.Result;
import com.felix.entity.Blog;
import com.felix.service.BlogService;
import com.felix.utils.ShiroUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author felix
 * @since 2021-07-20
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage, 5);
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>();
        queryWrapper.eq("status", 0);
        IPage pageData = blogService.page(page, queryWrapper.orderByDesc("created"));
        return Result.success(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "The blog has been deleted");

        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        System.out.println(blog.toString());
        Blog temp;
        if(blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setUsername(ShiroUtil.getProfile().getUsername());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        temp.setTitle(blog.getTitle());
        temp.setDescription(blog.getDescription());
        temp.setContent(blog.getContent());
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }

    @RequiresAuthentication
    @PostMapping("/blog/delete")
    public Result delete(@Validated @RequestBody Blog blog) {
        Blog temp = blogService.getById(blog.getId());
        temp.setStatus(-1);
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }

}
