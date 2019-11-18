package com.cczu.sys.system.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cczu.sys.system.utils.MethodTimeAdvice;

/**
 * 记录方法的执行时间
 * @author jason
 * @date 2017年5月31日
 */
public class MethodTimeAdvice implements MethodInterceptor {
	
    protected Logger log = LoggerFactory.getLogger(MethodTimeAdvice.class);
 
    /**
     * 拦截要执行的目标方法
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //用 commons-lang3 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        Object result = invocation.proceed();
        clock.stop();  //计时结束
 
        //方法参数类型，转换成简单类型
        @SuppressWarnings("rawtypes")
		Class[] params = invocation.getMethod().getParameterTypes();
        String[] simpleParams = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            simpleParams[i] = params[i].getSimpleName();
        }
 
        log.debug("Takes:" + clock.getTime() + " ms ["
                + invocation.getThis().getClass().getName() + "."
                + invocation.getMethod().getName() + "("+StringUtils.join(simpleParams,",")+")] ");
        return result;
    }
}
