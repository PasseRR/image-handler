package com.gome.image.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import static com.gome.image.constants.ImageParamsConstants.PIXEL_SEPARATOR;

/**
 * 宽高vo
 * @author xiehai1
 * @date 2018/02/24 14:09
 * @Copyright(c) gome inc Gome Co.,LTD
 */
@Builder
@ToString
@Getter
public class SizeVo extends BaseVo {
    /**
     * 宽度(px)
     */
    private Double width;
    /**
     * 高度(px)
     */
    private Double height;

    /**
     * 通过size字符串解析出宽高 300x200
     * @param size 字符串
     * @return SizeVo
     */
    public static SizeVo parse(String size) {
        SizeVo sizeVo = null;
        if (StringUtils.isNoneBlank(size)) {
            // 忽略分隔符大小写
            String[] nums = size.toLowerCase().split(PIXEL_SEPARATOR);
            // 若不是两个数字 直接忽略
            if (nums.length > 1) {
                try {
                    // 只取数组前两个数字作为宽高
                    Double width = Double.valueOf(nums[0]);
                    Double height = Double.valueOf(nums[1]);
                    // 只有宽高都大于0才有效
                    if (width > 0 && height > 0) {
                        sizeVo = SizeVo.builder()
                            .width(width)
                            .height(height)
                            .build();
                    }
                } catch (Exception e) {
                    // 若数字格式错误 忽略该属性
                }
            }
        }

        return sizeVo;
    }
}
