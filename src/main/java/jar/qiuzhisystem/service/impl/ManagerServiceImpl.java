package jar.qiuzhisystem.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jar.qiuzhisystem.dao.ManagerDao;
import jar.qiuzhisystem.entity.Manager;
import jar.qiuzhisystem.service.ManagerService;
/**
 * 管理员service实现类
 * @author 12952
 *
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
	@Resource
	private ManagerDao managerDao;

	public Manager getByUserName(String userName) {
		return managerDao.getByUserName(userName);
	}

	public Integer update(Manager manager) {
		return managerDao.update(manager);
	}
	
}
