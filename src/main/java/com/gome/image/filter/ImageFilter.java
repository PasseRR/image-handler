package com.gome.image.filter;

import com.gome.image.handler.ImageHandler;
import com.gome.image.vo.ImageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.gome.image.utils.FileUtils.isExists;
import static com.gome.image.utils.ImageUtils.isImage;

/**
 * 图片处理filter
 * @author xiehai1
 * @date 2018/02/24 10:31
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Slf4j
public class ImageFilter implements Filter {
    @Autowired
    private ImageHandler imageHandler;
    @Value("${spring.resources.static-locations}")
    private String resourceLocation;

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
        Map<String, String[]> params = request.getParameterMap();
        // 若不是图片路径或者没有query参数
        // 直接请求资源
        if (!isImage(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 图片绝对路径
            String filePath = this.resourceLocation + path;
            // 图片不存在 直接结束
            if (isExists(filePath)) {
                // 图片处理
                ImageVo imageVo = ImageVo.parse(params, filePath);
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                this.imageHandler.handle(imageVo, response);
            } else {
                if (log.isWarnEnabled()) {
                    log.warn("file '{}' not exists!", filePath);
                }
            }
        }
    }

    @Override
    public void destroy() {
        if (log.isDebugEnabled()) {
            log.debug("image filter destroy!");
        }

        this.imageHandler = null;
    }
}
