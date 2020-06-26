package io.pgs.svc.pref.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.pref.dto.UnitsDto;

import java.util.List;

@MariaDB
public interface UnitsMapper {





    List<UnitsDto> pagelist(UnitsDto unitsDto);
    int totalCount(UnitsDto unitsDto);
}
