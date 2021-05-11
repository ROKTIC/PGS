package io.pgs.svc.csct.service;


import io.pgs.svc.csct.dto.CsctDto;
import java.util.List;

public interface CsctService {

    int create(CsctDto csctDto);
    int as_create(CsctDto CsctDto);
    int update(CsctDto csctDto);
    int delete(int id);

    List<CsctDto> pagelist(CsctDto csctDto);
    int totalCount(CsctDto csctDto);
    List<CsctDto> all();
}
