package jar.qiuzhisystem.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jar.qiuzhisystem.entity.Jar;
import jar.qiuzhisystem.entity.PageBean;
import jar.qiuzhisystem.lucence.JarIndex;
import jar.qiuzhisystem.service.JarService;
import jar.qiuzhisystem.utils.ResponseUtil;
import jar.qiuzhisystem.utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * jar包后台管理的controller类
 * @author 12952
 *
 */
@Controller
@RequestMapping("/admin/jar")
public class JarAdminController {
	
	@Resource
	private JarService jarService;
	
	private JarIndex jarIndex = new JarIndex();
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page, @RequestParam(value="rows",required=false)String rows, @RequestParam(value="s_name",required=false)String s_name, HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("name", StringUtil.formatLike(s_name));
		List<Jar> jarList = jarService.list(map);
		Long total = jarService.getTotal(map);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(jarList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 删除jar包
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response) throws Exception{
		String []idsString = ids.split(",");
		for (String id : idsString) {
			jarService.delete(id);
			jarIndex.deleteIndex(id);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 添加或者修改jar包信息 
	 * @param jar
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Jar jar, HttpServletResponse response) throws Exception{
		int resultTotal = 0; //操作的记录条数
		if(jar.getUuid() == null) {
			//添加
			resultTotal = jarService.add(jar);
			jarIndex.addIndex(jar);
		}else {
			//修改
			resultTotal = jarService.update(jar);
			jarIndex.updateIndex(jar);
		}
		JSONObject result = new JSONObject();
		if(resultTotal > 0) {
			//成功
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		return null;
	}
}
