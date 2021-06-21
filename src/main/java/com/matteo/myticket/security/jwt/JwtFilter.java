package com.matteo.myticket.security.jwt;

import com.matteo.myticket.model.User;
import com.matteo.myticket.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token == null || !token.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        // Verify Token
        System.out.println("Token: " + token);
        token = token.substring(7);

        try {
            if (!jwtProvider.validate(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Get user identity and set it on the spring security context
            User user = userService.findByUsername(jwtProvider.getUsername(token));

            System.out.println(user);

            UsernamePasswordAuthenticationToken
                    authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), null,
                    user.getAuthorities()
            );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {

            e.printStackTrace();
            //don't trust the JWT!
        }

        filterChain.doFilter(request,response);
    }
}
