package org.mwdziak.vaccinationbackend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Incoming request: method={}, URI={}", request.getMethod(), request.getRequestURI());

        // Log request headers
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("Header: {}={}", headerName, request.getHeader(headerName));
        }

        // Log request parameters
        request.getParameterMap().forEach((name, values) -> {
            for (String value : values) {
                logger.info("Parameter: {}={}", name, value);
            }
        });

        // Continue with the filter chain
        filterChain.doFilter(request, response);
        logger.info("Outgoing response: status={}", response.getStatus());
        return;
    }
}
