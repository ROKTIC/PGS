package io.pgs.svc.syst.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UsersDto extends PageDto {
    // 폼
    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
}
