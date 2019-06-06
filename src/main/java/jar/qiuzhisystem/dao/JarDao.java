package jar.qiuzhisystem.dao;

import java.util.List;
import java.util.Map;

import jar.qiuzhisystem.entity.Jar;

/**
 * jar dao接口
 * @author 12952
 *
 */
public interface JarDao {
	
	/**
	 * 修改访问次数，点击次数
	 * @param jar
	 * @return
	 */
	public Integer update(Jar jar);
	/**
	 * 通过id查找实体
	 * @param uuid
	 * @return
	 */
	public Jar findById(String uuid);
	/**
	 * 根据条件分页查询jar信息
	 * @param map
	 * @return
	 */
	public List<Jar> list(Map<String, Object> map);
	/**
	 * 获取总记录条数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
	/**
	 * 删除jar包
	 * 
	 * @param id
	 * @return
	 */
	public Integer delete(String id);
	/**
	 * 添加jar包
	 * @param jar
	 * @return
	 */
	public Integer add(Jar jar);
}
