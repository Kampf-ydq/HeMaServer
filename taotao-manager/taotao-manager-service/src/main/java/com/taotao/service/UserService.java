package com.taotao.service;

import com.taotao.pojo.TbUser;

public interface UserService {

	TbUser CheckUserByUsernameAndPwd(TbUser user);
	void addUser(TbUser user);
	void updateNick(TbUser user);
	TbUser updateAddress(TbUser user);
}
