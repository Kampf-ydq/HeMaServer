package com.taotao.service;

import com.taotao.pojo.TbCart;

public interface CartService {

	void addCart(TbCart cart); //添加商品到购物车
	void deleteCart(TbCart delCart); //从购物车移除商品
	void updateCart(TbCart upCart); //修改商品数量
}
