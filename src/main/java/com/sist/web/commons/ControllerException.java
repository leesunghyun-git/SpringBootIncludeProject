package com.sist.web.commons;

import com.sist.web.controller.MainController;
import org.springframework.web.bind.annotation.ControllerAdvice;
// @RestController는 처리할 수 없다
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// 모든 요청기능 처리 => catch사용
public class ControllerException {
	/*
	 * 				Object
	 * 				  |
	 * 				Throwable
	 * 				  |
	 * 		----------------------------
	 * 		|						   |
	 * 	  Error						Exception
	 * 
	 * 
	 * 
	 */
    private final MainController mainController;

    ControllerException(MainController mainController) {
        this.mainController = mainController;
    }
	@ExceptionHandler(Exception.class)
	public void excetpion(Exception ex)
	{
		System.out.println("=========== Controller에서 예외 발생 ================");
		ex.printStackTrace();
		System.out.println("==================================================");
	}
	
	@ExceptionHandler(Throwable.class)
	public void throwable(Throwable ex)
	{
		System.out.println("=========== Controller에서 에러 발생 ================");
		ex.printStackTrace();
		System.out.println("==================================================");
	}
}
