package com.fastcampus.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BookDaoImplTest {

    @Autowired
    DataSource ds;
    
	@Autowired
	BookDao bookDao;
	
	@Test
    public void jdbcConnectionTest() throws Exception {	    
    	Connection conn = ds.getConnection();
    	assertThat(conn).isNotNull();
    }

	@Test
	public void testDeleteAllBooks() throws Exception {
		bookDao.deleteAllBooks();
		assertThat(bookDao.selectAllBooks().size() == 0).isTrue();
	}
	
	@Test
	public void testInsertBook() throws Exception {
		Book book = new Book(1, 1, "A001", 3000, new Date());
    	bookDao.insertBook(book);
    	assertThat(bookDao.selectAllBooks().size() == 1).isTrue();
    }
	
	@Test
	public void testSelectAllBooks() throws Exception {	    
    	List<Book> books = bookDao.selectAllBooks();
    	assertThat(books.size() == 1).isTrue();
    }
	
	@Test
	public void testSelectAllBooksThrowSQLException() {
		assertThatThrownBy(() -> {throw new SQLException("Exception test");})
		.isInstanceOf(SQLException.class)
		.hasMessageContaining("Exception test");
	}
	
	@Test
	public void testUpdateBook() throws Exception {	   
		Book book = new Book(1, 1, "A002", 3000, new Date());
    	bookDao.updateBook(book);
    	assertThat("A002".equals(bookDao.selectBook("1").getCode())).isTrue();
    }
}
