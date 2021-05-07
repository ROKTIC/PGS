package io.pgs.svc.csct.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.csct.dto.CsctDto;

import java.util.List;

@MariaDB
public interface CsctMapper {
    int exists(String id);

    int create(CsctDto CsctDto);
    int update(CsctDto CsctDto);
    int delete(String id);

    List<CsctDto> pagelist(CsctDto CsctDto);
    int totalCount(CsctDto CsctDto);
    List<CsctDto> all();



}