/*
package io.pgs.svc.mycar.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.mycar.dto.CarsDto;
import io.pgs.svc.mycar.mapper.CarsMapper;
import io.pgs.svc.mycar.service.CarsService;
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
public class CarsServiceImpl implements ApplicationEventPublisherAware, CarsService {

    @Resource
    private CarsMapper carsMapper;

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public List<CarsDto> carlist(CarsDto carsDto) {
        return this.CarsMapper.carlist(carsDto);
    }

    @Override
    public int totalCount(CarsDto carsDto) {
        return this.CarsMapper.totalCount(carsDto);
    }

    @Override
    public List<CarsDto> all() {
        return this.CarsMapper.all();
    }

    @Override
    public CarsDto summary() {
        return this.CarsMapper.summary();
    }


}

 */