package jar.qiuzhisystem.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Md5工具类
 * @author 12952
 *
 */
public class Md5Util {
	/**
	 * 返回一个md5加密的结果
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str, String salt) {
		return new Md5Hash(str, salt).toString();
	}
//	public static void main(String[] args) {
//		String password = "123456";
//		String salt = "qiuzhisystem";
//		System.out.println(Md5Util.md5(password, salt));
//	}
}
