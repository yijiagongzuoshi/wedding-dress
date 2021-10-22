package cn.weddingdress.mapper;

import cn.weddingdress.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {

    SysUser SelectUserByUsername(String username);

    SysUser selectUserById(String id);

    List<SysUser> selectUserList(Map<String, Object> map);

    int insertUser(SysUser user);

    int updateUser(SysUser user);

    int deleteUserById(String id);

    void deleteByIds(@Param("ids") List<String> ids);
}
