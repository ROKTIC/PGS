package io.pgs.svc.pref.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class UnitsDto extends PageDto {
    // 등록 폼
    private String id;
    private String name;
    private String type;
    private boolean enabled;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
}
