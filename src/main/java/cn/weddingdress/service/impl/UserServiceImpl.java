package cn.weddingdress.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.weddingdress.mapper.RoleMapper;
import cn.weddingdress.mapper.UserMapper;
import cn.weddingdress.mapper.UserRoleMapper;
import cn.weddingdress.model.SysUser;
import cn.weddingdress.model.SysUserRole;
import cn.weddingdress.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser SelectUserByUsername(String username) {
        return userMapper.SelectUserByUsername(username);
    }

    @Override
    public SysUser selectUserById(String id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public PageInfo<SysUser> selectUserList(SysUser sysUser, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (sysUser != null) {
            if (!StrUtil.hasEmpty(sysUser.getUsername())) {
                map.put("username", sysUser.getUsername());
            }
            if (!StrUtil.hasEmpty(sysUser.getPhone())) {
                map.put("phone", sysUser.getPhone());
            }
            if (!StrUtil.hasEmpty(sysUser.getName())) {
                map.put("name", sysUser.getName());
            }
        }
        PageHelper.startPage(page,limit);
        List<SysUser> sysUserList = userMapper.selectUserList(map);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

    @Override
    public int insertUser(SysUser user) {
        String userId = IdUtil.simpleUUID();
        user.setId(userId);
        user.setIsDel(0);
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode("123456"));
        String roleId = roleMapper.selectRoleIdByRoleType(user.getSysRole().getRoleType());
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(IdUtil.simpleUUID());
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setIsDel(0);
        userRoleMapper.insertUserRole(sysUserRole);
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(SysUser user) {
        String roleId = roleMapper.selectRoleIdByRoleType(user.getSysRole().getRoleType());
        SysUserRole sysUserRole= new SysUserRole();
        sysUserRole.setUserId(user.getId());
        sysUserRole.setRoleId(roleId);
        userRoleMapper.updateRoleIdByUserId(sysUserRole);
        user.setUpdateTime(new Date());
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserById(String id) {
        userRoleMapper.deleteUserRoleByUserId(id);
        return userMapper.deleteUserById(id);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        userMapper.deleteByIds(ids);
        userRoleMapper.deleteByUserIds(ids);
    }
}
