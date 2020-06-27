package io.pgs.svc.pref.service.impl;

import io.pgs.cmn.ServiceUtil;
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
    public int create(UnitsDto unitsDto) {
        String unit_id = unitsDto.getId();
        int successfulCount = this.unitsMapper.exists(unit_id);
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }

        return this.unitsMapper.create(unitsDto);
    }

    @Override
    public int update(UnitsDto unitsDto) {
        return this.unitsMapper.update(unitsDto);
    }

    @Override
    public int delete(String id) {
        return this.unitsMapper.delete(id);
    }

    @Override
    public List<UnitsDto> pagelist(UnitsDto unitsDto) {
        return this.unitsMapper.pagelist(unitsDto);
    }

    @Override
    public int totalCount(UnitsDto unitsDto) {
        return this.unitsMapper.totalCount(unitsDto);
    }
}
