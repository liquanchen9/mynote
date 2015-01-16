package cn.com.pengyue.vo;


//分页信息
public class PageInfo implements Cloneable {
	private static final int DEFAULT_PAGE_NO = 1;
	public static final int DEFAULT_PAGE_SIZE = 15;  
	private int pageNo=DEFAULT_PAGE_NO;
	private int pageSize=DEFAULT_PAGE_SIZE;
	private int totalSize;
	private int maxPageNo;
	private int firstNo=0;
	private int endNo=0;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<=0){
			pageNo=DEFAULT_PAGE_NO;
		}
		this.pageNo = pageNo;
		setFirstAndEndNo0();
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<=0){
			pageSize=DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
		setFirstAndEndNo0();
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
	public int getEndNo() {
		return endNo;
	}
	private void setFirstAndEndNo0() {
		firstNo = pageSize*(pageNo-1);
		if(firstNo<0){
			firstNo=0;
		}
		if(firstNo>totalSize){
			firstNo=totalSize;
		}
		endNo = pageSize*pageNo;
		if(endNo<0){
			endNo=0;
		}
		if(endNo > totalSize){
			endNo = totalSize;
		}
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

// TODO ognl 测试
//public static void main(String[] args) throws OgnlException {
//	
//	String ognlStr = "#index=0," +
//	"#forPageInfo=new cn.com.pengyue.vo.PageInfo()," +
//	"#forPageInfo.pageSize=64," +
//	"#forPageInfo.totalSize=4000," +
//	"#forPageInfo.pageNo=1," +
//	"#forEle=:[" +
//		"#forPageInfo.endNo<#this?(" +
//			"1" +
//		"):(" +
//			"@java.lang.System@out.println(#this)," +
//			"#forEle(#this+1))" +
//	"]," +
//	"#forPageer=:[" +
//		"#this.maxPageNo<#this.pageNo?(" +
//			"#index=#this.firstNo," +
////			"@java.lang.System@out.println(#index)," +
//			"1" +
//		"):(" +
////			"@java.lang.System@out.println('pageNo::'+#this.pageNo)," +
////			"@java.lang.System@out.println('endNo::'+#this.endNo)," +
////			"@java.lang.System@out.println('firstNo::'+#this.firstNo)," +
//			"#forEle(#this.firstNo)," +
//			"#this.pageNo=#this.pageNo+1," +
//			"#forPageer(#this)" +
//	")]," +
//	"#forPageer(#forPageInfo)," +
//	"1";
//	String dateStr = "(31 December 2012 to 12 January 2013)";
//	System.out.println(Pattern.compile("\\s+to\\s+\\d+\\s+[a-zA-Z]+\\s+\\d+").matcher(dateStr).find());
//	Ognl.getValue("#dataStr=#this,#dateRangPattern1 = @java.util.regex.Pattern@compile(\"to\\\\s+\\\\d+\\\\s+\"),"+
//	"#dateRangPattern2 = @java.util.regex.Pattern@compile(\"\\\\s+to\\\\s+\\\\d+\\\\s+[a-zA-Z]+\\\\s+\\\\d+\\\\)$\")," +
//	"#dateRangPattern2Matcher = #dateRangPattern2.matcher(#dataStr)," +
//	"@System@out.println(#dateRangPattern2Matcher.find())," +
//	"#dateRangPattern2Matcher = #dateRangPattern2.matcher(#dataStr)," +
//	"1"
//			, dateStr);
//	
////	Properties propes = new Properties();
////	try {
////		propes.load(OgnlParser.class.getResourceAsStream("OgnlParser.properties"));
////	} catch (IOException e) {
////	}
//	
//	//System.out.println(propes.get("Aptn.parse"));
//}

