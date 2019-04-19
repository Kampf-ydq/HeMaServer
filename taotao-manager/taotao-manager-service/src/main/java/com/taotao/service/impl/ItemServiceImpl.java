package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/*
 * 商品管理Service
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TaotaoResult getAllItems() {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		
		//取出所有数据
		criteria.andIdIsNotNull();
		
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list);
		}
		return TaotaoResult.ok();
	}

}
