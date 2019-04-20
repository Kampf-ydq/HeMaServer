package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.service.UserService;

/*
 * 用户管理Service
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	
	@Override
	public TbUser CheckUserByUsernameAndPwd(TbUser user) {
	    TbUserExample example = new TbUserExample();
	    Criteria criteria = example.createCriteria();
	    criteria.andUsernameEqualTo(user.getUsername());
	    List<TbUser> list = userMapper.selectByExample(example);
	    if (list != null && list.size() > 0) {
			TbUser dbUser = list.get(0);
			return dbUser;
		}
		return null;
	}

	@Override
	public void addUser(TbUser user) {
		//添加新用户
		userMapper.insert(user);
	}

	@Override
	public TbUser updateNick(TbUser user) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andUsernameEqualTo(user.getUsername());
		
		//取出该用户的记录
		List<TbUser> list = userMapper.selectByExample(example);
		
		//更改昵称
		TbUser dbUser = list.get(0);
		dbUser.setNickname(user.getNickname());
		//执行更新操作
		userMapper.updateByPrimaryKey(dbUser);
		
		//隐藏密码
		dbUser.setPassword(null);
		return dbUser;
	}

	@Override
	public TbUser updateAddress(TbUser user) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andUsernameEqualTo(user.getUsername());
		
		//取出该用户的记录
		List<TbUser> list = userMapper.selectByExample(example);
		
		//更改地址
		TbUser dbUser = list.get(0);
		dbUser.setAddress(user.getAddress());
		dbUser.setName(user.getName());
		dbUser.setPhone(user.getPhone());
		
		//执行更新操作
		userMapper.updateByExample(dbUser, example);
		
		//隐藏密码
		dbUser.setPassword(null);
		return dbUser;
	}

	@Override
	public TbUser selectUserByUsername(String username) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		return list.get(0);
	}

}
