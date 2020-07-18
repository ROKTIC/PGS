package io.pgs.svc.syst.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.syst.dto.CodesDto;

import java.util.List;

@MariaDB
public interface CodeDetailsMapper {
    int create(CodesDto codesDto);
    int update(CodesDto codesDto);
    int delete(CodesDto codesDto);
    int exists(CodesDto codesDto);

    List<CodesDto> list(CodesDto codesDto);
    List<CodesDto> listEnabled(CodesDto codesDto);
}
