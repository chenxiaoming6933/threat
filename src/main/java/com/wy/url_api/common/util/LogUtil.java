package com.wy.url_api.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

/**
 * 日志工具类,不在controller中使用，在service等中使用
 * 方法不全自己扩展，不使用也行
 * @author caocao
 */
public class LogUtil {
	
	/**
	 * 将信息打印到自定义日志（dev_info.log）中
	 * @param message 需要被打印的信息
	 */
    public static void info(Object message,Class<?> clazz){
    	Logger logger  =  LoggerFactory.getLogger(clazz);
    	
    	StringBuffer logOut = new StringBuffer();
    	logOut.append("\n");
    	logOut.append(message);
    	logOut.append("\n");
    	
    	logger.info(logOut.toString());
    }

    /**
     * 将信息打印到自定义日志（system_error.log）中
     * @param e 异常信息
     */
    public static void error(Exception e, Class<?> clazz){
        Logger logger  =  LoggerFactory.getLogger(clazz);

        StringBuffer logOut = new StringBuffer();
        logOut.append("\n");
        logOut.append(e.toString());
        logOut.append("\n");

        StackTraceElement[] errorList = e.getStackTrace();
        for (StackTraceElement stackTraceElement : errorList) {
            logOut.append(stackTraceElement.toString());
            logOut.append("\n");
        }

        logOut.append("\n");
        logOut.append("\n");

        logger.error(logOut.toString());
    }

    public static void main(String[] args) throws UnknownHostException {
//               InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
//               String hostAddress = address.getHostAddress();//192.168.0.121      
//               System.out.println(hostAddress);
    	String aa="192.168.1.1";
    	String[] split2 = aa.split(".");
    	
   }

	public static void error(String message, Class<?> clazz) {
		Logger logger  =  LoggerFactory.getLogger(clazz);
    	
    	StringBuffer logOut = new StringBuffer();
    	logOut.append("\n");
    	logOut.append(message);
    	logOut.append("\n");
    	
    	logger.error(logOut.toString());
		
	}
}
