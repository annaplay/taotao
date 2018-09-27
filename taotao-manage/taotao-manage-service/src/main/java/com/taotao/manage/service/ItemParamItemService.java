package com.taotao.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.taotao.manage.mapper.ItemParamItemMapper;
import com.taotao.manage.pojo.ItemParamItem;
@Service
public class ItemParamItemService extends BaseService<ItemParamItem>{
    
    @Autowired
    private ItemParamItemMapper itemParamItemMapper;
    public Integer updateItemParamItemByItemId(Long id, String itemParams) {
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(id);
        itemParamItem.setParamData(itemParams);
        
        Example example = new Example(ItemParamItem.class);
        example.createCriteria().andEqualTo("itemId", id);
        return this.itemParamItemMapper.updateByExampleSelective(itemParamItem, example);
    }

}
