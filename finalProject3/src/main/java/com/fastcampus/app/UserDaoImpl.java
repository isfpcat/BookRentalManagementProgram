package com.fastcampus.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    DataSource ds;
    final int FAIL = 0;

	@Override
	public int getUserCount() throws SQLException{
    	String sql = "select count(*) from member_tbl";
    	
        try(
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
            ){
                rs.next();
                int result = rs.getInt(1);

                return result;
            }
    }
	
    @Override
    public int deleteUser(String id) throws SQLException {
        int rowCnt = 0;
        String sql = "DELETE FROM member_tbl WHERE cust_no= ? ";

        try (  // try-with-resources - since jdk7
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1, id);
            return pstmt.executeUpdate(); //  insert, delete, update
//      } catch (Exception e) {
//          e.printStackTrace();
//          throw e;
        }
    }
	
    @Override
    public int insertUser(User user) throws SQLException{
        String sql = "INSERT INTO member_tbl VALUES (?,?,?,?,?,?) ";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPhone());
        pstmt.setTimestamp(4, new java.sql.Timestamp(user.getJoinDate().getTime()));
        pstmt.setString(5, user.getEmail());
        pstmt.setString(6, user.getGrade());

        return pstmt.executeUpdate();
    }
    
    @Override
    public User selectUser(String id) throws Exception {
        User user = null;
        String sql = "SELECT * FROM member_tbl WHERE cust_no = ? ";
       
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPhone(rs.getString(3));
            user.setJoinDate(new Date(rs.getTimestamp(4).getTime()));
            user.setEmail(rs.getString(5));
            user.setGrade(rs.getString(6));
        }

        return user;
    }
    
    public List<User> selectAllUser() throws Exception {
        List<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM member_tbl";
       
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setPhone(rs.getString(3));
            user.setJoinDate(new Date(rs.getTimestamp(4).getTime()));
            user.setEmail(rs.getString(5));
            user.setGrade(rs.getString(6));
            userList.add(user);
        }

        return userList;
    }
    
    public int updateUser(User user) throws SQLException {
    	String sql = "UPDATE member_tbl " +
                "SET cust_name=?, phone=?, join_date =?, cust_email=?, grade=? " +
                "WHERE cust_no = ? ";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getPhone());
        pstmt.setTimestamp(3, new java.sql.Timestamp(user.getJoinDate().getTime()));
        pstmt.setString(4, user.getEmail());
        pstmt.setString(5, user.getGrade());
        pstmt.setInt(6, user.getId());

        return pstmt.executeUpdate();
    }
    
    public List<User> selectUserTotalLoans() throws SQLException {
    	List<User> userList = new ArrayList<User>();
    	String sql = "SELECT cust_no," + 
    				 "	   	 cust_name," + 
    				 "       grade," + 
    				 "       SUM(rent_price) AS total_price" + 
    				 " FROM" + 
    				 "	(SELECT member_tbl.cust_no AS cust_no, " + 
    				 " 		  member_tbl.cust_name AS cust_name," + 
    			     "        member_tbl.grade AS grade," + 
    				 "        rent_tbl.rent_price AS rent_price" + 
    				 "   FROM member_tbl" + 
    				 "   INNER JOIN rent_tbl" + 
    				 "   ON member_tbl.cust_no = rent_tbl.cust_no) AS midTable" + 
    				 "	GROUP BY cust_no";
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            User user = new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setGrade(rs.getString(3));
            user.setLoans(rs.getInt(4));
            userList.add(user);
        }

        return userList;
    }
	
}