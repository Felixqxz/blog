package com.felix.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    private long id;

    private String username;

    private String avatar;

    private String email;
}
