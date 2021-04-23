package io.pgs.svc.as.service.impl;

import io.pgs.svc.as.service.RegistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional(transactionManager = "mariaTransactionManager")
public class RegistServiceImpl implements RegistService {

}
