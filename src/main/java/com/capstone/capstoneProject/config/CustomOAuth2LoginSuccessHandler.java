package com.capstone.capstoneProject.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException, IOException {
        // 사용자 정보를 로그에 출력 (필요시 활용 가능)
        System.out.println("OAuth2 인증 성공: " + authentication.getName());

        // 원하는 페이지로 리다이렉트
        response.sendRedirect("/successPage");
    }
}