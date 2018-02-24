package com.gome.image.eunms;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.gome.image.constants.BytesConstants.BYTE;
import static com.gome.image.constants.BytesConstants.BYTE_NAME;
import static com.gome.image.constants.BytesConstants.GIGA_BYTE;
import static com.gome.image.constants.BytesConstants.GIGA_BYTE_NAME;
import static com.gome.image.constants.BytesConstants.KILO_BYTE;
import static com.gome.image.constants.BytesConstants.KILO_BYTE_NAME;
import static com.gome.image.constants.BytesConstants.MEGA_BYTE;
import static com.gome.image.constants.BytesConstants.MEGA_BYTE_NAME;

/**
 * 字节类型枚举
 * @author xiehai1
 * @date 2018/02/24 17:44
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public enum BytesEnum {
    /**
     * 字节
     */
    B(1, BYTE - 1, BYTE_NAME),
    /**
     * 千字节
     */
    KB(BYTE, KILO_BYTE - 1, KILO_BYTE_NAME),
    /**
     * 兆字节
     */
    MB(KILO_BYTE, MEGA_BYTE - 1, MEGA_BYTE_NAME),
    /**
     * 吉字节
     */
    GB(MEGA_BYTE, GIGA_BYTE - 1, GIGA_BYTE_NAME);
    /**
     * 最小字节数
     */
    private long min;
    /**
     * 最大字节数
     */
    private long max;
    /**
     * 名称
     */
    private String name;

    BytesEnum(long min, long max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    /**
     * 通过给定字节数 获得对应字节类型
     * @param bytes 字节数
     * @return 字节类型
     */
    static BytesEnum getStorageBytesEnum(long bytes) {
        for (BytesEnum bytesEnum : BytesEnum.values()) {
            if (bytes >= bytesEnum.min && bytes <= bytesEnum.max) {
                return bytesEnum;
            }
        }

        // 超出最大返回GB
        return GB;
    }

    /**
     * 通过字节数获得字符串形式的字节
     * @param bytes 字节数
     * @return 1.2KB 3MB
     */
    public static String getByteAsString(long bytes) {
        BytesEnum bytesEnum = getStorageBytesEnum(bytes);
        BigDecimal size = new BigDecimal(bytes).divide(
            new BigDecimal(bytesEnum.min),
            // 保留3位小数
            3,
            RoundingMode.HALF_UP
        );

        return size.toString() + bytesEnum.name;
    }
}