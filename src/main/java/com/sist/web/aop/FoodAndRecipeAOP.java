package com.sist.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FoodAndRecipeAOP {
	@Around("execution(* com.sist.web.controller.*Controller.*(..))")
	public Object log(ProceedingJoinPoint jp) throws Throwable
	{
		System.out.println("요청 처리 메소드:"+jp.getSignature().getName());
		long start=System.currentTimeMillis();
		Object obj=jp.proceed();
		long end=System.currentTimeMillis();
		System.out.println("요청처리 시간:"+(end-start)+"MS");
		return obj;
	}
}
