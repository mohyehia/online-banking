package com.mohyehia.onlinebanking.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AOPLogger {
	private final Log log = LogFactory.getLog(this.getClass());
	
//	@Around("execution(* com.mohyehia.onlinebanking..*.*(..))")
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + " Started");
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		
		Object retVal = joinPoint.proceed();
		
		stopWatch.stop();
		
		StringBuilder logMessage = new StringBuilder();
		logMessage.append(joinPoint.getTarget().getClass().getName());
		logMessage.append(".");
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("(");
		
		Object[] args = joinPoint.getArgs();
		for (Object obj : args) {
            logMessage.append(obj).append(",");
            log.info(obj);
        }
        if (args.length > 0) {
            logMessage.deleteCharAt(logMessage.length() - 1);
        }
        
        logMessage.append(")");
        logMessage.append(" execution time: ");
        logMessage.append(stopWatch.getTotalTimeMillis());
        logMessage.append(" ms");
        log.info(logMessage.toString());
        log.info(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+" Return Value is "+retVal);
        log.info(joinPoint.getTarget().getClass().getName()+"."+joinPoint.getSignature().getName()+" Finished");
        return retVal;
	}
}
