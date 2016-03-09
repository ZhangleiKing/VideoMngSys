package com.vincent.videosys.controller.home;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.deploy.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.internal.logging.Logger;
import com.vincent.videosys.dao.UserDao;
import com.vincent.videosys.model.User;
import com.vincent.videosys.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final String TAG = "UserController: ";
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@RequestMapping("/login")
	public ModelAndView loginPage(){
		ModelAndView mav = new ModelAndView("user/login");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/loginSubmit", method=RequestMethod.POST)
	public Map<String, Object> loginSubmit(@RequestParam("username")String name, @RequestParam("password")String pwd, 
			HttpSession httpSession){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User rstUser = userService.checkLoginService(name,pwd);
		if(rstUser != null){
			//save session attribute
			httpSession.setAttribute("username", rstUser.getUsername());
			httpSession.setAttribute("authority",rstUser.getAuthority());
			
			resultMap.put("status", 1);//success
		}else{
			resultMap.put("status", 0);//failed
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/usernameExist",method=RequestMethod.POST)
	public Map<String, String> usernameExist(@RequestParam("username")String usernameString
			){
		Map<String, String> resultMap = new HashMap<String, String>();
		if(userService.usernameExistService(usernameString)){
			resultMap.put("status", "1");//exist
		}
		else{
			resultMap.put("status", "0");//not exist
		}
		System.out.println(TAG+"userService.usernameExistService");
		return resultMap;
	}

	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public ModelAndView registPage(HttpSession httpSession){
		ModelAndView mav = new ModelAndView("user/register");
		
		//save token in session
		String tokenString = TokenProcessor.getInstance().makeToken();
		httpSession.setAttribute("token", tokenString);
		
		return mav;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public ModelAndView registSubmit(@RequestParam("token")String tokenString, User user,
			Map<String, Object> map, HttpSession httpSession){
		/*
		 * get token from session if it exists
		 */
		Object server_token = httpSession.getAttribute("token");
		if(tokenString.equals("")){
			map.put("registStatus", 2);
		}
		else if(server_token == null){
			map.put("registStatus", 2);
		}
		else{
			server_token = (String) server_token;
			if(!tokenString.equals(server_token)){
				map.put("registStatus", 2);
			}
			else{
				//the authority of new user is default 1 
				user.setAuthority(1);
				
				if(userService.addUserService(user) > 0){
					map.put("registStatus", 1);
				}else{
					map.put("registStatus", 0);
				}
				System.out.println(TAG+"userService.addUserService");
			}
		}
		httpSession.removeAttribute("token");
		ModelAndView mav = new ModelAndView("user/registResult");
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession httpSession) {
		httpSession.invalidate();
		ModelAndView mav = new ModelAndView("redirect:login");
		return mav;
	}
}
