package io.pgs.svc.as.dto;

import io.pgs.cmn.PageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class AsDto extends PageDto {

    private Timestamp createdAt;
    private Integer as_type;




}
