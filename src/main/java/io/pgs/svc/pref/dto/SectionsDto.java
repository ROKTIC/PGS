package io.pgs.svc.pref.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter@Getter
public class SectionsDto extends PageDto {
    // 등록 폼
    private String id;
    private String name;
    private String drawing_id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    // 등록된 주차면 수
    private Integer unitCount;
    // 사용 가능한 주차면 수
    private Integer enabledUnitCount;
    // 주차면 사용률
    private Integer usedRate;
    // 사용 중인 주차면 수
    private Integer usedUnitCount;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
}
