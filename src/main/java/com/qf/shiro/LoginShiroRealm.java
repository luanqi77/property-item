package com.qf.shiro;

import com.qf.dao.PermissionMapper;

import com.qf.dao.StaffMapper;
import com.qf.domain.Permission;
import com.qf.domain.Staff;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class LoginShiroRealm extends AuthorizingRealm {
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String staffNumber = (String) principalCollection.getPrimaryPrincipal();     //得到用户名
        List<Permission> list = permissionMapper.selectPermissionByStaffNumber(staffNumber);
        Collection set=new HashSet();
        //HashSet底层由hashmap实现，不允许集合中有重
        // 复的值出现， 使用该方式时，需要重写equals（）
        //和hashcode（）方法
        for (Permission permission : list) {        //将权限去重
            set.add(permission.getPerName());
            //System.out.println(permission.getPerName());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(set);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String staffNumber=(String) authenticationToken.getPrincipal();

        Staff staff = staffMapper.findByStaffNumber(staffNumber);
        //System.out.println(staff);

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(staff.getStaffNumber(),staff.getPassword(),ByteSource.Util.bytes(staff.getStaffNumber()),getName());
        return simpleAuthenticationInfo;
    }
}
