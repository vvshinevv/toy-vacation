package com.vacation.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExcludedUriHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    private List<String> excludePatterns = new ArrayList<>();

    protected void add(String pattern) {
        this.excludePatterns.add(pattern);
    }

    private boolean isExcludedUri(HttpServletRequest request) {
        for (String uri : excludePatterns) {
            if (request.getRequestURI().indexOf(uri) == 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isExcludedUri(request)) {
            return doPreHandle(request, response, handler);
        }

        return true;
    }

    @Override
    public final void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!isExcludedUri(request)) {
            doPostHandle(request, response, handler, modelAndView);
        }
    }

    @Override
    public final void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!isExcludedUri(request)) {
            doAfterCompletion(request, response, handler, ex);
        }
    }

    @Override
    public final void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isExcludedUri(request)) {
            doAfterConcurrentHandlingStarted(request, response, handler);
        }
    }

    abstract public boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    abstract public void doPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;

    abstract public void doAfterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;

    abstract public void doAfterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

}
