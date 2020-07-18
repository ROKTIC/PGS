package io.pgs.svc.pref.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class UnitsDto extends PageDto {
    // 등록 폼
    private String id;
    private String name;
    private String type;
    private String car_no;
    private String incoming_time;
    private Integer enabled;/* 입차 가능(1), 입차 불가(0) */
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
    private String typeName;

    // Summary
    private Long totalCount;
    private Long enabledCount;
    private Long usedCount;
}
