package com.example.examenprincipalewaelboussoffara.utils;


import com.example.examenprincipalewaelboussoffara.security.JwtConfig;
import com.example.examenprincipalewaelboussoffara.security.JwtUtils;
import com.example.examenprincipalewaelboussoffara.dao.AuthRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtAuthentificationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthRepository authRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);
        String userName = JwtUtils.extractUsername(token,jwtConfig.getSigningKey());
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = authRepository.findByUserName(userName).get();
            if (JwtUtils.isTokenExpired(token, jwtConfig.getSigningKey())) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
