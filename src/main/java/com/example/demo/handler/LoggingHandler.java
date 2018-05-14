package com.example.demo.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingHandler {
	private static final Logger logger = LoggerFactory.getLogger(LoggingHandler.class);

	@Before("execution(* com.example..*.*(..)) && !execution(* com.example.handler..*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info(joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName()
				+ "() started.");
	}

	@After("execution(* com.example..*.*(..)) && !execution(* com.example.handler..*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.info(joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName()
				+ "() ended.");
	}

	@Around("execution(* com.symantec..*.*(..)) && !execution(* com.symantec.mmavis.monitor..*.*(..))")
	public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object retVal;
		try {
			retVal = joinPoint.proceed();

		} finally {
			stopWatch.stop();

			StringBuffer logMessage = new StringBuffer();
			logMessage.append(joinPoint.getTarget().getClass().getSimpleName());
			logMessage.append(".");
			logMessage.append(joinPoint.getSignature().getName());
			logMessage.append("() execution time: ");
			logMessage.append(stopWatch.getTotalTimeMillis());
			logMessage.append(" ms");
			logger.info(logMessage.toString());
		}

		return retVal;
	}
}
