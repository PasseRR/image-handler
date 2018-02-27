package com.gome.image.vo;

import com.gome.image.eunms.ImageOperationEnum;
import com.gome.image.utils.FileUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

import static com.gome.image.constants.CommonConstants.EXCLAMATION_MARK;
import static com.gome.image.constants.ImageParamsConstants.CUT;
import static com.gome.image.constants.ImageParamsConstants.DOWNLOAD;
import static com.gome.image.constants.ImageParamsConstants.FORMAT;
import static com.gome.image.constants.ImageParamsConstants.INFO;
import static com.gome.image.constants.ImageParamsConstants.QUALITY;
import static com.gome.image.constants.ImageParamsConstants.ROTATE;
import static com.gome.image.constants.ImageParamsConstants.SIZE;
import static com.gome.image.constants.ImageParamsConstants.ZOOM;
import static com.gome.image.utils.FileUtils.getFilePathWithoutSuffix;

/**
 * 图片请求vo
 * @author xiehai1
 * @date 2018/02/24 13:29
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Builder
@Getter
@ToString
public class ImageVo extends BaseVo {
    /**
     * 旋转角度(°) 正数顺时针 负数逆时针
     */
    private Double rotate;
    /**
     * 图片质量(%)
     */
    private Double quality;
    /**
     * 按比例缩放图片(%)
     */
    private Double zoom;
    /**
     * 按大小缩放(px) 300x200
     */
    private SizeVo size;
    /**
     * 裁剪图片(px) 300x200
     */
    private SizeVo cut;
    /**
     * 图片格式
     */
    private String format;
    /**
     * 图片物理路径
     */
    private String path;
    /**
     * 临时图片路径
     */
    private String thumbPath;
    private String suffix;
    /**
     * 图片操作类型
     */
    private ImageOperationEnum imageOperationEnum;

    /**
     * 通过get参数构建ImageVo对象
     * @param params   GET参数
     * @param filePath 图片绝对路径
     * @return ImageVo
     */
    public static ImageVo parse(Map<String, String[]> params, String filePath) {
        ImageVoBuilder builder = ImageVo.builder()
            .path(filePath);
        // 旋转角度
        if (params.containsKey(ROTATE)) {
            try {
                // GET参数可以是数组 只取数组第一个值 下同
                builder.rotate(Double.valueOf(params.get(ROTATE)[0]));
            } catch (Exception e) {
                // 若数字格式错误 忽略该属性
            }
        }
        // 图片质量
        if (params.containsKey(QUALITY)) {
            try {
                builder.quality(Double.valueOf(params.get(QUALITY)[0]));
            } catch (Exception e) {
                // 若数字格式错误 忽略该属性
            }
        }
        // 缩放比例
        if (params.containsKey(ZOOM)) {
            try {
                builder.zoom(Double.valueOf(params.get(ZOOM)[0]));
            } catch (Exception e) {
                // 若数字格式错误 忽略该属性
            }
        }
        // 按大小缩放
        if (params.containsKey(SIZE)) {
            builder.size(SizeVo.parse(params.get(SIZE)[0]));
        }
        // 裁剪
        if (params.containsKey(CUT)) {
            builder.cut(SizeVo.parse(params.get(CUT)[0]));
        }

        // 默认为查看图片
        builder.imageOperationEnum(ImageOperationEnum.VIEW);
        // 是否是下载图片
        if (params.containsKey(DOWNLOAD)) {
            builder.imageOperationEnum(ImageOperationEnum.DOWNLOAD);
            // 图片格式化 下载的时候使用
            if (params.containsKey(FORMAT)) {
                builder.format(params.get(FORMAT)[0]);
            }
        }
        // 图片信息
        if (params.containsKey(INFO)) {
            builder.imageOperationEnum(ImageOperationEnum.INFO);
        }

        return builder.suffix(FileUtils.getFileSuffix(filePath))
            .build();
    }

    /**
     * 获得临时图片名称
     * @return 图片名
     */
    public String getImagePath() {
        StringBuilder sb = new StringBuilder(getFilePathWithoutSuffix(this.path));
        if (null != this.size) {
            sb.append("_s");
            sb.append(this.size.getWidth());
            sb.append("x");
            sb.append(this.size.getHeight());
            if (this.size.isForce()) {
                sb.append(EXCLAMATION_MARK);
            }
        }

        if (null != this.cut) {
            sb.append("_c");
            sb.append(this.cut.getWidth());
            sb.append("x");
            sb.append(this.cut.getHeight());
            if (this.cut.isForce()) {
                sb.append(EXCLAMATION_MARK);
            }
        }

        if (null != this.quality) {
            sb.append("_q");
            sb.append(this.quality);
        }

        if (null != this.rotate) {
            sb.append("_r");
            sb.append(this.rotate);
        }

        if (null != this.zoom) {
            sb.append("_z");
            sb.append(this.zoom);
        }

        if (null != this.format) {
            sb.append("_f");
            sb.append(this.format);
        }

        sb.append(".");
        sb.append(FileUtils.getFileSuffix(this.path));

        return sb.toString();
    }
}
