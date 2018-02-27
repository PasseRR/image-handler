package com.gome.image.handler;

import com.gome.image.eunms.BytesEnum;
import com.gome.image.vo.ImageInfoVo;
import com.gome.image.vo.ImageVo;
import com.gome.image.vo.SizeVo;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static com.gome.image.utils.FileUtils.getFileSuffix;
import static com.gome.image.utils.FileUtils.isMoreThan100Kb;
import static com.gome.image.utils.ImageUtils.getImageWidthAndHeight;

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

    /**
     * 获得图片信息
     * @param file 图片文件
     * @return ImageInfoVo
     */
    default ImageInfoVo getImageInfo(File file) {
        ImageInfoVo.ImageInfoVoBuilder builder = ImageInfoVo.builder();
        builder.name(file.getName())
            .size(BytesEnum.getByteAsString(file.length()));
        SizeVo sizeVo = isMoreThan100Kb(file.length())
            ? getImageWidthAndHeight(file, getFileSuffix(file.getName()))
            : getImageWidthAndHeight(file);

        if (null != sizeVo.getHeight() && null != sizeVo.getWidth()) {
            builder.width(sizeVo.getWidth() + "px")
                .height(sizeVo.getHeight() + "px");
        }

        return builder.build();
    }

    /**
     * 通过ImageVo转为BufferedImage
     * @param imageVo 图片信息
     * @return BufferedImage
     * @throws IOException e
     */
    default byte[] toBufferedImage(ImageVo imageVo) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File file = new File(imageVo.getPath());
        Thumbnails.Builder<File> builder = Thumbnails.of(file);

        // 按照尺寸
        SizeVo size = imageVo.getSize();
        if (null != size) {
            builder.size(size.getWidth().intValue(), size.getHeight().intValue())
                .keepAspectRatio(size.isForce());
        } else {
            BufferedImage bufferedImage = ImageIO.read(file);
            builder.size(bufferedImage.getWidth(), bufferedImage.getHeight())
                .keepAspectRatio(true);
        }

        // 旋转角度
        if (null != imageVo.getRotate()) {
            builder.rotate(imageVo.getRotate())
                // 旋转图片设置格式为png 使背景色透明
                .outputFormat("png");
        }

        // 图片质量
        if (null != imageVo.getQuality()) {
            double quality = imageVo.getQuality() / 100.0;
            builder.outputQuality(quality > 1 || quality < 0 ? 1 : quality);
        }

        // 比例缩放
        if (null != imageVo.getZoom()) {
            double zoom = imageVo.getZoom() / 100.0;
            builder.scale(zoom > 1 || zoom < 0 ? 1 : zoom);
        }

        builder.toOutputStream(baos);

        return baos.toByteArray();
    }
}
