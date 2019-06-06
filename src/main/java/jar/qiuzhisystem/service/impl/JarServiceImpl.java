package jar.qiuzhisystem.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jar.qiuzhisystem.dao.JarDao;
import jar.qiuzhisystem.entity.Jar;
import jar.qiuzhisystem.service.JarService;
/**
 * JarService接口的实现类
 * @author 12952
 *
 */
@Service("jarService")
public class JarServiceImpl implements JarService{
	@Resource 
	private JarDao jarDao;
	public Jar findById(String uuid) {
		return jarDao.findById(uuid);
	}
	public Integer update(Jar jar) {
		return jarDao.update(jar);
	}
	public List<Jar> list(Map<String, Object> map) {
		return jarDao.list(map);
	}
	public Long getTotal(Map<String, Object> map) {
		return jarDao.getTotal(map);
	}
	public Integer delete(String id) {
		return jarDao.delete(id);
	}
	public Integer add(Jar jar) {
		return jarDao.add(jar);
	}

}
