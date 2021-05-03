package io.pgs.svc.csct.service.impl;

import io.pgs.svc.csct.service.CsctService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(transactionManager = "mariaTransactionManager")
public class CsctServiceImpl implements CsctService {

}
