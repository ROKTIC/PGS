package io.pgs.svc.mycar.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.mycar.dto.CarsDto;

import java.util.List;

@MariaDB
public interface CarsMapper {
    List<CarsDto> carList(CarsDto unitsDto);
}
