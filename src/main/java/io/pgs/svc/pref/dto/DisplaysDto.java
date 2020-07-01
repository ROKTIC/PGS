package io.pgs.svc.pref.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class DisplaysDto {
    // 등록 폼
    private String id;
    private String name;
    private String section_id;
    private String ip;
    private Integer port;
    private String style1;
    private String style2;
    private String style3;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
