package io.pgs.svc.pref.mapper;

import io.pgs.cmn.MariaDB;
import io.pgs.svc.pref.dto.DrawingsDto;

import java.util.List;

@MariaDB
public interface DrawingsMapper {
    int exists(String id);

    int create(DrawingsDto drawingsDto);
    int update(DrawingsDto drawingsDto);
    int delete(String id);

    List<DrawingsDto> list(DrawingsDto drawingsDto);
    DrawingsDto info(String id);
}
