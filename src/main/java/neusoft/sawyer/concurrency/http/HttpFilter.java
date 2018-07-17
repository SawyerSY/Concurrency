package neusoft.sawyer.concurrency.http;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.threadlocal.RequestHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by sawyer on 2018/7/17.
 */
@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        long threadId = Thread.currentThread().getId();
        log.info("Do filter: {}, {}", threadId, httpServletRequest.getServletPath());
        RequestHolder.setId(threadId);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
