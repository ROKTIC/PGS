package io.pgs.svc.syst.service;

import io.pgs.svc.syst.dto.CodesDto;

import java.util.List;

public interface CodeDetailsService {
    int create(CodesDto codesDto);
    int update(CodesDto codesDto);
    int delete(CodesDto codesDto);
    int exists(CodesDto codesDto);

    List<CodesDto> list(CodesDto codesDto);
}
