package io.pgs.svc.as.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class RegistDto { //등록 & 수정 Dto

    private Integer site_id;
    private Integer site_type;
    private String title;
    private String contents;

    private Timestamp updated_at;



}
