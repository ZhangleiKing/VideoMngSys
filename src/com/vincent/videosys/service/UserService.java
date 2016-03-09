package com.vincent.videosys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vincent.videosys.dao.UserDao;
import com.vincent.videosys.model.User;

@Transactional
@Service("userService")
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean usernameExistService(String username){
		boolean exist = userDao.usernameExist(username);
		return exist;
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUserService(User user){
		return userDao.addUser(user);
	}
	
	public User checkLoginService(String name, String pwd){
		return userDao.checkLogin(name, pwd);
	}
	
	/**
	 * 获取所有user信息
	 * @return
	 */
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	/**
	 * 根据id获取user
	 * @param id
	 * @return
	 */
	public User getUserByIdService(Integer id){
		int uid = id.intValue();
		return userDao.getUserById(uid);
	}
	
	/**
	 * 根据id删除user
	 * @param id
	 * @return
	 */
	public int deleteUserByIdService(String id){
		int uid = Integer.parseInt(id);
		return userDao.deleteUserById(uid);
	}
	
	/**
	 * 更新user信息
	 * @param user
	 */
	public void updateUserService(User user){
		userDao.updateUser(user);
	}
}
