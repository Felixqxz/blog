package com.felix.shiro;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.felix.entity.User;
import com.felix.service.UserService;
import com.felix.utils.JwtUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        log.info("jwt------------{}", jwtToken);
        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));

        if (user == null) {
            throw new UnknownAccountException("User account does not exist");
        }

        if (user.getStatus() == -1) {
            throw new LockedAccountException("User account has been locked");
        }

        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user, accountProfile);

        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());
    }
}
