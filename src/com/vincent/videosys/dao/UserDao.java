package com.vincent.videosys.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sun.istack.internal.logging.Logger;
import com.vincent.videosys.model.User;

@Repository("userDao")
public class UserDao extends BaseDao{
	private static final String TAG = "UserDao: ";
	
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);
	
	protected SessionFactory sessionFactory; 	
	
	/**
	 * �鿴���û��������ݿ����Ƿ����
	 * ���ڷ���true
	 * �����ڷ���false
	 * Ĭ�Ϸ���true
	 * @param username
	 * @return
	 */
	public boolean usernameExist(String username){
		//existΪtrueʱ�����������û������ڣ����򲻴���
		boolean exist = true;
		
		String hqlString = "from User where username = :username";
		Session session = super.getSession(); 
		Query query = session.createQuery(hqlString);
		query.setParameter("username", username);
		List<User> list = query.list();
		if(list.size() == 0) {
			exist = false;
		}
		
		return exist;
	}
	
	/**
	 * ����û�����ĵ�½��Ϣ�Ƿ���ȷ
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User checkLogin(String name, String pwd){
		User loginUser = null;
		String hqlString = "from User where username = :username";
		try{
			Session session = super.getSession(); 
			Query query = session.createQuery(hqlString);
			query.setParameter("username", name);
			List<User> list = query.list();
			if(list.size() > 0) {
				//ע�⣬������==�Ƚϣ���Ϊ��Ƚϵ�������String����ָ����ڴ��ַ�Ƿ����
				if(pwd.equals(list.get(0).getPassword())){
					loginUser = list.get(0);
					LOGGER.info(TAG+"login successful");
				}
				else{
					LOGGER.info(TAG+"wrong password");
				}
			}else{
				LOGGER.info(TAG+"wrong username");
			}
		}catch(Exception e){
			LOGGER.info(TAG+"check login failed, "+e);
		}	
		return loginUser;
	}
	
	/**
	 * ����û�
	 * @param user
	 * @return
	 */
	public int addUser(User user){	
		try{
			Session session = super.getSession();
			int rstObject = (int) session.save(user);
			LOGGER.info(TAG+"add successful");
			return rstObject;
		}catch(Exception e){
			LOGGER.info(TAG+"save failed, "+e);
			return 0;
		}
	}
	
	/**
	 * ����user��Ϣ
	 * @param user
	 */
	public void  updateUser(User user) {
		try{
			Session session = super.getSession();
			session.update(user);
			session.flush();
			LOGGER.info(TAG+"update successful");		
		}catch(Exception e){
			LOGGER.info(TAG+"update failed, "+e);
		}
	}
	
	/**
	 * ����idɾ��user
	 * @param id
	 * @return
	 */
	public int deleteUserById(int id){
		int rst = 0;
		String hqlString = "delete from User where id = :uid";
		try{
			Session session = super.getSession();
			rst = session.createQuery(hqlString).setParameter("uid", id).executeUpdate();
			LOGGER.info(TAG+"delete " + rst + " row data successful");
			
		}catch(Exception e){
			LOGGER.info(TAG+"delete failed, "+e);
		}
		return rst;
	}
	
	/**
	 * ����id��ȡuser
	 * @param id
	 * @return
	 */
	public User getUserById(int id){
		User user = null;
		String hqlString = "from User where id = :id";
		try{
			Session session = super.getSession(); 
			Query query = session.createQuery(hqlString);
			query.setParameter("id", id);
			List<User> list = query.list();
			if(list.size() > 0) {
				user = list.get(0);
			}
		}catch(Exception e){
			LOGGER.info(TAG+"get user by id failed, "+e);
		}	
		return user;
	}
	
	/**
	 * ��ȡ���ݿ��е�����user
	 * @return
	 */
	public List<User> getAllUsers(){
		List<User> list = null;
		
		String hqlString = "from User";
		try{
			Session session = super.getSession();
			Query query = session.createQuery(hqlString);
			list = query.list();
			LOGGER.info(TAG+"get " + list.size() + " users information");
		}catch(Exception e){
			LOGGER.info(TAG+"get users information failed, "+e);
		}
		
		return list;
	}
}
