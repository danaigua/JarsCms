package jar.qiuzhisystem.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jar.qiuzhisystem.entity.PageBean;
import jar.qiuzhisystem.entity.Tag;
import jar.qiuzhisystem.service.TagService;
import jar.qiuzhisystem.utils.ResponseUtil;
import jar.qiuzhisystem.utils.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * tag的后台管理
 * @author 12952
 *
 */
@Controller
@RequestMapping("/admin/tag")
public class TagAdminController {
	
	@Resource
	private TagService tagService;
	/**
	 * 列出所有的jar包tag标签
	 * @param page
	 * @param rows
	 * @param s_name
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required=false)String page, @RequestParam(value = "rows",required = false)String rows, @RequestParam(value = "s_name", required = false)String s_name, HttpServletResponse response)throws Exception{
		if(StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("name", StringUtil.formatLike(s_name));
		List<Tag> tagList = tagService.list(map);
		Long total = tagService.getTotal(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(tagList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 删除一个标签
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)throws Exception{
		String[] idString = ids.split(",");
		int total = 0;//受影响的总记录条数
		for (String id : idString) {
			total = tagService.delete(Integer.parseInt(id));
		}
		JSONObject result = new JSONObject();
		if(total > 0) {
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 添加或者修改一个标签
	 * @param tag
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Tag tag, HttpServletResponse response) throws Exception{
		int resultTotal = 0;
		if(tag.getId() == null) {
			//添加
			resultTotal = tagService.add(tag);
		}else {
			resultTotal = tagService.update(tag);
		}
		JSONObject result = new JSONObject();
		if(resultTotal>0) {
			result.put("success", true);
		}else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
