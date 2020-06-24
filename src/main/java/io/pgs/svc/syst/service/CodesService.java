package io.pgs.svc.syst.service;

import io.pgs.svc.syst.dto.CodesDto;

import java.util.List;

public interface CodesService {
    static final public int DUPLICATE_COUNT = 2;

    int create(CodesDto codesDto);
    int update(CodesDto codesDto);
    int delete(int id);
    List<CodesDto> pagelist(CodesDto codesDto);
    int totalCount(CodesDto codesDto);
}
