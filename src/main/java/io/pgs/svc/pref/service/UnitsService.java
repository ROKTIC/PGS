package io.pgs.svc.pref.service;

import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.syst.dto.CodesDto;

import java.util.List;

public interface UnitsService {
    int create(UnitsDto unitsDto);
    int update(UnitsDto unitsDto);
    int delete(String id);

    List<UnitsDto> pagelist(UnitsDto unitsDto);
    int totalCount(UnitsDto unitsDto);
}
