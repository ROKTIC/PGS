package io.pgs.svc.pref.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.pref.dto.SectionUnitsDto;

import java.util.List;


@MariaDB
public interface SectionUnitsMapper {
    List<SectionUnitsDto> listBySectionId(String sectionId);
}
