package cn.com.pengyue.vo;
//分页信息
public class PageInfo {
	private static final int DEFAULT_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 15;
	private int pageNo=DEFAULT_PAGE_NO;
	private int pageSize=DEFAULT_PAGE_SIZE;
	private int totalSize;
	private int maxPageNo;
	private int firstNo=0;
	public int getPageNo() {
		if(pageNo<=0){
			pageNo=DEFAULT_PAGE_NO;
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		setFirstNo0();
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<=0){
			pageSize=DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
		setFirstNo0();
	}
	public int getMaxPageNo() {
		return maxPageNo;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		
		//计算最大页数
		if(pageSize!=0){
			maxPageNo = totalSize / pageSize;
			if(this.totalSize% pageSize!=0 ){
				maxPageNo++;
			}
		}
	}
	public int getFirstNo() {
		return firstNo;
	}
	private void setFirstNo0() {
		firstNo = totalSize-(pageSize*pageNo);
		if(firstNo<0){
			firstNo=0;
		}
	}
}
