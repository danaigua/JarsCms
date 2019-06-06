package jar.qiuzhisystem.utils;

public class PageUtil {
	/**
	 * 生成分页代码
	 * @param targeUrl
	 * @param totalNum
	 * @param currentPage
	 * @param pageSize
	 * @param param
	 * @return
	 */
	public static String genPagination(String targeUrl, long totalNum, int currentPage, int pageSize, String param) {
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if(totalPage==0) {
			return "";
		}else {
			StringBuffer pageCode = new StringBuffer();
			pageCode.append("<li><a href = ' " + targeUrl + "?page = 1 &" + param + "'>首页</a></li>");
			if(currentPage > 1) {
				pageCode.append("<li><a href = ' " + targeUrl + "?page = " + (currentPage - 1) + " &" + param + "'>上一页</a></li>");
			}else {
				pageCode.append("<li class = 'disabled'><a href = '#'>上一页</a></li>");
			}
			for(int i = currentPage - 2; i < currentPage + 2; i++) {
				if(i < 1 || i > totalPage) {
					continue;
				}
				if(i == currentPage) {
					pageCode.append("<li class = 'active'><a  href = ' " + targeUrl + "?page = " + i + " &" + param + "'>" + i + "</a></li>");
				}else {
					pageCode.append("<li class = 'active'><ahref = ' " + targeUrl + "?page = " + i + " &" + param + "'>" + i + "</a></li>");
				}
			}
			if(currentPage < totalPage) {
				pageCode.append("<li><a href = ' " + targeUrl + "?page = " + (currentPage + 1) + " &" + param + "'>下一页</a></li>");
			}else {
				pageCode.append("<li class = 'disabled'><a href = '#'>下一页</a></li>");
			}
			pageCode.append("<li><a href = ' " + targeUrl + "?page = " + totalPage + " &" + param + "'>末页</a></li>");
			return pageCode.toString();
		}
		
	}
}
