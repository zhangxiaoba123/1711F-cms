package com.zxy.cms.util;
/**
 * 
 * @ClassName: CMSException 
 * @Description:  自定义异常
 * @author: admin
 * @date: 2020年3月12日 下午3:32:07
 */

public class CMSException extends RuntimeException{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;//异常消息
	public CMSException() {
		
	}
     public CMSException(String message) {
    	 super(message);
    	 this.message= message;
    	 
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

     
}
