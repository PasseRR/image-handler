package com.gome.image.utils;

import java.io.File;

/**
 * 文件工具方法
 * @author xiehai1
 * @date 2018/02/24 10:40
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface FileUtils {
    /**
     * 获得文件后缀名
     * @param fileName 文件名
     * @return 文件后缀名
     */
    static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 文件是否存在
     * @param path 文件路径
     * @return true/false
     */
    static boolean isExists(String path) {
        return new File(path).exists();
    }
}
