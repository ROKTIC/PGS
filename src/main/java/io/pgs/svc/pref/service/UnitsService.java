package io.pgs.svc.pref.service;

import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.syst.dto.CodesDto;

import java.util.List;

public interface UnitsService {


    List<UnitsDto> pagelist(UnitsDto unitsDto);
    int totalCount(UnitsDto unitsDto);
}
