package io.pgs.svc.pref.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.pref.dto.SectionUnitsDto;

import java.util.List;


@MariaDB
public interface SectionUnitsMapper {

    int create(SectionUnitsDto sectionUnitsDto);
    int deleteBySectionId(String id);
    int delete(SectionUnitsDto sectionUnitsDto);

    List<SectionUnitsDto> listBySectionId(String sectionId);
    int deleteByUnitId(String id);
}
