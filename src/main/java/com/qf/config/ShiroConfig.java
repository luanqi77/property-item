package com.qf.config;


import com.qf.shiro.LoginShiroRealm;
import com.qf.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全登录页面
        shiroFilterFactoryBean.setLoginUrl("/");
        //无权限跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        return shiroFilterFactoryBean;
    }


    //配置安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm")MyRealm myRealm,@Qualifier("loginShiroRealm")LoginShiroRealm loginShiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        ArrayList<Realm> realms = new ArrayList<>();
        realms.add(myRealm);
        realms.add(loginShiroRealm);
        defaultWebSecurityManager.setRealms(realms);
        return defaultWebSecurityManager;
    }

    //对密码进行加密
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean("myRealm")
    public MyRealm myRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm myRealm=new MyRealm();
        myRealm.setAuthorizationCachingEnabled(false);
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }
    @Bean("loginShiroRealm")
    public LoginShiroRealm loginShiroRealm(@Qualifier("hashedCredentialsMatcher")HashedCredentialsMatcher hashedCredentialsMatcher){
        LoginShiroRealm loginShiroRealm=new LoginShiroRealm();
        loginShiroRealm.setAuthorizationCachingEnabled(false);
        loginShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return loginShiroRealm;
    }

    //开启shiro注解
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    //开启aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
