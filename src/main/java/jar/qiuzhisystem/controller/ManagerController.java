package jar.qiuzhisystem.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jar.qiuzhisystem.entity.Manager;
import jar.qiuzhisystem.service.ManagerService;
import jar.qiuzhisystem.utils.Md5Util;

/**
 * 管理员Controller层
 * @author 12952
 *
 */
@Controller
@RequestMapping("/manager2")
public class ManagerController {
	
	@Resource
	private ManagerService managerService;
	/**
	 * 用户登陆
	 * @param manager
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(Manager manager, HttpServletRequest request) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(manager.getUserName(), Md5Util.md5(manager.getPassword(), "qiuzhisystem"));
		try {
			subject.login(token);//登陆验证
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("manager", manager);
			request.setAttribute("errorInfo", "用户名密码错误");
			return "login";
		}
	}
}
