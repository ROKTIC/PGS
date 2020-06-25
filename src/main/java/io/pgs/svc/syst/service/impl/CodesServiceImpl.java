package io.pgs.svc.syst.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.syst.dto.CodesDto;
import io.pgs.svc.syst.mapper.CodesMapper;
import io.pgs.svc.syst.service.CodesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CodesServiceImpl implements CodesService {

    @Resource
    private CodesMapper codesMapper;

    @Override
    public int create(CodesDto codesDto) {
        int successfulCount = this.codesMapper.exists(codesDto.getId());
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }

        successfulCount = this.codesMapper.create(codesDto);
        return successfulCount;
    }

    @Override
    public int update(CodesDto codesDto) {
        return this.codesMapper.update(codesDto);
    }

    @Override
    public int delete(int id) {
        // 만약 상세코드가 있으면 삭제불가능함!






        return this.codesMapper.delete(id);
    }

    @Override
    public List<CodesDto> pagelist(CodesDto codesDto) {
        return this.codesMapper.pagelist(codesDto);
    }

    @Override
    public int totalCount(CodesDto codesDto) {
        return this.codesMapper.totalCount(codesDto);
    }
}
