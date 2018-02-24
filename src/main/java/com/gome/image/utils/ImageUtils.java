package com.gome.image.utils;

import java.util.regex.Pattern;

/**
 * 图片相关工具方法
 * @author xiehai1
 * @date 2018/02/24 10:38
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface ImageUtils {
    Pattern p = Pattern.compile(".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.gif|.GIF|.bmp|.BMP)$");
    /**
     * 是否是图片
     * @param fileName 文件名
     * @return true/false
     */
    static boolean isImage(String fileName) {
        return p.matcher(fileName).matches();
    }

    static void main(String[] args) {
        System.out.println(isImage("/javaer.jpg"));
    }
}
