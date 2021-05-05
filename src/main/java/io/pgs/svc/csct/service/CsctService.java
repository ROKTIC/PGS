package io.pgs.svc.csct.service;


import io.pgs.svc.csct.dto.CsctDto;
import java.util.List;

public interface CsctService {

    int create(CsctDto csctDto);
    int update(CsctDto csctDto);
    int delete(String id);

    List<CsctDto> pagelist(CsctDto csctDto);
    List<CsctDto> all();
}
