package com.fastcampus.app;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

	int getUserCount() throws SQLException;
	int deleteUser(String id) throws SQLException;
	int insertUser(User user) throws SQLException;
	User selectUser(String id) throws Exception;
	List<User> selectAllUser() throws Exception;
	int updateUser(User user) throws SQLException;
	List<User> selectUserTotalLoans() throws SQLException;
}