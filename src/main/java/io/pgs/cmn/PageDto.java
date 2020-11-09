package io.pgs.cmn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageDto {
    private int curPage;
    private int pageIdx;
    private int pageSize;
}
