package io.pgs.svc.csct.service.impl;

import io.pgs.cmn.ServiceUtil;
import io.pgs.svc.csct.dto.CsctDto;
import io.pgs.svc.csct.service.CsctService;
import io.pgs.svc.csct.mapper.CsctMapper;
import io.pgs.svc.pref.dto.UnitsDto;
import io.pgs.svc.syst.dto.CodesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional(transactionManager = "mariaTransactionManager")
public class CsctServiceImpl implements CsctService {
    @Resource
    private CsctMapper csctMapper;

    @Override
    public int create(CsctDto csctDto) {
        /*
        String unit_id = CsctDto.getId();
        int successfulCount = this.csctMapper.exists(unit_id);
        if (successfulCount > 0) { // 중복건 존재
            return ServiceUtil.DUPLICATE_COUNT;
        }

        String incomingTiime = ServiceUtil.deleteDateformat(unitsDto.getIncoming_time());
        unitsDto.setIncoming_time(incomingTiime);
        */
        return this.csctMapper.create(csctDto);

    }
    @Override
    public int update(CsctDto csctDto) {
        return 0;
    }

    @Override
    public int delete(String id) {

        // 주차구획면에서 해당 주차면 삭제처리

        return 0;
    }


    @Override
    public List<CsctDto> pagelist(CsctDto csctDto) {
        return this.csctMapper.pagelist(csctDto);
    }

    @Override
    public int totalCount(CsctDto csctDto) {
        return this.csctMapper.totalCount(csctDto);
    }

    @Override
    public List<CsctDto> all() {
        return this.csctMapper.all();
    }


}
