package com.leviosa.pumpkin.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.springframework.security.core.context.SecurityContextHolder;

public class AddUserIdFilter extends ZuulFilter {
    @Override
    public String filterType() {
		return "pre";
	}

    @Override
	public int filterOrder() {
		return 1;
	}

    @Override
	public boolean shouldFilter() {
		return true;
	}

    @Override
	public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ctx.addZuulRequestHeader("Username", username);   
        return null;    
	}
}