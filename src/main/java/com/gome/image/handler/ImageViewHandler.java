package com.gome.image.handler;

import com.gome.image.vo.ImageVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片查看处理器
 * @author xiehai1
 * @date 2018/02/24 16:05
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class ImageViewHandler implements Handler {
    @Override
    public void handle(ImageVo imageVo, HttpServletResponse response) throws IOException {

    }
}
