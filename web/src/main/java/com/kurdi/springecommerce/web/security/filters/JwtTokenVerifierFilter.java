package com.kurdi.springecommerce.web.security.filters;

import com.google.common.base.Strings;
import com.kurdi.security.Roles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (Strings.isNullOrEmpty(authorizationHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        //token verification logic
        String userId = "";
        try {
            String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
            Key key = new SecretKeySpec(Base64.getDecoder().decode(secret),

                    SignatureAlgorithm.HS256.getJcaName());
            Claims jwtClaims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authorizationHeader).getBody();
            userId = jwtClaims.getId();

        } catch (JwtException e) {
            //don't trust the JWT!
            filterChain.doFilter(request, response);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Roles.ADMIN.getRole()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userId,
                null,
                authorities
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);


    }

}
