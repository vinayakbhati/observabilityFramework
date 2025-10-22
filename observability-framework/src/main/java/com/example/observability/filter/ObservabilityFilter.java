
package com.example.observability.filter;

import com.example.observability.context.ObservabilityContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class ObservabilityFilter implements Filter {
    private static final ThreadLocal<ObservabilityContext> contextHolder = new ThreadLocal<>();

    public static ObservabilityContext getContext() {
        return contextHolder.get();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ObservabilityContext context = new ObservabilityContext();
        contextHolder.set(context);

        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);
        long start = System.currentTimeMillis();

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            context.addTrace("API Call: " + req.getMethod() + " " + req.getRequestURI());
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            context.addTrace("Total Duration: " + duration + " ms");
            System.out.println("OBSERVABILITY SUMMARY -> " + context.getTracePath());
            MDC.clear();
            contextHolder.remove();
        }
    }
}
