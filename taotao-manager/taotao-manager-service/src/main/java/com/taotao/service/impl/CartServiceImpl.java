package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbCartMapper;
import com.taotao.pojo.TbCart;
import com.taotao.pojo.TbCartExample;
import com.taotao.pojo.TbCartExample.Criteria;
import com.taotao.service.CartService;

/*
 * 购物车管理Service
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private TbCartMapper cartMapper;
	
	@Override
	public void addCart(TbCart cart) {
		TbCart tbCart = new TbCart();
		//补全TbCart对象
		tbCart.setId(null);
		tbCart.setUsername(cart.getUsername());
		tbCart.setTitle(cart.getTitle());
		tbCart.setPrice(cart.getPrice());
		tbCart.setNum(cart.getNum());
		tbCart.setImage(cart.getImage());
		
		tbCart.setStatus((byte)0); //状态：0（未支付），1（已支付）
		
		//补全时间项
		Date date = new Date();
		tbCart.setCreated(date); //加入购物车的时间
		tbCart.setUpdated(date);
		
		cartMapper.insert(tbCart); //插入数据
	}

	@Override
	public void deleteCart(TbCart delCart) {
		TbCartExample example = new TbCartExample();
		Criteria criteria = example.createCriteria();
		
		//username和title唯一确定一条记录
		criteria.andUsernameEqualTo(delCart.getUsername());
		criteria.andTitleEqualTo(delCart.getTitle());
		
		//移除该商品
		cartMapper.deleteByExample(example);
		
	}

	@Override
	public void updateCart(TbCart upCart) {
		TbCartExample example = new TbCartExample();
		Criteria criteria = example.createCriteria();
		
		//username和title唯一确定一条记录
		criteria.andUsernameEqualTo(upCart.getUsername());
		criteria.andTitleEqualTo(upCart.getTitle());
		
		//选出该行
		List<TbCart> list = cartMapper.selectByExample(example);
		
		if (list == null && list.size() <= 0) {
			System.out.println("更新项不存在"); //不存在
		}
		
		//取出数据库记录
		TbCart dbCart = list.get(0);
		//修改更新项(数量、更新时间)
		Date date = new Date();
		dbCart.setNum(upCart.getNum());
		dbCart.setUpdated(date);
		
		//同步到数据库
		cartMapper.updateByExample(upCart, example);
	}

}
