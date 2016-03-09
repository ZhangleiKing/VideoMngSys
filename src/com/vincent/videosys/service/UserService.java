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
	 * ����û�
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
	 * ��ȡ����user��Ϣ
	 * @return
	 */
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	/**
	 * ����id��ȡuser
	 * @param id
	 * @return
	 */
	public User getUserByIdService(Integer id){
		int uid = id.intValue();
		return userDao.getUserById(uid);
	}
	
	/**
	 * ����idɾ��user
	 * @param id
	 * @return
	 */
	public int deleteUserByIdService(String id){
		int uid = Integer.parseInt(id);
		return userDao.deleteUserById(uid);
	}
	
	/**
	 * ����user��Ϣ
	 * @param user
	 */
	public void updateUserService(User user){
		userDao.updateUser(user);
	}
}
