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
public class BookDaoImpl implements BookDao {
    @Autowired
    DataSource ds;
    final int FAIL = 0;
    
    public List<Book> selectAllBooks() throws SQLException{
        List<Book> books = new ArrayList<Book>();
        String sql = "SELECT * FROM rent_tbl";
       
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Book book = new Book();
            book.setUserId(rs.getInt(1));
            book.setId(rs.getInt(2));
            book.setCode(rs.getString(3));
            book.setPrice(rs.getInt(4));
            book.setRentDate(new Date(rs.getTimestamp(5).getTime()));
            System.out.print("book = " + book);
            books.add(book);
        }

        return books;
    }

	@Override
	public int insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO rent_tbl VALUES (?,?,?,?,?) ";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setInt(1, book.getUserId());
        pstmt.setInt(2, book.getId());
        pstmt.setString(3, book.getCode());
        pstmt.setInt(4, book.getPrice());
        pstmt.setTimestamp(5, new java.sql.Timestamp(book.getRentDate().getTime()));
        
        return pstmt.executeUpdate();
	}
	
	@Override
	public int deleteAllBooks() throws SQLException {
        int rowCnt = 0;
        String sql = "TRUNCATE rent_tbl";

        try (  // try-with-resources - since jdk7
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            return pstmt.executeUpdate(); //  insert, delete, update
//      } catch (Exception e) {
//          e.printStackTrace();
//          throw e;
        }
	}

	@Override
	public int deleteBook(String id) throws SQLException {
        int rowCnt = 0;
        String sql = "DELETE FROM rent_tbl WHERE rent_no= ? ";

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
	public Book selectBook(String id) throws SQLException {
		Book book = null;
        int rowCnt = 0;
        String sql = "SELECT * FROM rent_tbl WHERE rent_no= ? ";

        try (  // try-with-resources - since jdk7
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                book = new Book();
                book.setUserId(rs.getInt(1));
                book.setId(rs.getInt(2));
                book.setCode(rs.getString(3));
                book.setPrice(rs.getInt(4));
                book.setRentDate(new Date(rs.getTimestamp(5).getTime()));
            }
//      } catch (Exception e) {
//          e.printStackTrace();
//          throw e;
        }
        
        return book;
	}

	@Override
	public int updateBook(Book book) throws SQLException {
    	String sql = "UPDATE rent_tbl " +
                "SET cust_no=?, book_code=?, rent_price=?, rent_date=? " +
                "WHERE rent_no = ? ";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, book.getUserId());
        pstmt.setString(2, book.getCode());
        pstmt.setInt(3, book.getPrice());
        pstmt.setTimestamp(4, new java.sql.Timestamp(book.getRentDate().getTime()));
        pstmt.setInt(5, book.getId());

        return pstmt.executeUpdate();
	}
}
