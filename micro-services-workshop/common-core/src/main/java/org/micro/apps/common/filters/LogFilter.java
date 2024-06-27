package org.micro.apps.common.filters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author tibinatomy
 */

@Component
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestBody = new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines()
                .collect(Collectors.joining("\t"));

        log.info("Request incoming : target = {}, payload = {}", request.getRequestURL(), requestBody);

        filterChain.doFilter(servletRequest, servletResponse);

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("Response outgoing : status= {}",  HttpStatus.valueOf(response.getStatus()));
    }
}
