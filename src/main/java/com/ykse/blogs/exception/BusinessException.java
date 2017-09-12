package com.ykse.blogs.exception;

/**
 * 业务逻辑异常
 * 
 * @author huangtao
 * @version $Id: BusinessException.java, v 0.1 2016年11月17日 下午8:29:33 huangtao Exp $
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message,  cause);
    }    

}
