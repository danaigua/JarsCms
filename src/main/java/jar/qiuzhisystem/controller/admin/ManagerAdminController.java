package jar.qiuzhisystem.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jar.qiuzhisystem.entity.Manager;
import jar.qiuzhisystem.service.ManagerService;
import jar.qiuzhisystem.utils.Md5Util;
import jar.qiuzhisystem.utils.ResponseUtil;
import net.sf.json.JSONObject;

/**
 * 管理员后台管理
 * @author 12952
 *
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerAdminController {
	@Resource
	private ManagerService managerService;
	/**
	 * 修改密码
	 * @param newPassword
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword,HttpServletResponse response)throws Exception{
		Manager manager = new Manager();
		manager.setPassword(Md5Util.md5(newPassword, "qiuzhisystem"));
		managerService.update(manager);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 注销
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout() throws Exception{
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
