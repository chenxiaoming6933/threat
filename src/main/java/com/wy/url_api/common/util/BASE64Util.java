package com.wy.url_api.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

@SuppressWarnings("restriction")
public class BASE64Util {

	public static String getBase64(String str,String code) {
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

	/**
	 * 解密
	 */
	public static String getFromBase64(String s,String code) {
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


}
