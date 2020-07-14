package io.pgs.svc.pref.event;

import io.pgs.svc.pref.dto.UnitsDto;
import lombok.Getter;

@Getter
public class UnitCollectorEvent {
    private UnitsDto unit;

    public UnitCollectorEvent(UnitsDto unit) {
        this.unit = unit;
    }
}
