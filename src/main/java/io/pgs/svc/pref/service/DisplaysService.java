package io.pgs.svc.pref.service;

import io.pgs.svc.pref.dto.DisplaysDto;

import java.util.List;

public interface DisplaysService {
    int create(DisplaysDto displaysDto);
    int update(DisplaysDto displaysDto);
    int delete(String id);

    List<DisplaysDto> list(DisplaysDto displaysDto);
}
