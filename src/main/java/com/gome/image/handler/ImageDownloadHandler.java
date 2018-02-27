package com.gome.image.handler;

import com.gome.image.vo.ImageVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gome.image.constants.CommonConstants.ISO_8859_1;
import static com.gome.image.constants.CommonConstants.UTF_8;

/**
 * 图片下载处理器
 * @author xiehai1
 * @date 2018/02/24 16:06
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class ImageDownloadHandler implements Handler {
    @Override
    public void handle(ImageVo imageVo, HttpServletResponse response) throws IOException {
        byte[] bytes = this.toBufferedImage(imageVo);
        response.addHeader("Content-Length", String.valueOf(bytes.length));
        response.addHeader("Content-Disposition", "attachment;filename="
            + new String(imageVo.getImagePath().getBytes(UTF_8), ISO_8859_1));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.getOutputStream().write(bytes);
    }
}
