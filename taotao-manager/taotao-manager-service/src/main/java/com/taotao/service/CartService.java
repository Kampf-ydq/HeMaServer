package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbCart;

public interface CartService {

	TaotaoResult addCart(TbCart cart); //添加商品到购物车
	void deleteCart(TbCart delCart); //从购物车移除商品
	void updateCart(TbCart upCart); //修改商品数量
	
	TaotaoResult getCart(String username); //根据用户名取出记录
}
