package com.gome.image.handler;

import com.gome.image.eunms.ImageOperationEnum;
import com.gome.image.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图像处理器
 * @author xiehai1
 * @date 2018/02/24 12:11
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Component
public class ImageHandler implements Handler {
    @Autowired
    private ImageViewHandler imageViewHandler;
    @Autowired
    private ImageInfoHandler imageInfoHandler;
    @Autowired
    private ImageDownloadHandler imageDownloadHandler;

    @Override
    public void handle(ImageVo imageVo, HttpServletResponse response) throws IOException {
        this.getHandler(imageVo.getImageOperationEnum())
            .handle(imageVo, response);
    }

    private Handler getHandler(ImageOperationEnum imageOperationEnum) {
        Handler handler;
        switch (imageOperationEnum) {
            case INFO: {
                handler = this.imageInfoHandler;
                break;
            }
            case DOWNLOAD: {
                handler = this.imageDownloadHandler;
                break;
            }
            // 默认为查看图片
            default: {
                handler = this.imageViewHandler;
                break;
            }
        }

        return handler;
    }
}
