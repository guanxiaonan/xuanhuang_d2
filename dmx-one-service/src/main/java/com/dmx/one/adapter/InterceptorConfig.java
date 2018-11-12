package com.dmx.one.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.dmx.one.Constant.Constant;
import com.dmx.one.pojo.MonitorParam;
import com.dmx.one.pojo.Result;
import com.dmx.one.service.MonitorService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

    @Autowired
    MonitorService monitorService;

    @Bean
    public InterfaceAuthCheckInterceptor getInterfaceAuthCheckInterceptor() {
        return new InterfaceAuthCheckInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //registry.addInterceptor(getInterfaceAuthCheckInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new InterfaceAuthCheckInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login/**");
        // 如果interceptor中不注入redis或其他项目可以直接new，否则请使用上面这种方式
        super.addInterceptors(registry);
    }


    /**
     * 微服务间接口访问密钥验证
     * @author xiaochangwei
     *
     */
    class InterfaceAuthCheckInterceptor implements HandlerInterceptor {

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse arg1, Object arg2, Exception arg3)
                throws Exception {
            String user = (String)request.getSession().getAttribute("user");
            if (user != null) {
                MonitorParam monitor = new MonitorParam();
                monitor.setCallUser(user.split("\\|")[0]);

                HandlerMethod hander = (HandlerMethod)arg2;
                String callMethod = hander.getMethod().getName();
                monitor.setCallMethod(callMethod);

                Object bean = hander.getBean();
                Class<?> aClass = bean.getClass();
                String name = aClass.getName();
                //todo 获取服务名
                if (name.contains("User")) {
                    monitor.setServiceName(Constant.USER_SERVICE);
                    addCallRecord(monitor);
                } else if(name.contains("Inventory")) {
                    monitor.setServiceName(Constant.INVENTORY_SERVICE);
                    addCallRecord(monitor);
                } else if (name.contains("Task")) {
                    monitor.setServiceName(Constant.PRODUCE_TASK_SERVICE);
                    addCallRecord(monitor);
                } else if (name.contains("Material")) {
                    monitor.setServiceName(Constant.MATERIAL_SERVICE);
                    addCallRecord(monitor);
                } else if (name.contains("Monitor")){
                    logger.info("监控控制器");
                } else {
                    logger.info("未指定监控指标！{}",name);
                }
            }
        }

        private void addCallRecord(MonitorParam monitor) {
            monitor.setCallTime(new Date());
            monitorService.addCallRecord(monitor);
            logger.info("after: 添加调用记录");
        }

        @Override
        public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                throws Exception {
            System.out.println("postHandle");

        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)
                throws Exception {
            System.out.println("preHandle");
            HttpSession session = request.getSession();
            String user = (String)session.getAttribute("user");
            if (user == null) {
                response.setContentType("application/json;charset=utf-8");
                Result result = new Result();
                result.setCode(Constant.USER_NOT_LOGIN_CODE);
                result.setMessage(Constant.USER_NOT_LOGIN_MSG);
                response.getWriter().write(JSONObject.toJSONString(result));
                logger.info("无权限！请先登录！！");
                return false;
            } else {
                logger.info("有权限！放行");
                // TODO 验证逻辑
                return true;
            }
        }

    }
}
