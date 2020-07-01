package io.pgs.svc.pref.service;

import io.pgs.svc.pref.dto.SectionsDto;

import java.util.List;

public interface SectionsService {
    int create(SectionsDto sectionsDto);
    int update(SectionsDto sectionsDto);
    int delete(String id);

    List<SectionsDto> list(SectionsDto sectionsDto);
}
