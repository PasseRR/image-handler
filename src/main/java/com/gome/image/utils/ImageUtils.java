package com.gome.image.utils;

import com.gome.image.vo.SizeVo;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * 图片相关工具方法
 * @author xiehai1
 * @date 2018/02/24 10:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface ImageUtils {
    Pattern P = Pattern.compile(".+(.JPEG|.JPG|.PNG|.GIF|.BMP)$");
    /**
     * 是否是图片
     * @param fileName 文件名
     * @return true/false
     */
    static boolean isImage(String fileName) {
        return P.matcher(fileName.toUpperCase()).matches();
    }

    /**
     * 获取图片宽高(图片越大，消耗的时间越长，针对百KB以下的图片速度较快)
     * @param file 图片
     * @return 图片宽高
     */
    static SizeVo getImageWidthAndHeight(File file) {
        SizeVo.SizeVoBuilder builder = SizeVo.builder();
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            builder.width((double) bufferedImage.getWidth())
                .height((double) bufferedImage.getHeight());
        } catch (IOException e) {
            // ignore
        }
        return builder.build();
    }

    /**
     * 获取图片宽高(不论图片大小，基本恒定时间，在100ms左右)
     * @param file   文件
     * @param suffix 图片后缀
     * @return 图片宽高
     */
    static SizeVo getImageWidthAndHeight(File file, String suffix) {
        SizeVo.SizeVoBuilder builder = SizeVo.builder();
        try {
            Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReadersByFormatName(suffix);
            ImageReader reader = imageReaderIterator.next();
            ImageInputStream imageInputStream = ImageIO.createImageInputStream(file);
            reader.setInput(imageInputStream, true);
            builder.width((double) reader.getWidth(0))
                .height((double) reader.getHeight(0));
        } catch (IOException e) {
            // ignore
        }

        return builder.build();
    }
}
