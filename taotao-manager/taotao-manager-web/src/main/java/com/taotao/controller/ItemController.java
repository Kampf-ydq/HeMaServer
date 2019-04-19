package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemService;

/*
 * 商品管理Controller
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item")
	@ResponseBody
	public TaotaoResult getAllItems(){
		return itemService.getAllItems();
	}
	
}
