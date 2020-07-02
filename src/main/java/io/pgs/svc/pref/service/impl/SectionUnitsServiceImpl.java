package io.pgs.svc.pref.service.impl;

import io.pgs.svc.pref.dto.SectionUnitsDto;
import io.pgs.svc.pref.mapper.SectionUnitsMapper;
import io.pgs.svc.pref.service.SectionUnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SectionUnitsServiceImpl implements SectionUnitsService {

    @Resource
    private SectionUnitsMapper sectionUnitsMapper;

    @Override
    public List<SectionUnitsDto> listBySectionId(String sectionId) {
        return this.sectionUnitsMapper.listBySectionId(sectionId);
    }
}
