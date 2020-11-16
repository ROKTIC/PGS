package io.pgs.cmn;
// 페이징 처리
public class Pagination {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_RANGE_SIZE = 10;

    private int pageSize = DEFAULT_PAGE_SIZE;
    private int rangeSize = DEFAULT_RANGE_SIZE;
    private int totalCount;
    private int pageCount;
    private int rangeCount;

    private int curPage = 1; //현재 페이지 current page
    private int curRange = 1;
    private int startPage = 1;
    private int endPage = 1;
    private int startIndex = 0;
    private int prevPage;
    private int nextPage;

    public Pagination(int totalCount, int curPage) {
        this(totalCount, curPage, DEFAULT_PAGE_SIZE);
    }

    public Pagination(int totalCount, int curPage, int pageSize) {
        this.pageSize = pageSize;
        this.setCurPage(curPage);
        this.setTotalCount(totalCount);
        this.setPageCount(totalCount);
        this.setRangeCount(this.getPageCount());
        this.rangeSetting(curPage);
        this.setStartIndex(curPage);
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setPageCount(int totalCount) {
        this.pageCount = (int) Math.ceil(totalCount * 1.0 / pageSize);
    }

    public void setRangeCount(int pageCount) {
        this.rangeCount = (int) Math.ceil(pageCount * 1.0 / rangeSize);
    }

    public void rangeSetting(int curPage) {

        this.setCurRange(curPage);
        this.startPage = (curRange - 1) * rangeSize + 1;
        this.endPage = startPage + rangeSize - 1;

        if (endPage > pageCount) {
            this.endPage = pageCount;
        }

        this.prevPage = curPage - 1;
        this.nextPage = curPage + 1;
    }

    public void setCurRange(int curPage) {
        this.curRange = (int) ((curPage - 1) / rangeSize) + 1;
    }

    public void setStartIndex(int curPage) {
        this.startIndex = (curPage - 1) * pageSize;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public int getCurRange() {
        return this.curRange;
    }

    public int getCurPage() {
        return this.curPage;
    }

    public int getPrevPage() {
        return this.prevPage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public int getRangeCount() {
        return this.rangeCount;
    }

    public int getStartPage() {
        return this.startPage;
    }

    public int getEndPage() {
        return this.endPage;
    }

    public void copyTo(PageDto pageDto) {
        pageDto.setPageIdx(this.getStartIndex());
        pageDto.setPageSize(this.getPageSize());
    }
}
