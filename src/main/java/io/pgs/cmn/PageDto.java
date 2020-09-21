package io.pgs.cmn;
// 테스트1
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PageDto {
    private int curPage;
    private int pageIdx;
    private int pageSize;
}
