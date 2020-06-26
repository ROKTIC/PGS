package io.pgs.svc.pref.service.impl;

import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.pref.mapper.UnitsMapper;
import io.pgs.svc.pref.service.UnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
public class UnitsServiceImpl implements UnitsService {

    @Resource
    private UnitsMapper unitsMapper;

    @Override
    public List<UnitsDto> pagelist(UnitsDto unitsDto) {
        return this.unitsMapper.pagelist(unitsDto);
    }

    @Override
    public int totalCount(UnitsDto unitsDto) {
        return this.unitsMapper.totalCount(unitsDto);
    }
}
