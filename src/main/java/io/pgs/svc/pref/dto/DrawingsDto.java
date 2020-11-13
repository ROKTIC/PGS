package io.pgs.svc.pref.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter @Getter @ToString
public class DrawingsDto {

    private String id;
    private String name;
    private String img_path; // 도면 경로
    private String img_name; // 도면 이름
    private Timestamp createdAt;

}
