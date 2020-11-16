package io.pgs.svc.mycar.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.mycar.dto.CarsDto;

import java.util.List;

@MariaDB
public interface CarsMapper { // DAO
    List<CarsDto> carList(CarsDto unitsDto);
}
