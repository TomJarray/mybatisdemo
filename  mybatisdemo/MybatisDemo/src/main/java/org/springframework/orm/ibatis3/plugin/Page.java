package org.springframework.orm.ibatis3.plugin;

public class Page {
	
	private int pageNo;
	private int pageSize;
	private int totalCount;
	
	public static int DEFAULT_PAGE_NO = 1;
	public static int DEFAULT_PAGE_SIZE = 10;
	
	public Page()
    {
        pageNo = DEFAULT_PAGE_NO;
        pageSize = DEFAULT_PAGE_SIZE;
        totalCount = -1;
    }

    public Page(int pageNo, int pageSize)
    {
        this.pageNo = DEFAULT_PAGE_NO;
        this.pageSize = DEFAULT_PAGE_SIZE;
        totalCount = -1;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Page(int pageNo, int pageSize, int totalCount)
    {
        this.pageNo = DEFAULT_PAGE_NO;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.totalCount = -1;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getOffset()
    {
        if(pageNo < 0 || pageSize < 0)
            return -1;
        else
            return (pageNo - 1) * pageSize;
    }

    public int getTotalPageCount()
    {
        int count = totalCount / pageSize;
        if(totalCount % pageSize > 0)
            count++;
        return count;
    }

    public int getNextPage()
    {
        if(hasNextPage())
            return pageNo + 1;
        else
            return pageNo;
    }
    
    public int getPrePage()
    {
        if(hasPrePage())
            return pageNo - 1;
        else
            return pageNo;
    }
    
    public boolean hasNextPage()
    {
        return pageNo + 1 <= getTotalPageCount();
    }

    public boolean hasPrePage()
    {
        return pageNo - 1 >= 1;
    }
	
}
