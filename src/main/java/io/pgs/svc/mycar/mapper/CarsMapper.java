package io.pgs.svc.mycar.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.mycar.dto.CarsDto;

import java.util.List;

@MariaDB
public interface CarsMapper {
    int exists(String id);

    List<CarsDto> carlist(CarsDto unitsDto);
    int totalCount(CarsDto carsDto);
    List<CarsDto> all();

    //CarsDto summary();
}
