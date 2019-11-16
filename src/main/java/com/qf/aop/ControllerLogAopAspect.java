package com.qf.aop;

/**
 * @author 张正
 * @version 1.0
 * @date 2019/11/14 21:01
 */

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;


import com.qf.dao.LogInfoMapper;
import com.qf.domain.LogInfo;
import com.qf.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 〈一句话功能简述:操作日志切面记录操作〉<br>
 * 〈功能详细描述〉
 *
 * @author xiang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
@Aspect
@Slf4j
public class ControllerLogAopAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogAopAspect.class);

    //注入,用来将日志信息保存在数据库
    @Autowired
    private LogInfoMapper logInfoMapper;

    //配置接入点
    // 指定controller的类进行切面　　@Pointcut("execution(* com.controller..CustomerController.*(..))||execution(* com.controller.ManageController.*(..))")
    @Pointcut("execution(* com.qf.controller..*.*(..))")
    private void controllerAspect() {
        System.out.println("point cut start");
    }//定义一个切入点


    @SuppressWarnings({"rawtypes", "unused"})
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) {
        //常见日志实体对象
        LogInfo log = new LogInfo();
        //获取登录用户账户
        //String staffNumber = (String) SecurityUtils.getSubject().getPrincipal();

        //获取系统时间
        log.setExecuteTime(new Date());

        //获取系统ip,这里用的是我自己的工具类,可自行网上查询获取ip方法
        //String ip = GetLocalIp.localIp();
        //log.setIP(ip);

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        //String params = Arrays.toString(pjp.getArgs());
        JSONArray operateParamArray = new JSONArray();
        for (int i = 0; i < args.length; i++) {
            Object paramsObj = args[i];

            if (paramsObj instanceof JSONObject) {
                //如果参数为string或者jsonObject
                String str = (String) paramsObj;
                //将其转为jsonObject
                JSONObject dataJson = JSONObject.parseObject(str);

                if (dataJson == null || dataJson.isEmpty() || "null".equals(dataJson)) {
                    break;
                } else {
                    operateParamArray.add(dataJson);
                }
            } else if (paramsObj instanceof Map) {
                //get请求，以map类型传参
                //1.将object的map类型转为jsonObject类型
                Map<String, Object> map = (Map<String, Object>) paramsObj;
                JSONObject json = new JSONObject(map);
                operateParamArray.add(json);
            }else if (paramsObj instanceof String||paramsObj instanceof Integer) {
                //正常的get请求
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("args",paramsObj.toString());
                operateParamArray.add(jsonObject);

            } else {
                //如果参数为已封装好的对象，直接转为jsonString
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(paramsObj);
                operateParamArray.add(jsonObject);
            }
        }
        log.setParams(operateParamArray.toJSONString());


        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;

        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            LOGGER.error("ControllerLogAopAspect around error", e1);
        } catch (SecurityException e1) {
            LOGGER.error("ControllerLogAopAspect around error", e1);
        }
        if (null != method) {
            // 判断是否包含自定义的注解
            if (method.isAnnotationPresent(SystemControllerLog.class)) {
                // 获取登录用户
                String staffNumber = (String) SecurityUtils.getSubject().getPrincipal();
                log.setStaffNumber(staffNumber);

                SystemControllerLog systemlog = method.getAnnotation(SystemControllerLog.class);
                //设置注解标注的方法类型
                log.setMethod(systemlog.methods());


                //非必要，获取操作前参数
                //请求查询操作前数据的spring bean
                String serviceClass = systemlog.serviceClass();
                //请求查询数据的方法
                String queryMethod = systemlog.queryMethod();
                //判断是否需要获取操作前参数
                if (StringUtils.isNotBlank(systemlog.parameterKey())
                        && StringUtils.isNotBlank(systemlog.parameterType())
                        && StringUtils.isNotBlank(systemlog.queryMethod())
                        && StringUtils.isNotBlank(systemlog.serviceClass())) {
                    boolean isArrayResult = systemlog.paramIsArray();
                    //参数类型
                    String paramType = systemlog.parameterType();
                    String key = systemlog.parameterKey();

                    if (isArrayResult) {//批量操作
                        //JSONArray jsonarray = (JSONArray) object.get(key);
                        //从请求的参数中解析出查询key对应的value值
                        String value = "";
                        JSONArray beforeParamArray = new JSONArray();
                        for (int i = 0; i < operateParamArray.size(); i++) {
                            JSONObject params = operateParamArray.getJSONObject(i);
                            JSONArray paramArray = (JSONArray) params.get(key);
                            if (paramArray != null) {
                                for (int j = 0; j < paramArray.size(); j++) {
                                    String paramId = paramArray.getString(j);
                                    //在此处判断spring bean查询的方法参数类型
                                    Object data = getOperateBeforeData(paramType, serviceClass, queryMethod, paramId);
                                    JSONObject json = (JSONObject) JSON.toJSON(data);
                                    beforeParamArray.add(json);
                                }
                            }
                        }
                        log.setParams(beforeParamArray.toJSONString());

                    } else {//单量操作

                        //从请求的参数中解析出查询key对应的value值
                        String value = "";
                        for (int i = 0; i < operateParamArray.size(); i++) {
                            JSONObject params = operateParamArray.getJSONObject(i);
                            value = params.getString(key);
                            if (StringUtils.isNotBlank(value)) {
                                break;
                            }
                        }
                        //在此处获取操作前的spring bean的查询方法
                        Object data = getOperateBeforeData(paramType, serviceClass, queryMethod, value);
                        JSONObject beforeParam = (JSONObject) JSON.toJSON(data);
                        log.setParams(beforeParam.toJSONString());
                    }
                }


                try {
                    //执行页面请求模块方法，并返回
                    object = pjp.proceed();
                    String result = (String) object;
                    log.setResultstatus("success");
                    log.setResultmsg(result);
                    //保存进数据库
                    logInfoMapper.insertSelective(log);
                } catch (Throwable e) {
                    log.setResultstatus("fail");
                    log.setResultmsg(e.getMessage());
                    e.printStackTrace();
                    logInfoMapper.insertSelective(log);
                }
            } else {
                //没有包含注解
                try {
                    object = pjp.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        } else {
            //不需要拦截直接执行
            try {
                object = pjp.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return object;
    }

    /**
     * 功能描述: <br>
     * 〈〉
     *
     * @param paramType:参数类型
     * @param serviceClass：bean名称
     * @param queryMethod：查询method
     * @param value：查询id的value
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */

    public Object getOperateBeforeData(String paramType, String serviceClass, String queryMethod, String value) {
        Object obj = new Object();
        //在此处解析请求的参数类型，根据id查询数据，id类型有四种：int，Integer,long,Long
        if (paramType.equals("int")) {
            int id = Integer.parseInt(value);
            Method mh = ReflectionUtils.findMethod(ApplicationContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, ApplicationContextUtil.getBean(serviceClass), id);

        } else if (paramType.equals("Integer")) {
            Integer id = Integer.valueOf(value);
            Method mh = ReflectionUtils.findMethod(ApplicationContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, ApplicationContextUtil.getBean(serviceClass), id);

        } else if (paramType.equals("long")) {
            long id = Long.parseLong(value);
            Method mh = ReflectionUtils.findMethod(ApplicationContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, ApplicationContextUtil.getBean(serviceClass), id);

        } else if (paramType.equals("Long")) {
            Long id = Long.valueOf(value);
            Method mh = ReflectionUtils.findMethod(ApplicationContextUtil.getBean(serviceClass).getClass(), queryMethod, Long.class);
            //用spring bean获取操作前的参数,此处需要注意：传入的id类型与bean里面的参数类型需要保持一致
            obj = ReflectionUtils.invokeMethod(mh, ApplicationContextUtil.getBean(serviceClass), id);
        }
        return obj;
    }
}