package com.api.API_Bancaria.security;

import com.api.API_Bancaria.services.CustomUserDetailsService;
import com.api.API_Bancaria.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final CustomUserDetailsService userDetailsService;

    public JWTFilter(JWTService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        System.out.println("Entrou no filtro");
        System.out.println("path" + req.getServletPath());
        String authheader = req.getHeader("Authorization");

        System.out.println("authheader: " + authheader);

        if (authheader == null || !authheader.startsWith("Bearer ")) {
            System.out.println("Token invalido");
            chain.doFilter(req, res);
            return;
        }

        String token = authheader.substring(7);
        System.out.println("token: " + token);

        String cpf = jwtService.extractUsername(token);
        System.out.println("cpf: " + cpf);

        UserDetails userDetails = userDetailsService.loadUserByUsername(cpf);
        System.out.println("userDetails: " + userDetails);

        if (SecurityContextHolder.getContext().getAuthentication() == null && jwtService.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);


        }

        chain.doFilter(req, res);
    }
}
