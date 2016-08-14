package com.wsy.webseed.realms;

import com.wsy.webseed.domain.SysAuthVo;
import com.wsy.webseed.domain.SysRoleVo;
import com.wsy.webseed.domain.SysUserVo;
import com.wsy.webseed.domain.component.AngularShiroPojo;
import com.wsy.webseed.service.RBACService;
import com.wsy.webseed.util.SysConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class WebRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebRealm.class);

    @Autowired
    private RBACService rbacService;


    public static final String USER_ROLE_IDS = "USER_ROLE_IDS";
    public static final String ANGULAR_SHIRO = "angularShiro";

    /**
     * 为当前登录的Subject授予角色和权限
     */
    @SuppressWarnings("unchecked")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {

        final String currentUsername = (String) super.getAvailablePrincipal(principals);
        final SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        if ((null != currentUsername)) {
            List<SysAuthVo> list = rbacService.queryByLoginName(currentUsername);
            Set<String> permitions = new HashSet<String>();
            SysUserVo user = (SysUserVo) getSession(SysConstant.SESSION_USER_KEY);
            Map<String, String> principal = new HashMap<String, String>();
            Map<String, String> credentials = new HashMap<String, String>();
            principal.put("login", user.getLoginName());
            credentials.put("name", user.getName());
            credentials.put("email", user.getEmail());
            for (SysAuthVo vo : list) {
                permitions.add(vo.getCode());
            }

            AngularShiroPojo shiroPojo = new AngularShiroPojo();
            shiroPojo.setPrincipal(principal);
            shiroPojo.setCredentials(credentials);
            shiroPojo.setPermitions(permitions);
            shiroPojo.setRoles((java.util.Set<String>) this.getSession(USER_ROLE_IDS));

            setSession(ANGULAR_SHIRO, shiroPojo.toString());

            for (String str : permitions) {
                simpleAuthorInfo.addStringPermission(str);
            }
            if (this.getSession(USER_ROLE_IDS) != null) {
                simpleAuthorInfo.setRoles((java.util.Set<String>) this.getSession(USER_ROLE_IDS));
            }

            return simpleAuthorInfo;
        }
        return null;
    }

    /**
     * 验证当前登录的Subject
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken authcToken) throws AuthenticationException {
        final UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        LOGGER.debug("Try to login info {}", token.toString());
        final SysUserVo user = rbacService.querySysUserByLoginName(token.getUsername());
        if (null != user) {
            if (StringUtils.equals(user.getPassword(), new String(token.getPassword()))) {
                final AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
                this.setSession(SysConstant.SESSION_USER_KEY, user);
                this.setSession(SysConstant.SESSION_LOGIN_ID_KEY, user.getLoginName());

                List<SysRoleVo> roleList = rbacService.queryRolesByUserId(user.getId());
                java.util.Set<String> setStr = new HashSet<String>();
                for (SysRoleVo sysRole : roleList) {
                    setStr.add(sysRole.getId() + "");
                }
                this.setSession(USER_ROLE_IDS, setStr);

                return authcInfo;
            }
        }
        return null;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     */
    private void setSession(final Object key, final Object value) {
        final Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            final Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
                session.setTimeout(1800000);
            }
        }
    }

    private Object getSession(final Object key) {
        final Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            final Session session = currentUser.getSession();
            if (null != session) {
                return session.getAttribute(key);
            }
        }

        return null;
    }
}
