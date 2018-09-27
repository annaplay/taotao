package com.taotao.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.bean.QueryResult;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.pojo.ItemParamItem;

@Service
public class ItemService extends BaseService<Item> {

    @Autowired
    private ItemDescService itemDesecSevice;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemParamItemService itemParamItemService;
    /**
     * 连续两次向数据库插入，存在事务问题 而事务是基于xml配置在service层 所以将两次操作放于一个service 对ItemDesc 来说 Item是主表 所以在此运行
     * 可以查看日志查看事务运行。
     * 
     * @param item
     * @param desc
     */
    public Boolean saveItem(Item item, String desc, String itemParams) {
        // 将商品类中不经常修改而且数据比较大的详情抽取出来 初始化
        item.setStatus(1);// 设置状态为正常
        item.setId(null);// 防止恶意代码注入
        Integer count1 = super.save(item);

        // 初始化 商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());// 初始化 商品id
        itemDesc.setItemDesc(desc);// 注入商品详情
        Integer count2 = this.itemDesecSevice.save(itemDesc);
        
        //初始化 商品规格
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        Integer count3 = this.itemParamItemService.save(itemParamItem);
        
        return count1.intValue() == 1 && count2.intValue() == 1 && count3.intValue() == 1;
    }
    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    public QueryResult queryItemList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        
        Example example = new Example(Item.class);
        example.setOrderByClause("created DESC");
        List<Item> items = this.itemMapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<Item>(items);
        return new QueryResult(pageInfo.getTotal(), pageInfo.getList());
    }
    
    /**
     * 
     * 通过主键 排空 编辑商品
     * @param item
     * @param desc
     * @return
     */
    public Boolean editItem(Item item, String desc, String itemParams) {
        Integer count1 = super.updateSelective(item);
        // 初始化 商品描述
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        Integer count2 = this.itemDesecSevice.updateSelective(itemDesc);
        
      //初始化 商品规格
        Integer count3 = this.itemParamItemService.updateItemParamItemByItemId(item.getId(), itemParams);
        
        return count1.intValue() == 1 && count2.intValue() == 1 && count3.intValue() == 1;
    }
}
