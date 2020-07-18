package io.pgs.svc.syst.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.syst.dto.CodesDto;
import io.pgs.svc.syst.mapper.CodeDetailsMapper;
import io.pgs.svc.syst.service.CodeDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CodeDetailsServiceImpl implements CodeDetailsService {

    @Resource
    private CodeDetailsMapper codeDetailsMapper;

    @Override
    public int create(CodesDto codesDto) {
        int successfulCount = this.codeDetailsMapper.exists(codesDto);
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }

        successfulCount = this.codeDetailsMapper.create(codesDto);
        return successfulCount;
    }

    @Override
    public int update(CodesDto codesDto) {
        return this.codeDetailsMapper.update(codesDto);
    }

    @Override
    public int delete(CodesDto codesDto) {
        return this.codeDetailsMapper.delete(codesDto);
    }

    @Override
    public int exists(CodesDto codesDto) {
        return this.codeDetailsMapper.exists(codesDto);
    }

    @Override
    public List<CodesDto> list(CodesDto codesDto) {
        return this.codeDetailsMapper.list(codesDto);
    }

    @Override
    public List<CodesDto> listEnabled(CodesDto codesDto) {
        return this.codeDetailsMapper.listEnabled(codesDto);
    }
}
