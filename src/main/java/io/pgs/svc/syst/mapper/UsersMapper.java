package io.pgs.svc.syst.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.syst.dto.UsersDto;

import java.util.List;

@MariaDB
public interface UsersMapper {
    int exists(String username);

    int createUser(UsersDto usersDto);
    int createUserDetail(UsersDto usersDto);
    int createAuthority(UsersDto usersDto);

    int deleteUser(String username);
    int deleteUserDetail(String username);
    int deleteAuthority(String username);

    int updateUser(UsersDto usersDto);
    int updateUserDetail(UsersDto usersDto);

    List<UsersDto> list(UsersDto usersDto);
}
