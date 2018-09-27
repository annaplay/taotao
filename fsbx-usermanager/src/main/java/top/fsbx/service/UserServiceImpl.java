package top.fsbx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.fsbx.bean.QueryResult;
import top.fsbx.mapper.UserMapper;
import top.fsbx.pojo.User;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 通过Id查询用户
     */
    @Override
    public QueryResult queryUserList(Integer page, Integer rows) {
        //设置分页参数
        PageHelper.startPage(page, rows);
        //查询User数据
        Example example = new Example(User.class);
        example.setOrderByClause("updated DESC");
        List<User> list = this.userMapper.selectByExample(example);
        //获取分页后的信息
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return new QueryResult(pageInfo.getTotal(),pageInfo.getList());
    }
    @Override
    public User queryUserById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }
    /**
     * 新增用户
     */
    @Override
    public Boolean saveUser(User user) {
       return this.userMapper.insert(user) == 1;
    }
    /**
     * 更新用户
     */
    @Override
    public Boolean updateUser(User user) {
        return this.userMapper.updateByPrimaryKeySelective(user) == 1;
    }
    /**
     * 删除用户
     */
    @Override
    public Boolean deleteUser(Long id) {
        return this.userMapper.deleteByPrimaryKey(id) == 1;
    }

}
