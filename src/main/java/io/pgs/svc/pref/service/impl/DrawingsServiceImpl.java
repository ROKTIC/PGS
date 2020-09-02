package io.pgs.svc.pref.service.impl;

import io.pgs.svc.pref.dto.DrawingsDto;
import io.pgs.svc.pref.mapper.DrawingsMapper;
import io.pgs.svc.pref.service.DrawingsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional(transactionManager = "mariaTransactionManager")
public class DrawingsServiceImpl implements DrawingsService {

    @Resource
    private DrawingsMapper drawingsMapper;

    @Override
    public int exists(String id) {
        return this.drawingsMapper.exists(id);
    }

    @Override
    public int create(DrawingsDto drawingsDto) {
        return this.drawingsMapper.create(drawingsDto);
    }

    @Override
    public int update(DrawingsDto drawingsDto) {
        return this.drawingsMapper.update(drawingsDto);
    }

    @Override
    public int delete(String id) {
        return this.drawingsMapper.delete(id);
    }

    @Override
    public int save(String id, DrawingsDto drawingsDto) {
        int successfulCount = 0;

        if(StringUtils.isNotEmpty(id)) {
            // 등록 처리
            drawingsDto.setId(id);
            successfulCount = this.drawingsMapper.create(drawingsDto);
        } else {
            // 수정 처리
        }
        return successfulCount;
    }

    @Override
    public List<DrawingsDto> list(DrawingsDto drawingsDto) {
        return this.drawingsMapper.list(drawingsDto);
    }

    @Override
    public DrawingsDto info(String id) {
        return this.drawingsMapper.info(id);
    }
}
