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
    private Integer xleft; // x 좌표
    private Integer ytop; // y 좌표

    // 검색 조건
    private String searchValue; // 텍스트박스 값 = 차량번호
    private String searchCondition; // 검색조건
    // Summary
   // private Long totalCount;
   // private Long enabledCount;
   // private Long usedCount;
}
