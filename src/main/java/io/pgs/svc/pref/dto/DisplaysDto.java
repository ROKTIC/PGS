package io.pgs.svc.pref.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class DisplaysDto extends PageDto {
    // 등록 폼
    private String id;
    private String name;

    // 검색 조건
    private String searchCondition;
    private String searchValue;

}
