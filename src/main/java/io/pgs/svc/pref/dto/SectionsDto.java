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
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
}
