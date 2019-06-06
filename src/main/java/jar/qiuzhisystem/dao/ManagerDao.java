package jar.qiuzhisystem.dao;

import jar.qiuzhisystem.entity.Manager;

/**
 * 管理员dao接口
 * @author 12952
 *
 */
public interface ManagerDao {
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Manager getByUserName(String userName);
	/**
	 * 更新管理员信息
	 * @param manager
	 * @return
	 */
	public Integer update(Manager manager);
}
