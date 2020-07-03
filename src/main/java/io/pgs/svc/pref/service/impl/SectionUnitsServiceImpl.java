package io.pgs.svc.pref.service.impl;

import io.pgs.svc.pref.dto.SectionUnitsDto;
import io.pgs.svc.pref.mapper.SectionUnitsMapper;
import io.pgs.svc.pref.service.SectionUnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SectionUnitsServiceImpl implements SectionUnitsService {

    @Resource
    private SectionUnitsMapper sectionUnitsMapper;

    @Override
    @Transactional(transactionManager = "mariaTransactionManager")
    public int save(String sectionId, List<SectionUnitsDto> sectionUnitsList) {

        int successfulCount = this.sectionUnitsMapper.deleteBySectionId(sectionId);
        log.debug("delete successfulCount: {}", successfulCount);

        successfulCount = 0;
        for (SectionUnitsDto element : sectionUnitsList) {

            successfulCount += this.sectionUnitsMapper.create(element);
            log.debug("create successfulCount: {}", successfulCount);
        }

        return successfulCount;
    }

    @Override
    public List<SectionUnitsDto> listBySectionId(String sectionId) {
        return this.sectionUnitsMapper.listBySectionId(sectionId);
    }
}
