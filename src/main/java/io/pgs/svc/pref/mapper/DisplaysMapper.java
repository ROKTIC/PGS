package io.pgs.svc.pref.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.pref.dto.DisplaysDto;

import java.util.List;

@MariaDB
public interface DisplaysMapper {
    int exists(String id);

    int create(DisplaysDto displaysDto);
    int update(DisplaysDto displaysDto);
    int delete(String id);

    List<DisplaysDto> list(DisplaysDto displaysDto);
}
