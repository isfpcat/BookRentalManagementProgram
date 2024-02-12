package com.fastcampus.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    DataSource ds;
    
	@Autowired
	UserDao userDao;
	
	@Autowired
	BookDao bookDao;
	
	User user = new User(1, "test", "010-1234-5678", "email@naver.com", "P");
	
	@Test
    public void jdbcConnectionTest() throws Exception {	    
    	Connection conn = ds.getConnection();
    	assertThat(conn).isNotNull();
    }

	@Test
	public void testInsertUser() throws Exception{
		userDao.deleteUser("1");
		assertThat(userDao.insertUser(user)>0).isTrue();
	}
	
	@Test
	public void testGetUserAccountSuccess() throws SQLException {
		assertThat(userDao.getUserCount()).isNotEqualTo(0);
	}
	
	@Test
	public void testGetUserAccountThrowSQLAssertion() {
		assertThatThrownBy(() -> {throw new SQLException("Exception test");})
		.isInstanceOf(SQLException.class)
		.hasMessageContaining("Exception test");
	}
	
	@Test
	public void selectUserSuccess() throws Exception{
		User insertedUser = userDao.selectUser("1");
		assertThat(insertedUser.getId()).isEqualTo(user.getId());
	}
	
	@Test
	public void selectUserNotFound() throws Exception{
		User insertedUser = userDao.selectUser("99999999");
		assertThat(insertedUser).isEqualTo(null);
	}
	
	@Test
	public void selectUserWithInvalidIdNoFail() throws Exception{
		assertThat(userDao.selectUser("invalid")).isEqualTo(new Exception());
	}
}
