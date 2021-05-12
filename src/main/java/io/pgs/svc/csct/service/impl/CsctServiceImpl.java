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
import java.sql.Timestamp;
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


        int successfulCount = 0;
        if (successfulCount > 0) { // 중복건 존재
            log.debug("serviceimp에서 오류");
            log.debug("successfulCount: {}", successfulCount);

            return ServiceUtil.DUPLICATE_COUNT;
        }
        log.debug("successfulCount: {}", successfulCount);

        //String created_at = ServiceUtil.deleteDateformat(csctDto.getCreated_at());
        //csctDto.setCreated_at(created_at);
        return this.csctMapper.create(csctDto);

    }

    @Override
    public int as_update(CsctDto csctDto) {

        String trx_dt = ServiceUtil.deleteDateformat(csctDto.getTrx_dt());
        csctDto.setTrx_dt(trx_dt);

        int successfulCount = 0;
        if (successfulCount > 0) { // 중복건 존재
            log.debug("serviceimp에서 오류");
            log.debug("successfulCount: {}", successfulCount);

            return ServiceUtil.DUPLICATE_COUNT;
        }
        log.debug("successfulCount: {}", successfulCount);

        return this.csctMapper.as_update(csctDto);

    }

    @Override
    public int update(CsctDto csctDto) {

       // String incomingTiime = ServiceUtil.deleteDateformat(csctDto.getIncoming_time());
      //  csctDto.setIncoming_time(incomingTiime);

        int successfulCount = this.csctMapper.update(csctDto);
        log.debug("successfulCount: {}", successfulCount);


        return successfulCount;
    }

    @Override
    public int delete(int id) {

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
