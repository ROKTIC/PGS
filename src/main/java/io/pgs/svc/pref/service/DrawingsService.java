package io.pgs.svc.pref.service;

import io.pgs.svc.pref.dto.DrawingsDto;

import java.util.List;

public interface DrawingsService {
    int exists(String id);

    int create(DrawingsDto drawingsDto);
    int update(DrawingsDto drawingsDto);
    int delete(String id);

    int save(String id, DrawingsDto drawingsDto);

    List<DrawingsDto> list(DrawingsDto drawingsDto);
    DrawingsDto info(String id);
}
