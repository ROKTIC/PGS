package io.pgs.svc.syst.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class CodesDto extends PageDto {
    
    // 폼
    private Integer id;
    private String name;
    private Integer enabled;
    private Integer sort;
    private String attr1;
    private String attr2;
    private String attr3;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
}
