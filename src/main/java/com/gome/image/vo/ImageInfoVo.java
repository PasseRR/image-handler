package com.gome.image.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 图片文件信息
 * @author xiehai1
 * @date 2018/02/24 16:18
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@ToString
@Builder
@Getter
public class ImageInfoVo extends BaseVo {
    /**
     * 图片名称
     */
    private String name;
    /**
     * 图片大小描述
     */
    private String size;
    /**
     * 宽度(px)
     */
    private String width;
    /**
     * 高度(px)
     */
    private String height;
}
