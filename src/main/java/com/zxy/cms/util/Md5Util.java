package com.zxy.cms.util;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * 
 * @ClassName: Md5Util 
 * @Description:  对密码进行加密处理
 * @author: admin
 * @date: 2020年3月12日 下午4:55:18
 */
public class Md5Util {

	private static String salt="qazwsx1234";
	public static String encode(String password) {
		  return DigestUtils.md5Hex(password + salt);		
	}
	
	public static void main(String[] args) {
		Md5Util.encode("123456");
		Md5Util.encode("1");
		Md5Util.encode("1");
	}
}
