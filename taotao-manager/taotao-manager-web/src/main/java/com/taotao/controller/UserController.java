package com.taotao.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.service.UserService;
import com.taotao.commom.result.ReturnType;

/*
 * 用户管理Controller
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userLogin")
	@ResponseBody
	public TaotaoResult userLogin(@RequestBody TbUser user,HttpSession session){
		
		//判断前台提交数据是否有误
		if (user == null) {
			return TaotaoResult.ok(ReturnType.NO_RECIEVE_DATA);
		}
		
		TbUser dbUser = userService.CheckUserByUsernameAndPwd(user);
		if (dbUser == null) {  //该用户不存在
			return TaotaoResult.ok(ReturnType.NO_USER);
		}
		//登录成功
		if (dbUser.getUsername().equals(user.getUsername()) && dbUser.getPassword().equals(user.getPassword())) {
			//返回处理后的用户（隐藏密码）
			dbUser.setPassword(null);
			
			//设置sessionId，区分当前用户
			session.setAttribute("loginUser", dbUser);
			//System.out.println("\t\t======>>>>>loginsuccess:\tsessionid"+session.getId());
			return TaotaoResult.ok(dbUser); //返回用户所有信息
		}else {
			return TaotaoResult.ok(ReturnType.INVALID_PASSWORD); //密码错误
		}
	}
	
	@RequestMapping("/currUser")
	@ResponseBody
	public TaotaoResult currUser(HttpSession session){
		TbUser user = (TbUser) session.getAttribute("loginUser");
		return TaotaoResult.ok(user);
	}
	
	@RequestMapping("/logOut")
	@ResponseBody
	public TaotaoResult logOut(HttpSession session){
		session.removeAttribute("loginUser");
		return TaotaoResult.ok();
	}
	
	@RequestMapping("/selectUser/{username}")
	@ResponseBody
	public TaotaoResult selectUserByUsername(@PathVariable String username){
	    if (username != null) {
			TbUser user = userService.selectUserByUsername(username);
			return TaotaoResult.ok(user);
		}
		return TaotaoResult.ok();
	}
	
	
	@RequestMapping("/addUser")
	@ResponseBody
	public TaotaoResult addUser(@RequestBody TbUser user){
		//判断该号码是否注册
		TbUser isExitUser = userService.CheckUserByUsernameAndPwd(user);
		if (isExitUser != null) { //已注册
			return TaotaoResult.ok(ReturnType.IS_REG);
		}
		//提交数据正确且未注册
		if (user != null && isExitUser == null) {
			//补全User信息
			TbUser newUser = new TbUser();
			newUser.setId(null);
			newUser.setUsername(user.getUsername());
			newUser.setPassword(user.getPassword());
			
			//默认值
			newUser.setHeadpic("http://localhost:8085/user_image/kobe.jpg");
			newUser.setNickname("Mr/Miss");
			newUser.setAddress("***");
			newUser.setName("***");
			newUser.setPhone("***");
			
			//System.out.println("\t\t======>>>>>registsuccess:\tsessionid"+newUser.getHeadpic());
			
			//添加用户
			userService.addUser(newUser);
			return TaotaoResult.ok(ReturnType.SUCCESS);
		}
		return TaotaoResult.ok(ReturnType.ERROR);
	}
	
	@RequestMapping("/updateNick")
	@ResponseBody
	public TaotaoResult updateNick(@RequestBody TbUser user){
		if (user.getNickname() != null) {
			return TaotaoResult.ok(userService.updateNick(user));
		}
		return TaotaoResult.ok(ReturnType.ERROR);
	}
	
	@RequestMapping("/updateAddress")
	@ResponseBody
	public TaotaoResult updateAddress(@RequestBody TbUser user){
		if (user.getAddress() != null && user.getName() != null && user.getPhone() != null) {
			return TaotaoResult.ok(userService.updateAddress(user));
		}
		return TaotaoResult.ok(ReturnType.ERROR);
	}
	
}
