package com.gome.image.utils;

import com.gome.image.eunms.BytesEnum;
import com.gome.image.vo.ImageInfoVo;
import com.gome.image.vo.SizeVo;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import static com.gome.image.utils.FileUtils.getFileSuffix;
import static com.gome.image.utils.FileUtils.isMoreThan100Kb;

/**
 * 图片相关工具方法
 * @author xiehai1
 * @date 2018/02/24 10:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface ImageUtils {
    Pattern p = Pattern.compile(".+(.JPEG|.JPG|.PNG|.GIF|.BMP)$");
    /**
     * 是否是图片
     * @param fileName 文件名
     * @return true/false
     */
    static boolean isImage(String fileName) {
        return p.matcher(fileName.toUpperCase()).matches();
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

    /**
     * 获得图片信息
     * @param file 图片文件
     * @return ImageInfoVo
     */
    static ImageInfoVo getImageInfo(File file) {
        ImageInfoVo.ImageInfoVoBuilder builder = ImageInfoVo.builder();
        builder.name(file.getName())
            .size(BytesEnum.getByteAsString(file.length()));
        SizeVo sizeVo;
        if (isMoreThan100Kb(file.length())) {
            sizeVo = getImageWidthAndHeight(file, getFileSuffix(file.getName()));
        } else {
            sizeVo = getImageWidthAndHeight(file);
        }

        if (null != sizeVo.getHeight() && null != sizeVo.getWidth()) {
            builder.width(sizeVo.getWidth() + "px")
                .height(sizeVo.getHeight() + "px");
        }

        return builder.build();
    }
}
