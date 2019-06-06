package jar.qiuzhisystem.service;

import java.util.List;
import java.util.Map;

import jar.qiuzhisystem.entity.Tag;

/**
 * 标签service接口
 * @author 12952
 *
 */
public interface TagService {
	public List<Tag> randomList(Integer n);
	/**
	 * 查找标签信息
	 * @param map
	 * @return
	 */
	public List<Tag> list(Map<String, Object> map);
	/**
	 * 查询总记录条数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
	/**
	 * 添加一个标签
	 * @param tag
	 * @return
	 */
	public Integer add(Tag tag);
	/**
	 * 修改一个标签
	 * @param tag
	 * @return
	 */
	public Integer update(Tag tag);
	/**
	 * 删除一个标签
	 * @param tag
	 * @return
	 */
	public Integer delete(Integer id);
}
