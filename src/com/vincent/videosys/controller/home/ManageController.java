package com.vincent.videosys.controller.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.internal.logging.Logger;

@RequestMapping("/manage")
@Controller
public class ManageController {
		
	private static final String TAG = "ManageController: ";
	
	private static final Logger LOGGER = Logger.getLogger(ManageController.class);
	
	@RequestMapping("index")
	public ModelAndView index(HttpSession httpSession){
		ModelAndView mav = new ModelAndView();
		Object name = httpSession.getAttribute("username");
		if(name == null){
			mav.setViewName("redirect:/user/login");
			LOGGER.info(TAG+"name="+name+", not exists in session");
		}
		else{
			mav.setViewName("manage/index");
			LOGGER.info(TAG+"name="+name+", exists in session");
		}
		return mav;
	}
}
