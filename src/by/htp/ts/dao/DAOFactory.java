package by.htp.ts.dao;

import by.htp.ts.dao.impl.UserDAOImpl;

public final class DAOFactory {
	private static final DAOFactory instance=new DAOFactory();
	
	private final UserDAO userDao=new UserDAOImpl();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO getUserDao() {
		return userDao;
	}
	

}
