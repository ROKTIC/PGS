package io.pgs.cmn;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PageDto {
    private int curPage;
    private int pageIdx;
    private int pageSize;
}
