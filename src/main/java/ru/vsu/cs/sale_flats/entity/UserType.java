package ru.vsu.cs.sale_flats.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority {
    admin,user;

    @Override
    public String getAuthority() {
        return name();
    }
}
