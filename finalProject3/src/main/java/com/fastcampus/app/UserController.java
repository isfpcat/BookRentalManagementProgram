package com.fastcampus.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@Controller
public class UserController {
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		
		int userCount = 0;
		try {
			userCount = userDao.getUserCount();
		} catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userCount", userCount+1);
		
		return "index";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(User user, Model model) {
		try {
			userDao.insertUser(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		try {
			List<User> list = userDao.selectAllUser();
			model.addAttribute("list", list);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "userList";
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getUserInfo(String id, Model model) {
		try {
			User user = userDao.selectUser(id);
			model.addAttribute("user", user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUserInfo(User user, Model model) {
		try {
			userDao.updateUser(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/billing", method = RequestMethod.GET)
	public String gettotalLoans(Model model) {
		try {
			List<User> user = userDao.selectUserTotalLoans();
			model.addAttribute("list", user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "userTotalLoansList";
	}
	
}
