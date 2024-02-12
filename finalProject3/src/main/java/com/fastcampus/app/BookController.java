package com.fastcampus.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {
	@Autowired
	BookDao bookDao;
	
	@RequestMapping(value = "/rentList", method = RequestMethod.GET)
	public String getRentList(Model model) {
		try {
			List<Book> list = bookDao.selectAllBooks();
			model.addAttribute("list", list);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "bookList";
	}	
}
