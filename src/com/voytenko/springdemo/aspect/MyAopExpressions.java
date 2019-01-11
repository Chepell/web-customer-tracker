package com.voytenko.springdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Artem Voytenko
 * 11.01.2019
 */

@Aspect
public class MyAopExpressions {

	@Pointcut("execution(* com.voytenko.springdemo.dao.*.*(..))")
	public void forDaoPackage() {}

	@Pointcut("execution(* com.voytenko.springdemo.controller.*.*(..))")
	public void forControllerPackage() {}

	@Pointcut("execution(* com.voytenko.springdemo.service.*.*(..))")
	public void forServicePackage() {}

	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	public void forAllPackage() {}
}
