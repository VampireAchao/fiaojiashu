package cn.fiaojiashu.search.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: GlobalExceptionResolver
 * @Date: 2020/3/20 20:55
 * @Description:全局异常处理器
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //打印控制台
        ex.printStackTrace();
        //写日志
        LOGGER.debug("测试输出的日志。。。。。。");
        LOGGER.info("系统发生异常了。。。。。。");
        LOGGER.error("系统发生异常", ex);
        //发邮件、发短信
        //使用jmail工具包
        //使用第三方的WebService
        //显示一个错误页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
