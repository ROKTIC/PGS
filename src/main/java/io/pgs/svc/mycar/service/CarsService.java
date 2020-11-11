package io.pgs.svc.mycar.service;

import io.pgs.svc.mycar.dto.CarsDto;

import java.util.List;

public interface CarsService {
    List<CarsDto> carList(CarsDto carsDto);
}
