package com.vincent.videosys.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.internal.logging.Logger;
import com.vincent.videosys.controller.home.HdfsController;
import com.vincent.videosys.model.User;
import com.vincent.videosys.service.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger LOGGER = Logger.getLogger(AdminController.class);
	
	/**
	 * show all the users' information
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView userList(Map<String, Object> map){
		ModelAndView mav = new ModelAndView("admin/list");	
		map.put("users", userService.getAllUsers());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public Map<String, Object> deleteUser(@RequestParam("id")String id){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		if(userService.deleteUserByIdService(id) > 0){
			rstMap.put("deleteStatus", 1);
			LOGGER.info("delete id="+id+" user successful");
		}
		else{
			rstMap.put("deleteStatus", 0);
			LOGGER.info("delete failed");
		}
		return rstMap;
	}
	
	/**
	 * the administrator add or revise user information
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ModelAndView userPage(Map<String, Object> map){
		ModelAndView mav = new ModelAndView("admin/edit");
		map.put("user", new User());
		return mav;
	}
	
	/**
	 * add new user
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ModelAndView saveUser(User user){
		ModelAndView mav = new ModelAndView("redirect:list");	
		if(userService.addUserService(user) > 0){
			LOGGER.info("save user successful");
		}
		else{
			LOGGER.info("save user failed");
		}
		return mav;
	}
	
	/**
	 * update user information
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/user", method=RequestMethod.PUT)
	public ModelAndView updateUser(User user){
		ModelAndView mav = new ModelAndView("redirect:list");
		userService.updateUserService(user);
		return mav;
	}
	
	/**
	 * edit user
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id")Integer id, Map<String, Object> map){
		ModelAndView mav = new ModelAndView("admin/edit");	
		map.put("user", userService.getUserByIdService(id));
		return mav;
	}
	
	@RequestMapping(value="/test", method=RequestMethod.DELETE)
	public ModelAndView test(){
		String srcName = "hdfs://192.168.103.128:9000/user/zhanglei/input/test.txt";
		try {
			HdfsController.deleteHdfsFile(srcName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("admin/test");
	}
	
	@RequestMapping(value="/testUpload", method=RequestMethod.POST)
	public ModelAndView testUpload(){
		String localSrc = "C:\\Users\\y410p\\Desktop\\Git.txt";
		String destinationSrc = "hdfs://192.168.103.128:9000/user/zhanglei/input/Git.txt";
		try {
			HdfsController.copyLocalFileToHdfs(localSrc,destinationSrc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("admin/test");
	}
	
	@RequestMapping(value="/testReceive", method=RequestMethod.POST)
	public ModelAndView testReceive(@RequestParam("file") MultipartFile uploadFile) 
			throws Exception{
		System.out.println("file name: "+uploadFile.getOriginalFilename());
		String dst = "hdfs://192.168.103.128:9000/user/zhanglei/input/Git.txt";
		try {
			InputStream in = uploadFile.getInputStream();
			HdfsController.copyLocalFileToHdfsByStream(in, dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ModelAndView("admin/test");
	}
	
}
