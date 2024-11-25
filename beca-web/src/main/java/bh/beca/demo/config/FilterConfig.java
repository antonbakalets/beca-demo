package bh.beca.demo.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<StaticResourceForwarderFilter> staticResourceForwarderFilter() {
        FilterRegistrationBean<StaticResourceForwarderFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new StaticResourceForwarderFilter());
        return filterFilterRegistrationBean;
    }

    public static class StaticResourceForwarderFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            if (!httpRequest.getRequestURI().startsWith("/api/") && !httpRequest.getRequestURI().equals("/")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
                requestDispatcher.forward(request, response);
                return;
            }

            chain.doFilter(request, response);
        }
    }
}
