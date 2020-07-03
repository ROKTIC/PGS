package io.pgs.svc.pref.service;

import io.pgs.svc.pref.dto.SectionUnitsDto;

import java.util.List;

public interface SectionUnitsService {
    int save(String sectionId, List<SectionUnitsDto> sectionUnitsDto);
    List<SectionUnitsDto> listBySectionId(String sectionId);
}
