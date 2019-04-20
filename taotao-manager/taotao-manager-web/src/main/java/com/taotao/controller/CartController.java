package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.result.ReturnType;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbCart;
import com.taotao.service.CartService;

/*
 * 购物车管理Controller
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("/addCart")
	@ResponseBody
	public TaotaoResult addCart(@RequestBody TbCart cart){
		if (cart != null) {
			return TaotaoResult.ok(cartService.addCart(cart));
		}
		return TaotaoResult.ok(ReturnType.ERROR);
	}
	
	@RequestMapping("/showCart/{username}")
	@ResponseBody
	public TaotaoResult showCart(@PathVariable String username){
		if (username != null) {
			return TaotaoResult.ok(cartService.getCart(username));
		}
		return TaotaoResult.ok(ReturnType.ERROR);
	}
	
	@RequestMapping("/deleteCart")
	@ResponseBody
	public TaotaoResult delCart(@RequestBody TbCart cart){
		if (cart != null) {
			cartService.deleteCart(cart);
			return TaotaoResult.ok(ReturnType.SUCCESS);
		}
		return TaotaoResult.ok(ReturnType.ERROR);
	}
	
	@RequestMapping("/updateCart")
	@ResponseBody
	public TaotaoResult updateCart(@RequestBody TbCart cart){
		if (cart != null) {
			cartService.updateCart(cart);
			return TaotaoResult.ok(ReturnType.SUCCESS);
		}
		return TaotaoResult.ok(ReturnType.ERROR);
	}
}
