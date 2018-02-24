package com.gome.image.constants;

/**
 * 字节类型相关常量
 * @author xiehai1
 * @date 2018/02/24 18:51
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface BytesConstants {
    /**
     * 进制
     */
    long STEP = 1024L;
    /**
     * 字节
     */
    long BYTE = STEP;
    String BYTE_NAME = "B";
    /**
     * 千字节
     */
    long KILO_BYTE = BYTE * STEP;
    String KILO_BYTE_NAME = "KB";
    /**
     * 兆字节
     */
    long MEGA_BYTE = KILO_BYTE * STEP;
    String MEGA_BYTE_NAME = "MB";
    /**
     * 吉字节
     */
    long GIGA_BYTE = MEGA_BYTE * STEP;
    String GIGA_BYTE_NAME = "GB";
    /**
     * 太字节
     */
    long TERA_BYTE = GIGA_BYTE * STEP;
    String TERA_BYTE_NAME = "TB";
    /**
     * 拍字节
     */
    long PETA_BYTE = TERA_BYTE * STEP;
    String PETA_BYTE_NAME = "PB";

    // exabyte(艾字节EB)、zettabyte(皆字节)等超出long范围不列举
}
