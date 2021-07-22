package com.felix.service.impl;

import com.felix.entity.User;
import com.felix.mapper.UserMapper;
import com.felix.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
