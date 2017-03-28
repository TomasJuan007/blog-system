package com.ykse.blogs.exception;

/**
 * 参数异常
 * 
 * @author huangtao
 * @version $Id: ParameterException.java, v 0.1 2016年11月17日 下午8:30:40 huangtao Exp $
 */
public class ParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message,  cause);
    } 

}
