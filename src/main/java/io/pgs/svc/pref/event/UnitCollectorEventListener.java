package io.pgs.svc.pref.event;

import io.netty.util.CharsetUtil;
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

        String unitId = StringUtils.defaultIfEmpty(unit.getId(), "");
        unitId = unitId.replaceAll("U-", "");
        unitId = leftPad(unitId, 5);


        String incomingTime = unit.getIncoming_time();
        String carNo = leftPadContainsHangle(unit.getCar_no(), 20);
        Integer enabled = unit.getEnabled(); // enabled = 1: (입차가능), enabled = 0 (입차중)

        log.debug("unitId: {}", unitId); // U-10018
        log.debug("carNo: {}", carNo);
        log.debug("enabled: {}", enabled);

        // 입/출차Flag: 1 - 입차, 0 - 출차
        String flag = "0";
        if (enabled == 1) { // 입차가능
            flag = "0"; // 출차
        } else { // 입차불가
            flag = "1"; // 입차
        }

        String message = unitId + carNo + flag;
        log.debug("message: "+ message);

        UnitCollectorAgent agent = UnitCollectorAgent.of(host, port);
        agent.send(message);

    }

    private static final String PADDING_STRING = "@";
    private static String leftPadContainsHangle(String str, int length) {
        str = StringUtils.deleteWhitespace(StringUtils.defaultIfEmpty(str, ""));
        int repeatCnt = length - str.getBytes(CharsetUtil.UTF_8).length;
        log.debug("repeatCnt: >>>"+ repeatCnt);
        String paddingStr = StringUtils.repeat(PADDING_STRING, repeatCnt);
        return str + paddingStr;
    }

    private static String leftPad(String str, int length) {
        str = StringUtils.deleteWhitespace(StringUtils.defaultIfEmpty(str, ""));
        return StringUtils.leftPad(str, length, PADDING_STRING);
    }
}
