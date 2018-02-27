package com.gome.image.handler;

import com.gome.image.vo.ImageVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.gome.image.constants.CommonConstants.GSON;
import static com.gome.image.constants.CommonConstants.UTF_8;

/**
 * 图片信息处理器
 * @author xiehai1
 * @date 2018/02/24 16:05
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class ImageInfoHandler implements Handler {
    @Override
    public void handle(ImageVo imageVo, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setCharacterEncoding(UTF_8);
        GSON.toJson(
            this.getImageInfo(new File(imageVo.getPath())),
            response.getWriter()
        );
    }
}
