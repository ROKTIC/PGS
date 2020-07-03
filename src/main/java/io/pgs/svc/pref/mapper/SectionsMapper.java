package io.pgs.svc.pref.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.pref.dto.SectionsDto;

import java.util.List;

@MariaDB
public interface SectionsMapper {
    int exists(String id);

    int create(SectionsDto sectionsDto);
    int update(SectionsDto sectionsDto);
    int delete(String id);

    List<SectionsDto> list(SectionsDto sectionsDto);
    List<SectionsDto> all();
    List<String> namelist(List<String> sectionIds);
}
