package cn.weddingdress.service;

import cn.weddingdress.model.SysRole;

public interface RoleService {
    SysRole selectPermissionCodeById(String id);
}
