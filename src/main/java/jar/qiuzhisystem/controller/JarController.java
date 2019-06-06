package jar.qiuzhisystem.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jar.qiuzhisystem.entity.Jar;
import jar.qiuzhisystem.lucence.JarIndex;
import jar.qiuzhisystem.service.JarService;
import jar.qiuzhisystem.service.TagService;
import jar.qiuzhisystem.utils.PageUtil;
import jar.qiuzhisystem.utils.StringUtil;

/**
 * jar包controller
 * @author 12952
 *
 */
@Controller
@RequestMapping("/jar")
public class JarController {
	@Resource
	private TagService tagService;
	
	@Resource
	private JarService jarService;
	private JarIndex jarIndex = new JarIndex(); 
	/**
	 * 根据关键字查询相关jar包信息
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/q")
	public ModelAndView search(@RequestParam(value = "q", required = false) String q, @RequestParam(value = "page", required = false) String page, HttpServletRequest request) throws Exception{
		if(StringUtil.isEmpty(page)) {
			page = "1";
		}
		ModelAndView mav = new ModelAndView();
		List<Jar> jarList = jarIndex.searchJar(q.trim(), 200);
		mav.addObject("q", q);
		Integer toIndex = jarList.size() >= Integer.parseInt(page) * 20 ? Integer.parseInt(page) * 20 : jarList.size();
		mav.addObject("jarList", jarList.subList((Integer.parseInt(page) - 1) * 20, toIndex));
		mav.addObject("resultTotal", jarList.size());
		mav.addObject("tagList", tagService.randomList(200));
		mav.addObject("pageCode",PageUtil.genPagination(request.getServletContext().getContextPath() + "/jar/q.do", jarList.size(), Integer.parseInt(page), 20, "q = " + q));
		mav.setViewName("result");
		return mav;
	}
	/**
	 * 请求具体jar页面信息
	 * @return
	 */
	@RequestMapping("/{uuid}")
	public ModelAndView view(@PathVariable("uuid") String uuid) throws Exception {
		ModelAndView mav = new ModelAndView();
		Jar jar = jarService.findById(uuid);
		jar.setClick(jar.getClick() + 1);
		jarService.update(jar);
		mav.addObject("jar",jar);
		mav.addObject("tagList", tagService.randomList(200));
		mav.addObject("relJarList", jarIndex.searchJar(jar.getName().replaceAll("-", " "), 16));
		mav.setViewName("view");
		return mav;
	}
	/**
	 * 下载指定jar包
	 * @param uuid
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download/{uuid}")
	public String download(@PathVariable("uuid") String uuid, HttpServletResponse response) throws Exception {
		Jar jar = jarService.findById(uuid);
		jar.setDownHit(jar.getDownHit() + 1);
		jarService.update(jar);
		response.sendRedirect(jar.getPath());
		return null;
	}
}
