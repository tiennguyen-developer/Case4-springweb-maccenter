package com.codegym.maccenter.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;

    private String email;

    private String password;

    private List<Integer> roleList;

    private String firstName;

    private String lastName;

}
