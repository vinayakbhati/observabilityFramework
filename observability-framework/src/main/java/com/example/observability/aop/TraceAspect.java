
package com.example.observability.aop;

import com.example.observability.filter.ObservabilityFilter;
import com.example.observability.context.ObservabilityContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAspect {
    @Around("execution(* com.example.demo..*(..))")
    public Object trace(ProceedingJoinPoint pjp) throws Throwable {
        ObservabilityContext ctx = ObservabilityFilter.getContext();
        if (ctx != null) {
            String method = pjp.getSignature().toShortString();
            ctx.addTrace("INVOKE: " + method);
        }
        Object result = pjp.proceed();
        if (ctx != null) {
            ctx.addTrace("EXIT: " + pjp.getSignature().toShortString());
        }
        return result;
    }
}
