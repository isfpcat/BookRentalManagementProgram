package com.fastcampus.app;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {

	List<Book> selectAllBooks() throws SQLException;;
	int insertBook(Book book) throws SQLException;
	int deleteBook(String id) throws SQLException;
	int deleteAllBooks() throws SQLException;
	Book selectBook(String id) throws SQLException;
	int updateBook(Book book) throws SQLException;
}