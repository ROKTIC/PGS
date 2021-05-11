package io.pgs.svc.csct.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.csct.dto.CsctDto;

import java.util.List;

@MariaDB
public interface CsctMapper {

    int create(CsctDto CsctDto);
    int as_create(CsctDto CsctDto);
    int update(CsctDto CsctDto);
    int delete(int id);

    List<CsctDto> pagelist(CsctDto CsctDto);
    int totalCount(CsctDto CsctDto);
    List<CsctDto> all();



}
