package com.capstone.capstoneProject.service;


import com.capstone.capstoneProject.domain.Member;
import com.capstone.capstoneProject.domain.Role;
import com.capstone.capstoneProject.dto.CustomOAuth2User;
import com.capstone.capstoneProject.dto.GoogleResponse;
import com.capstone.capstoneProject.dto.NaverResponse;
import com.capstone.capstoneProject.dto.OAuth2Response;
import com.capstone.capstoneProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        String userEmail = oAuth2Response.getEmail();
        Member existData = memberRepository.findByEmail(userEmail);

        if (existData == null) {

            Member member = new Member();

            member.setEmail(oAuth2Response.getEmail());
            member.setRole(Role.GUEST);
            member.setName(userEmail.split("@")[0]);

            memberRepository.save(member);
        }
        else {
            System.out.println("이미 로그인을 한적이 있습니다.");
        }

        return new CustomOAuth2User(oAuth2Response, Role.GUEST);
    }
}
