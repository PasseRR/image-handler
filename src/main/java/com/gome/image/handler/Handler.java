package com.gome.image.handler;

import com.gome.image.vo.ImageVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理器
 * @author xiehai1
 * @date 2018/02/24 15:33
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface Handler {
    /**
     * 处理过程
     * @param imageVo  图像处理信息
     * @param response http应答
     * @throws IOException
     */
    void handle(ImageVo imageVo, HttpServletResponse response) throws IOException;
}
