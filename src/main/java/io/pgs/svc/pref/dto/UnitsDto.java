package io.pgs.svc.pref.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UnitsDto extends PageDto {
    // 등록 폼
    private String id;
    private String name;
    private String type;
    private String incoming_time;
    private String car_no;
    private Integer enabled;/* 입차 가능(1), 입차 불가(0) */

    // 검색 조건
    private String searchCondition;
    private String searchValue;
}
