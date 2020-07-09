package io.pgs.svc.pref.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.SectionsDto;
import io.pgs.svc.pref.mapper.SectionsMapper;
import io.pgs.svc.pref.service.SectionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SectionsServiceImpl implements SectionsService {

    @Resource
    private SectionsMapper sectionsMapper;

    @Override
    public int create(SectionsDto sectionsDto) {
        String section_id = sectionsDto.getId();
        int successfulCount = this.sectionsMapper.exists(section_id);
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }

        return this.sectionsMapper.create(sectionsDto);
    }

    @Override
    public int update(SectionsDto sectionsDto) {
        return this.sectionsMapper.update(sectionsDto);
    }

    @Override
    public int delete(String id) {
        return this.sectionsMapper.delete(id);
    }

    @Override
    public List<SectionsDto> list(SectionsDto sectionsDto) {
        return this.sectionsMapper.list(sectionsDto);
    }

    @Override
    public List<SectionsDto> all() {
        return this.sectionsMapper.all();
    }

    @Override
    public List<String> namelist(List<String> sectionIds) {
        return this.sectionsMapper.namelist(sectionIds);
    }

    @Override
    public List<SectionsDto> unitCountPerSection() {
        return this.sectionsMapper.unitCountPerSection();
    }
}
