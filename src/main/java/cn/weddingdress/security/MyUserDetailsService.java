package cn.weddingdress.security;

import cn.weddingdress.model.SysPermission;
import cn.weddingdress.model.SysRole;
import cn.weddingdress.model.SysUser;
import cn.weddingdress.service.RoleService;
import cn.weddingdress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("执行登录");
        //根据username查询数据库
        SysUser sysUser = userService.SelectUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(String.format("%s.用户不存在", username));
        } else {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            SysRole sysRole = sysUser.getSysRole();
            authorities.add(new SimpleGrantedAuthority(sysRole.getRoleEnName()));
            SysRole rolePermission = roleService.selectPermissionCodeById(sysRole.getId());
            for (SysPermission sysPermission : rolePermission.getSysPermissionList()) {
                authorities.add(new SimpleGrantedAuthority(sysPermission.getPermissionCode()));
            }
            //返回用户对象
            return new SysUser(sysUser.getId(),sysUser.getUsername(), sysUser.getPassword(),sysUser.getSysRole(), authorities);
        }
    }
}
