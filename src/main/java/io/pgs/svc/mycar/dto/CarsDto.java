package io.pgs.svc.mycar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CarsDto {
    // 등록 폼
    private String id; // 주차면 번호
    private String name; //주차면 명
    private String car_no; // 차량 번호
    private Integer xleft;
    private Integer ytop;

    // 검색 조건
    private String searchCondition;
    private String searchValue;
    private String typeName;


}
