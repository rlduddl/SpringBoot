package com.example.demo.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.demo.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// ?쇰컲?곸씤 MVC 而⑦듃濡ㅻ윭?먯꽌 諛쒖깮?섎뒗 ?덉쇅瑜?泥섎━?섎ŉ, 酉곕? 諛섑솚?섎뒗 諛⑹떇?대떎. (JSP, Thyeleaf ?깅벑)
@ControllerAdvice
public class GlobalExceptionHandler {
	
	

	/**
	 * 404 ?먮윭
	 * @param ex
	 * @param model
	 * @return
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handle404(NoHandlerFoundException ex, Model model) {
		// ?띿꽦, html 寃쎈줈
		model.addAttribute("message", "?섏씠吏瑜?李얠쓣 ???놁뒿?덈떎.");
		return "error/404";
	}
	
	/**
	 * 500 ?먮윭
	 * @param ex
	 * @param model
	 * @return
	 */
	//@ExceptionHandler(Exception.class)
	//public String handle500(Exception ex, Model model) {
		// ?띿꽦, html 寃쎈줈
	//	model.addAttribute("message", "Internal Server Error");
	//	return "error/500";
	//}
	
	
	
}
