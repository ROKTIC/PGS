package io.pgs.svc.pref.event;

import io.pgs.svc.pref.dto.UnitsDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class UnitCollectorEventListener {

    @Value("${collector.host}")
    private String host;

    @Value("${collector.port}")
    private int port;


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = UnitCollectorEvent.class)
    public void handle(UnitCollectorEvent event) throws Exception {
        UnitsDto unit = event.getUnit();
        log.debug("unit >>>"+ unit);

        String unitId = unit.getId();
        String incomingTime = unit.getIncoming_time();
        String carNo = leftPadContainsHangle(unit.getCar_no(), 20);
        Integer enabled = unit.getEnabled();

        log.debug("unitId: {}", unitId.substring(2)); // U-10018
        log.debug("carNo: {}", carNo);
        log.debug("enabled: {}", enabled);

        String message = unitId.substring(2) + carNo + enabled;
        log.debug("message: "+ message);

        UnitCollectorAgent agent = UnitCollectorAgent.of(host, port);
        agent.send(message);

    }

    private static final String PADDING_STRING = "@";

    private static String leftPadContainsHangle(String str, int length) {
        str = StringUtils.deleteWhitespace(StringUtils.defaultIfEmpty(str, ""));
        int repeatCnt = length - str.getBytes().length;
        String paddingStr = StringUtils.repeat(PADDING_STRING, repeatCnt);
        return str + paddingStr;
    }
}
