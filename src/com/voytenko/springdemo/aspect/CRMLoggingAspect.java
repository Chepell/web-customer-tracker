package com.voytenko.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Artem Voytenko
 * 11.01.2019
 */

@Aspect
@Component
public class CRMLoggingAspect {
	// setup logger
	private static Logger myLogger = Logger.getLogger(CRMLoggingAspect.class.getName());


	// add @Before advice
	@Before("com.voytenko.springdemo.aspect.MyAopExpressions.forAllPackage()")
	public void before(JoinPoint joinPoint) {
		// display the method we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n\n======> in @Before: calling method: " + method);

		// display the arguments to the method
		// get argument
		Object[] args = joinPoint.getArgs();
		// loop and show arguments
		for (Object arg : args) {
			myLogger.info("=======> argument: " + arg);
		}
	}

	// add @AfterReturning advice
	@AfterReturning(
			pointcut = "com.voytenko.springdemo.aspect.MyAopExpressions.forAllPackage()",
			returning = "result")
	public void after(JoinPoint joinPoint, Object result) {
		// display method we are returning from advice
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n\n======> in @AfterReturning: from method: " + method);

		// display the data return
		myLogger.info("\n\n======>" + result);
	}
}
