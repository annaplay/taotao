package top.fsbx.mapper;

import top.fsbx.pojo.User;

import com.github.abel533.mapper.Mapper;
/**
 * 通用mapper只适用于单表查询，多表查询需要mapper.xml文件
 * @author BoBo
 *
 */
public interface UserMapper extends Mapper<User>{
    
}
