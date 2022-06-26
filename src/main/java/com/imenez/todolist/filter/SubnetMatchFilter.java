package com.imenez.todolist.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class SubnetMatchFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (matches(request, "0:0:0:0:0:0:0:1") || matches(request, "127.0.0.1")) {
            filterChain.doFilter(request, response);
            log.warn("Subnet matched.");
        }else
            log.warn("Subnet did not match!");

    }

    private boolean matches(HttpServletRequest request, String subnet) {
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(subnet);
        return ipAddressMatcher.matches(request);
    }
}