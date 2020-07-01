package io.pgs.svc.pref.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.pref.dto.UnitsDto;

import java.util.List;

@MariaDB
public interface UnitsMapper {
    int exists(String id);

    int create(UnitsDto unitsDto);
    int update(UnitsDto unitsDto);
    int delete(String id);

    List<UnitsDto> pagelist(UnitsDto unitsDto);
    int totalCount(UnitsDto unitsDto);
}
