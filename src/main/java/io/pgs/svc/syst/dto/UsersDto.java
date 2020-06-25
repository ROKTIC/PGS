package io.pgs.svc.syst.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class UsersDto {
    // 사용자(users, user_details)
    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    private String phone;
    private LocalDateTime createdAt;
    
    // 권한(authorities) - 콤마(,)로 구분된 문자열 (ex) ROLE_USER,ROLE_ADMIN
    private String authority;
}
