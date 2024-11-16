package com.capstone.capstoneProject.dto;

import com.capstone.capstoneProject.domain.Role;
import lombok.Getter;

@Getter
public class JoinForm {

    private String email;
    private Role role;
}