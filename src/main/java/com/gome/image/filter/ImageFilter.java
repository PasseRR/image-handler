package com.gome.image.filter;

import com.gome.image.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 图片处理filter
 * @author xiehai1
 * @date 2018/02/24 10:31
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Slf4j
public class ImageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (log.isDebugEnabled()) {
            log.debug("image filter init!");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getServletPath();
        String query = request.getQueryString();
        if (StringUtils.isEmpty(query) || !ImageUtils.isImage(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 图片处理

        }
    }

    @Override
    public void destroy() {
        if (log.isDebugEnabled()) {
            log.debug("image filter destroy!");
        }
    }
}
