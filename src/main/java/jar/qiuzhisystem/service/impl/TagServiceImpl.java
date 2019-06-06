package jar.qiuzhisystem.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jar.qiuzhisystem.dao.TagDao;
import jar.qiuzhisystem.entity.Tag;
import jar.qiuzhisystem.service.TagService;
/**
 * 标签service实现类
 * @author 12952
 *
 */
@Service("tagService")
public class TagServiceImpl implements TagService{
	@Resource
	private TagDao tagDao;

	public List<Tag> randomList(Integer n) {
		return tagDao.randomList(n);
	}

	public List<Tag> list(Map<String, Object> map) {
		return tagDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return tagDao.getTotal(map);
	}

	public Integer add(Tag tag) {
		return tagDao.add(tag);
	}

	public Integer update(Tag tag) {
		return tagDao.update(tag);
	}

	public Integer delete(Integer id) {
		return tagDao.delete(id);
	}
	
}
