package cn.weddingdress.service;

import cn.weddingdress.model.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    SysUser SelectUserByUsername(String username);

    SysUser selectUserById(String id);

    PageInfo<SysUser> selectUserList(SysUser sysUser, Integer page, Integer limit);

    int insertUser(SysUser sysUser);

    int updateUser(SysUser sysUser);

    int deleteUserById(String id);

    void deleteByIds(List<String> ids);
}
