/**
 *
 */
package com.wy.url_api.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author liwei
 * <p><B>last update </B> by liwei @ 2016-8-7</p>
 */
@SuppressWarnings("restriction")
public class Tools {
    private static final Logger LOG = LoggerFactory.getLogger(Tools.class);

    public static String getDBName4FileName(String fileName) {
        return new File(fileName).getParentFile().getName();
        //return "2";
    }

    public static String getDBName4FileCreate(File file) {
        return file.getParentFile().lastModified() + "";
    }


    public static String getDatRoundNumber(String filePath) {
        File directory = new File(filePath);
        String parentPath = directory.getParent();
        return parentPath.substring(parentPath.lastIndexOf(File.separator) + 1, parentPath.length());
    }

    public static String getDatServerNumber(String filePath) {
        File directory = new File(filePath);
        String parentPath = directory.getParent();
        parentPath = parentPath.substring(0, parentPath.lastIndexOf(File.separator));
        return parentPath.substring(parentPath.lastIndexOf(File.separator) + 1, parentPath.length());
    }

    public static void deleteAllFilesOfDir(File path) {
        if (!path.exists())
            return;
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAllFilesOfDir(files[i]);
        }
        path.delete();
    }

    public static long getDirSize(File file) {
        //判断文件是否存在     
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小    
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位   
                long size = file.length();
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        }
    }

    public static long getDirSize(File file, String ext) {
        //判断文件是否存在     
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小    
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children) {
                    size += getDirSize(f, ext);
                }
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位  
                long size = 0;
                if (file.getName().endsWith(ext)) {
                    size = file.length();
                }
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        }
    }

    public static List<String> getDirList(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小    
            if (file.isDirectory()) {
                List<String> dirList = new ArrayList<String>();
                File[] children = file.listFiles();
                for (File f : children) {
                    if (f.isDirectory()) {
                        dirList.add(f.getName());
                    }
                }
                return dirList;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String moveFileToDirectory(String filePath, String directory) {
        File afile = new File(filePath);
        String round = getDatRoundNumber(filePath);
        String moveDirName = directory + File.separator + round + File.separator;
        File dir = new File(moveDirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (afile.renameTo(new File(moveDirName + afile.getName()))) {
            LOG.info(filePath + " is move " + moveDirName + afile.getName() + " successful!");
            return moveDirName + afile.getName();
        } else {
            LOG.info(filePath + " is move " + moveDirName + afile.getName() + " failed!");
            return null;
        }

    }


    /**
     * 解压文件
     *
     * @param zipfile
     * @param type         解压类型 1=角本，2=Java
     * @param oldExtension 解压原文件扩展名
     * @return 解压后文件路径，解压失败为null
     */
    public static String unzip(File zipfile, int type, String oldExtension) {
        if (zipfile.exists()) {
            String dataFileName = zipfile.getName().substring(0, zipfile.getName().lastIndexOf(".")) + oldExtension;
            String dataPath = zipfile.getParent();
            if (type == 1) {
                if (System.getProperties().getProperty("os.name").indexOf("Windows") == -1) {
                    try {
                        //linux
                        Process process = Runtime.getRuntime().exec("unzip -n " + zipfile.getAbsolutePath() + " -d " + dataPath);
                        InputStreamReader ir = new InputStreamReader(process.getInputStream());
                        BufferedReader input = new BufferedReader(ir);
                        String line;
                        while ((line = input.readLine()) != null) {
                            System.out.println(line);
                        }
                        input.close();
                        ir.close();
                        return dataPath + File.separator + dataFileName;
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                    }
                } else {
                    boolean flag = upzipFile(zipfile, dataPath);
                    if (flag) {
                        LOG.info("[解压文件成功]" + zipfile.getAbsolutePath());
                        return dataPath + File.separator + dataFileName;
                    } else {
                        LOG.info("[解压文件失败]" + zipfile.getAbsolutePath());
                    }
                }
            } else if (type == 2) {
                boolean flag = upzipFile(zipfile, dataPath);
                if (flag) {
                    LOG.info("[解压文件成功]" + zipfile.getAbsolutePath());
                    return dataPath + File.separator + dataFileName;
                } else {
                    LOG.info("[解压文件失败]" + zipfile.getAbsolutePath());
                }
            }
        }
        return null;
    }

    /**
     * 解压缩ZIP文件，将ZIP文件里的内容解压到saveFileDir目录下
     *
     * @param saveFileDir 目标目录
     */
    public static boolean upzipFile(File zipFile, String saveFileDir) {
        boolean flag = false;
        if (zipFile.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(zipFile));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                    File target = new File(saveFileDir, entry.getName());
                    if (!target.getParentFile().exists()) {
                        // 创建文件父目录
                        target.getParentFile().mkdirs();
                    }
                    // 写入文件
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
                flag = true;
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                if (zis != null) {
                    try {
                        zis.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 读取文件第一行
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String readFileOneLine(String filePath) throws Exception {
        // 获取轮数信
        File file = new File(filePath);
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            FileReader fr = null;
            try {
                fr = new FileReader(file);
                bufferedReader = new BufferedReader(fr);
                return bufferedReader.readLine();
            } catch (Exception e) {
                // 获取文件内容
                LOG.error("[读取文件失败]：", e.getLocalizedMessage());
                return null;
            } finally {
                bufferedReader.close();
                fr.close();
            }
        }
        return null;
    }

    /**
     * 写文件
     *
     * @param filename
     * @param content
     * @return : boolean
     * @Description : 写文件
     * @Method_Name : writer
     * @Creation Date : 2011-3-2 下午03:31:15
     * @version : v1.00
     * @Author : zhanghao
     */
    public static void writer(String filename, String content) {
        FileWriter writer = null;
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            writer = new FileWriter(filename, true);
            writer.write(content + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void runShell(String shell) {
        try {
            Process process = Runtime.getRuntime().exec(shell);
            process.waitFor();
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            BufferedReader input = new BufferedReader(ir);
            String line;
            while ((line = input.readLine()) != null) {
                LOG.debug(line);
            }
            input.close();
            ir.close();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    public static void runShell(String[] commandArry) {
        try {
            Process process = Runtime.getRuntime().exec(commandArry);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            BufferedReader input = new BufferedReader(ir);
            String line;
            while ((line = input.readLine()) != null) {
                LOG.info(line);
            }
            input.close();
            ir.close();
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static String getRunTime(Date start) {
        Date end = new Date();
        long time_secs = (end.getTime() - start.getTime()) / 1000;
        String result = "用时: " + (time_secs / 60) + " 分 " + (time_secs % 60) + " 秒（共 " + time_secs + " 秒）";
        return result;
    }

    public static String getRunTime2(Date start) {
        Date end = new Date();
        long time_secs = (end.getTime() - start.getTime());
        String result = "用时:（共 " + time_secs + " 毫秒）";
        return result;
    }

    public static long getFilsCount(File file, String suffix) {
        //判断文件是否存在     
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小    
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    size += getFilsCount(f, suffix);
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位 
                long size = 0;
                if (file.getName().endsWith(suffix)) {
                    size = 1;
                }
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        }
    }

    // 加密
    public static String getBase64(String str, String code) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes(code);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s, String code) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, code);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * ip转化成Long
     * 
     * @param ip
     * @return
     */
    public static long ipToInteger(String ip) {
        String[] ips = ip.split("\\.");
        long ipFour = 0;
        // 因为每个位置最大255，刚好在2进制里表示8位
        for (String ip4 : ips) {
            long ip4a = Long.parseLong(ip4);
            // 这里应该用+也可以,但是位运算更快
            ipFour = (ipFour << 8) | ip4a;
        }
        return ipFour;
    }
    /**
     * long转出啊成ip
     * 
     * @param ip
     * @return
     */
    public static String IntegerToIp(long ip) {
        // 思路很简单，每8位拿一次，就是对应位的IP
        StringBuilder sb = new StringBuilder();
        for (long i = 3; i >= 0; i--) {
            long ipa = (ip >> (8 * i)) & (0xff);
            sb.append(ipa + ".");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public static void main(String[] args) {
//    	File zipfile=new File("E:"+File.separator+"tmp"+File.separator+"data"+File.separator+"APortIP_2001"+File.separator+"1"+File.separator+"APortIP_2001_1_1473693636.zip");
//    	String defaultExtension=".dat";
//    	String dataFileName=zipfile.getName().substring(0,zipfile.getName().lastIndexOf("."))+defaultExtension;
//    	System.out.println(dataFileName);
//    	System.out.println(zipfile.getParent());
//    	System.out.println(zipfile.getParent()+File.separator+dataFileName);
//    	System.out.println(zipfile.getAbsolutePath().substring(0,zipfile.getAbsolutePath().indexOf(zipfile.getName())));
//    	System.out.println(System.getProperties().getProperty("os.name"));
        String filePath = "E:\\tmp\\data\\AliveIP_50001";
        List<String> list = getDirList(new File(filePath));

        for (int j = 3; j > 0; j--) {
            System.out.println(list.get(j - 1));
        }

//		runShell("cmd /c start cmd.exe /c ping 192.168.1", true);
//		System.out.println("end");
    }
}
