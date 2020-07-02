package io.pgs.svc.pref.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class SectionUnitsDto {
    private String section_id;
    private String unit_id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
