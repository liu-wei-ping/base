package com.base.core.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP Controller RestController
 * 
 * @author liu wp
 * 
 */
@Aspect
@Component
public class AutoControllerAspect {
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 日志类 */
	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	/**
	 * AfterReturning 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行
	 *
	 * @param joinPoint
	 * @param returnObj
	 *            返回值
	 */
	@AfterReturning(pointcut = "allControllerMethod()||allrestControllerMethod()", returning = "returnObj")
	public void afterReturning(final JoinPoint joinPoint, final Object returnObj) {
		logger.info("----afterReturning Start 请求时间：{}----", dateTimeFormat.format(new Date()));
		logger.info("返回值：{}", returnObj);
		logger.info("----afterReturning End 请求时间：{}----", dateTimeFormat.format(new Date()));

	}

	/**
	 * AfterThrowing 核心业务逻辑调用异常退出后执行，处理错误信息
	 *
	 * @param joinPoint
	 * @param ex
	 *            异常信息
	 */
	@AfterThrowing(value = "allControllerMethod()||allrestControllerMethod()", throwing = "ex")
	public void AfterThrowing(final JoinPoint joinPoint, final Exception ex) {
		logger.info("----AfterThrowing Start 请求时间：{}---", dateTimeFormat.format(new Date()));
		logger.info("异常信息：{}", ex.getMessage());
		logger.info("----AfterThrowing End 请求时间：{}---", dateTimeFormat.format(new Date()));
	}

	/**
	 * 监控所有@Controller的方法
	 */
	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void allControllerMethod() {
	}

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void allrestControllerMethod() {
	}

	/**
	 * Around 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
	 *
	 * 注意：当核心业务抛异常后，立即退出，转向afterReturning 执行完afterReturning，再转到AfterThrowing
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("allControllerMethod()||allrestControllerMethod()")
	public Object Around(final ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("----Around Start 请求时间：{}----", dateTimeFormat.format(new Date()));
		logger.info("---请求前 beforeMethod");
		final Object obj = joinPoint.proceed();
		logger.info("---请求后  afterReturning");
		logger.info("----Around End 请求时间：{}----", dateTimeFormat.format(new Date()));
		return obj;
	}

	/**
	 * 在核心业务执行前执行。
	 *
	 * @param joinPoint
	 * @throws InterruptedException
	 */
	@Before("allControllerMethod() || allrestControllerMethod()")
	public void beforeMethod(final JoinPoint joinPoint) throws InterruptedException {
		logger.info("--beforeMethod Start 请求时间：{}--", dateTimeFormat.format(new Date()));
		final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		final HttpServletRequest request = servletRequestAttributes.getRequest();
		final String requestUrl = request.getRequestURI().toString();
		final int jpsHashCode = joinPoint.getSignature().hashCode();
		logger.info("访问序号：{}，请求路径：{},访问请求参数对象 : {}", jpsHashCode, requestUrl, Arrays.toString(joinPoint.getArgs()));
		logger.info("--beforeMethod End 请求时间：{}--", dateTimeFormat.format(new Date()));
	}

}
