package io.pgs.svc.syst.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.syst.dto.CodesDto;

import java.util.List;

@MariaDB
public interface CodesMapper {
    int create(CodesDto codesDto);
    int update(CodesDto codesDto);
    int delete(int id);
    int exists(int id);

    List<CodesDto> pagelist(CodesDto codesDto);
    int totalCount(CodesDto codesDto);
    CodesDto info(int id);
}
