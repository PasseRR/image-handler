package com.gome.image.handler;

import com.gome.image.vo.ImageVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 图片下载处理器
 * @author xiehai1
 * @date 2018/02/24 16:06
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class ImageDownloadHandler implements Handler {
    @Override
    public void handle(ImageVo imageVo, HttpServletResponse response) {

    }
}
