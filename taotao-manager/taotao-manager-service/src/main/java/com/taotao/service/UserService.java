package com.taotao.service;

import com.taotao.pojo.TbUser;

public interface UserService {

	TbUser CheckUserByUsernameAndPwd(TbUser user);
	TbUser selectUserByUsername(String username);
	void addUser(TbUser user);
	TbUser updateNick(TbUser user);
	TbUser updateAddress(TbUser user);
}
