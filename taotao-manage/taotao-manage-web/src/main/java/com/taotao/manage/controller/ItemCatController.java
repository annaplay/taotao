package com.taotao.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;

@Controller
@RequestMapping(value = "item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ItemCat>> queryItemListByParentId(
            @RequestParam(value = "id", defaultValue = "0") Long pid) {
        try {
            ItemCat record = new ItemCat();
            record.setParentId(pid);
            List<ItemCat> list = this.itemCatService.queryListByWhere(record);
            if (null == list || list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    @RequestMapping(value = "{cid}", method = RequestMethod.GET)
    public ResponseEntity<ItemCat> queryById(@PathVariable(value = "cid") Long cid){
        try {
            ItemCat itemCat = this.itemCatService.queryById(cid);
            if(null == itemCat){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);         
            }
            return ResponseEntity.ok(itemCat);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}