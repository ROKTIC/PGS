package io.pgs.svc.syst.service;

import io.pgs.svc.syst.dto.UsersDto;

import java.util.List;

public interface UsersService {
    List<UsersDto> list(UsersDto usersDto);
    int create(UsersDto usersDto);
    int delete(String username);
    int update(UsersDto usersDto);
    int changePassword(UsersDto usersDto);
}
