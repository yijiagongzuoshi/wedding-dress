package cn.weddingdress.mapper;

import cn.weddingdress.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {

    SysRole selectPermissionCodeById(String id);

    String selectRoleIdByRoleType(Integer roleType);
}
