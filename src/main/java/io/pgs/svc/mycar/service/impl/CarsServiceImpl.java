
package io.pgs.svc.mycar.service.impl;

import io.pgs.svc.mycar.dto.CarsDto;
import io.pgs.svc.mycar.mapper.CarsMapper;
import io.pgs.svc.mycar.service.CarsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Service
@Transactional(transactionManager = "mariaTransactionManager") // From RepositoryConfig
public class CarsServiceImpl implements CarsService {
    @Resource
    private CarsMapper carsMapper;

    @Override
    public List<CarsDto> carList(CarsDto carsDto) {
        return this.carsMapper.carList(carsDto);
    } // 부모의 메소드를 다시 오버라이딩(재정의)함
}

