package io.pgs.svc.csct.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.csct.dto.CsctDto;
import io.pgs.svc.csct.service.CsctService;
import io.pgs.svc.csct.mapper.CsctMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional(transactionManager = "mariaTransactionManager")
public class CsctServiceImpl implements ApplicationEventPublisherAware, CsctService {
    @Resource
    private CsctMapper csctMapper;

    @Resource

    private ApplicationEventPublisher eventPublisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public int create(CsctDto csctDto) {

        String unit_id = csctDto.getId();
        int successfulCount = this.csctMapper.exists(unit_id);
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }

        String incomingTiime = ServiceUtil.deleteDateformat(csctDto.getIncoming_time());
        csctDto.setIncoming_time(incomingTiime);
        return this.csctMapper.create(csctDto);

    }

    @Override
    public int update(CsctDto csctDto) {

        String incomingTiime = ServiceUtil.deleteDateformat(csctDto.getIncoming_time());
        csctDto.setIncoming_time(incomingTiime);

        int successfulCount = this.csctMapper.update(csctDto);
        log.debug("successfulCount: {}", successfulCount);


        return successfulCount;
    }

    @Override
    public int delete(String id) {

        // 주차구획면에서 해당 주차면 삭제처리
        int successfulCount = this.csctMapper.delete(id);
        log.debug("csct.successfulCount: {}", successfulCount);

        return successfulCount;
    }


    @Override
    public List<CsctDto> pagelist(CsctDto csctDto) {
        return this.csctMapper.pagelist(csctDto);
    }

    @Override
    public int totalCount(CsctDto csctDto) {
        return this.csctMapper.totalCount(csctDto);
    }

    @Override
    public List<CsctDto> all() {
        return this.csctMapper.all();
    }


}
