package be.securex.sss.dimonaconverterservice.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by 6060 on 10/03/2016.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* be.securex.sss.dimonaconverterservice.controller.*.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        logger.info( "Begin --- "+pjp.getSignature().getDeclaringType().getSimpleName()+"."+pjp.getSignature().getName());
        Object obj = pjp.proceed();
        logger.info( "End --- "+pjp.getSignature().getDeclaringType().getSimpleName()+"."+pjp.getSignature().getName());
        return obj;
    }
}
