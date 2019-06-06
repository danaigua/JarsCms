package jar.qiuzhisystem.utils;
/**
 * 字符串工具类
 * @author 12952
 *
 */
public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * @param hName
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str)) {
			return true;
		}else {
			return false;
		}
		
	}
	/**
	 * 判断是否非空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	/**
	 * 格式化模糊查询
	 * @param str
	 * @return
	 */
	public static String formatLike(String str) {
		if(isNotEmpty(str)) {
			return "%" + str + "%";
		}
		return null;
	}

	
}
