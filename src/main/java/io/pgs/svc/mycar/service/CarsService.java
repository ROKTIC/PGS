package io.pgs.svc.mycar.service;

import io.pgs.svc.mycar.dto.CarsDto;

import java.util.List;

public interface CarsService { // 서비스 인터페이스 만들기
    List<CarsDto> carList(CarsDto carsDto);
}
