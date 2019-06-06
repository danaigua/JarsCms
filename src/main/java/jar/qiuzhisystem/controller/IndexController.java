package jar.qiuzhisystem.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jar.qiuzhisystem.entity.Tag;
import jar.qiuzhisystem.service.TagService;

/**
 * 首页的Controller类
 * @author 12952
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	@Resource
	private TagService tagService;
	
	/**
	 * 请求index的时候直接到这个方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index() throws Exception{
		ModelAndView mav = new ModelAndView();
		List<Tag> tagList = tagService.randomList(200);
		mav.addObject("tagList", tagList);
		mav.setViewName("index");
		return mav;
	}
}
