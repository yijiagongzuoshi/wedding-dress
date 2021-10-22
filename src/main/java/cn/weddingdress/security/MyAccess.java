package cn.weddingdress.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyAccess {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
