package io.pgs.svc.pref.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.pref.dto.DisplaysDto;
import io.pgs.svc.pref.mapper.DisplaysMapper;
import io.pgs.svc.pref.service.DisplaysService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional(transactionManager = "mariaTransactionManager")
public class DisplaysServiceImpl implements DisplaysService {

    @Resource
    private DisplaysMapper displaysMapper;

    @Override
    public int create(DisplaysDto displaysDto) {
        String display_id = displaysDto.getId();
        int successfulCount = this.displaysMapper.exists(display_id);
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }
        return this.displaysMapper.create(displaysDto);
    }

    @Override
    public int update(DisplaysDto displaysDto) {
        return this.displaysMapper.update(displaysDto);
    }

    @Override
    public int delete(String id) {
        return this.displaysMapper.delete(id);
    }

    @Override
    public List<DisplaysDto> list(DisplaysDto displaysDto) {
        return this.displaysMapper.list(displaysDto);
    }

    @Override
    public DisplaysDto info(String id) {
        return this.displaysMapper.info(id);
    }
}
