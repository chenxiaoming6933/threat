package com.wy.url_api.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ShellUtil {
	
	public static String exec(String shellCommand) throws IOException, InterruptedException{
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer returnStringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        //格式化日期时间，记录日志时使用  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS ");

        stringBuffer.append(dateFormat.format(new Date())).append("准备执行Shell命令 ").append(shellCommand).append(" \r\n");
        LogUtil.info(stringBuffer.toString(),ShellUtil.class);
        stringBuffer = new StringBuffer();
        Process pid = null;
//        String[] cmd = {"/bin/sh", "-c", shellCommand};
        //执行Shell命令
        pid = Runtime.getRuntime().exec(shellCommand);
        if (pid != null) {
            stringBuffer.append("进程号：").append(pid.toString()).append("\r\n");
            //bufferedReader用于读取Shell的输出内容
            bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 2048);
            pid.waitFor(2,TimeUnit.SECONDS);
        } else {
            stringBuffer.append("没有pid\r\n");
        }
        stringBuffer.append(dateFormat.format(new Date())).append("Shell命令: "+shellCommand+" 执行完毕\r\n执行结果为：\r\n");
        String line = null;
        //读取Shell的输出内容，并添加到stringBuffer中
        while (bufferedReader != null && (line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line).append("\r\n");
            returnStringBuffer.append(line).append("\r\n");
        }
        LogUtil.info(stringBuffer.toString(),ShellUtil.class);
            
        return returnStringBuffer.toString();
    }

	public static void main(String[] args) throws Exception, InterruptedException{
		//exec("ls");
		
//		File file1=new File("C:/Users/caocao/Desktop/device/ceshi/10000001_status_main.json");
//		String path=file1.getParent()+File.separatorChar+"0000000"+"_status_main.json";
//		System.out.println(path);
//		System.out.println(file1.getParent());
//		System.out.println(file1.getParentFile());
		String exec = ShellUtil.exec("C:\\Users\\admin\\Desktop\\device_info.bat");
		System.out.println("\n\n\n\n\n\n"+exec);
	}

}