package com.wy.url_api.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作工具类
 * @author caocao
 *
 */
public class FileUtil {

    /**
     * 是否为该目录下的文件
     * @param dirPath
     * @param childPath
     * @return
     */
    public static boolean isChildFile(String dirPath, String childPath){
        if (StringUtils.isBlank(dirPath) || StringUtils.isBlank(childPath)) {
            return false;
        }
        return isChildFile(new File(dirPath), new File(childPath));
    }

    /**
     * 是否为该目录下的文件
     * @param dirFile
     * @param childFile
     * @return
     */
    public static boolean isChildFile(File dirFile, File childFile){
        if (dirFile == null || childFile == null) {
            return false;
        }

        if (!dirFile.exists() || !childFile.exists()) {
            return false;
        }

        try {
            if (childFile.getCanonicalPath().startsWith(dirFile.getCanonicalPath())) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
    
	public static String getJsonPath() {
		File file = new File(FileUtil.class.getClassLoader().getResource(File.separator).getFile());
		String path = file.getParentFile().getParentFile().getPath() + File.separator + "json" + File.separator;
		return path;
	}
	
	public static String getResourcePath() {
		File file = new File(FileUtil.class.getClassLoader().getResource(File.separator).getFile());
		String path = file.getParentFile() + File.separator;
		return path;
	}
	
	public static void main(String[] args) {
		File file = new File(FileUtil.class.getClassLoader().getResource(File.separator).getFile());
		String path = file.getParentFile().getParentFile().getPath() + File.separator + "json" + File.separator;
		System.out.println( path);
	}

}
