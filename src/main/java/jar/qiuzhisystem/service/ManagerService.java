package jar.qiuzhisystem.service;

import jar.qiuzhisystem.entity.Manager;

/**
 * 管理员service接口
 * @author 12952
 *
 */
public interface ManagerService {
	public Manager getByUserName(String userName);
	/**
	 * 更新管理员信息
	 * @param manager
	 * @return
	 */
	public Integer update(Manager manager);
}
