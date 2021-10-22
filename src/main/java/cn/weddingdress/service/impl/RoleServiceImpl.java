package cn.weddingdress.service.impl;

import cn.weddingdress.mapper.RoleMapper;
import cn.weddingdress.model.SysRole;
import cn.weddingdress.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public SysRole selectPermissionCodeById(String id) {
        return roleMapper.selectPermissionCodeById(id);
    }
}
