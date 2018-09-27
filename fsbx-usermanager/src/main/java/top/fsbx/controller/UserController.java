package top.fsbx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import top.fsbx.bean.QueryResult;
import top.fsbx.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="list",method=RequestMethod.GET)
    @ResponseBody
    public QueryResult querUserList(Integer page,Integer rows){
        QueryResult queryResult = userService.queryUserList(page, rows);
        return queryResult;
    }
}
