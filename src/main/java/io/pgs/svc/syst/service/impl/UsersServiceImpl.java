package io.pgs.svc.syst.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.syst.dto.UsersDto;
import io.pgs.svc.syst.mapper.UsersMapper;
import io.pgs.svc.syst.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public List<UsersDto> list(UsersDto usersDto) {
        return this.usersMapper.list(usersDto);
    }

    @Override
    @Transactional(transactionManager = "mariaTransactionManager")
    public int create(UsersDto usersDto) {

        String username = usersDto.getUsername();
        int successfulCount = this.usersMapper.exists(username);
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }

        successfulCount = this.usersMapper.createUser(usersDto);
        log.debug("Operation create users successfulCount: {}", successfulCount);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation create users failed for ...{successfulCount: " + successfulCount + "}");
        }

        successfulCount = this.usersMapper.createUserDetail(usersDto);
        log.debug("Operation create user_details successfulCount: {}", successfulCount);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation create user_details failed for ...{successfulCount: " + successfulCount + "}");
        }

        successfulCount = this.usersMapper.deleteAuthority(username);
        log.debug("Operation delete authorities successfulCount: {}", successfulCount);

        String authority = StringUtils.trimAllWhitespace(usersDto.getAuthority()); // 콤마(,)로 구분된 문자열 (ex) ROLE_USER,ROLE_ADMIN
        String[] arrayOfAuthority = authority.split(",");

        for (int idx = 0; idx < arrayOfAuthority.length; idx++) {
            UsersDto userTx = new UsersDto();
            userTx.setUsername(username);
            userTx.setAuthority(arrayOfAuthority[idx]);

            successfulCount = this.usersMapper.createAuthority(userTx);
            log.debug("Operation create authorities successfulCount: {}", successfulCount);
            if (successfulCount == 0) {
                throw new RuntimeException("Operation create authorities failed for ...{successfulCount: " + successfulCount + "}");
            }
        }

        return successfulCount;
    }

    @Override
    public int delete(String username) {
        int successfulCount = this.usersMapper.deleteAuthority(username);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation delete authorities failed for ...{successfulCount: " + successfulCount + "}");
        }

        successfulCount = this.usersMapper.deleteUserDetail(username);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation delete user_details failed for ...{successfulCount: " + successfulCount + "}");
        }

        successfulCount = this.usersMapper.deleteUser(username);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation delete user failed for ...{successfulCount: " + successfulCount + "}");
        }

        return successfulCount;
    }

    @Override
    public int update(UsersDto usersDto) {

        int successfulCount = this.usersMapper.updateUser(usersDto);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation update user failed for ...{successfulCount: " + successfulCount + "}");
        }

        successfulCount = this.usersMapper.updateUserDetail(usersDto);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation update user_details failed for ...{successfulCount: " + successfulCount + "}");
        }

        String username = usersDto.getUsername();
        successfulCount = this.usersMapper.deleteAuthority(username);
        if (successfulCount == 0) {
            throw new RuntimeException("Operation delete authorities failed for ...{successfulCount: " + successfulCount + "}");
        }

        String authority = StringUtils.trimAllWhitespace(usersDto.getAuthority()); // 콤마(,)로 구분된 문자열 (ex) ROLE_USER,ROLE_ADMIN
        String[] arrayOfAuthority = authority.split(",");

        for (int idx = 0; idx < arrayOfAuthority.length; idx++) {
            UsersDto userTx = new UsersDto();
            userTx.setUsername(username);
            userTx.setAuthority(arrayOfAuthority[idx]);

            successfulCount = this.usersMapper.createAuthority(userTx);
            log.debug("Operation create authorities successfulCount: {}", successfulCount);
            if (successfulCount == 0) {
                throw new RuntimeException("Operation create authorities failed for ...{successfulCount: " + successfulCount + "}");
            }
        }

        return successfulCount;
    }

    @Override
    public int changePassword(UsersDto usersDto) {
        return this.usersMapper.changePassword(usersDto);
    }
}
