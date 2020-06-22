package io.pgs.svc.syst.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CodesDto extends PageDto {
    
    // 폼
    private int id;
    private String name;
    private boolean enabled;
    private int sort;
    private String attr1;
    private String attr2;
    private String attr3;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
}
