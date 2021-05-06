package io.pgs.svc.csct.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class CsctDto extends PageDto { //콜 메인리스트 페이지 Dto

    private Timestamp created_at;  //등록일시
    private Integer call_id; // Auto Inc
    private Integer site_id;
    private Integer site_type;
    private String title;
    private String contents;
    private String created_by;

    //As 처리
    private Timestamp updated_at;
    private String trx_dt; //처리일시
    private String trx_contents;
    private String sms_yn;
    private String trx_by;

    //검색 조건
    private String searchCondition;

}