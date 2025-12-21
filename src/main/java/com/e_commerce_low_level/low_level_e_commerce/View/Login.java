package com.e_commerce_low_level.low_level_e_commerce.View;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class Login extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            chain.doFilter(request, response); // continue

        } else {

            Cookie[] cookies = request.getCookies();

            HttpSession session = request.getSession();
            Object attribute = session.getAttribute("id");

            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals("id") && attribute == null) {
                    response.sendRedirect("/login");
                }
            }

        }

    }
}
