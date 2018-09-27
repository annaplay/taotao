package top.fsbx.service;

import top.fsbx.bean.QueryResult;
import top.fsbx.pojo.User;

public interface UserService {
    public QueryResult queryUserList(Integer page,Integer rows);

    public User queryUserById(Long id);

    public Boolean saveUser(User user);

    public Boolean updateUser(User user);

    public Boolean deleteUser(Long id);
}
