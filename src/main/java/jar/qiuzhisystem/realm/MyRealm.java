package jar.qiuzhisystem.realm;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import jar.qiuzhisystem.entity.Manager;
import jar.qiuzhisystem.service.ManagerService;
/**
 * 自定义realm
 * @author 12952
 *
 */
public class MyRealm extends AuthorizingRealm{
	@Resource
	private ManagerService managerService;
	
	/**
	 * 为当前用户设置访问权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
	/**
	 * 验证当前登陆的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		Manager manager = managerService.getByUserName(userName);
		if(manager != null) {
			//把当前用户存到session中
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", manager);
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(manager.getUserName(), manager.getPassword(), "xxx");
			return authcInfo;
		}else {
			return null;
		}
	}

}
