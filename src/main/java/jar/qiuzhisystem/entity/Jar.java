package jar.qiuzhisystem.entity;

import java.util.Date;

/**
 * jar实体
 * @author 12952
 *
 */
public class Jar {
	private String uuid;
	private String name;//加标签
	private String noTagName;//不加标签
	private String path;//资源路径
	private Date updateDate;//更新日期
	private String type; //资源分类： jar包 source源码 doc 文档
	private Integer click;//点击次数
	private Integer downHit;//下载次数
	private Integer indexState;//索引状态：默认0 0未生成Lucene索引 1生成
	private Integer tagState;//tag生成状态 默认0  1生成
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
	}
	public Integer getDownHit() {
		return downHit;
	}
	public void setDownHit(Integer downHit) {
		this.downHit = downHit;
	}
	public Integer getIndexState() {
		return indexState;
	}
	public void setIndexState(Integer indexState) {
		this.indexState = indexState;
	}
	public Integer getTagState() {
		return tagState;
	}
	public void setTagState(Integer tagState) {
		this.tagState = tagState;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNoTagName() {
		return noTagName;
	}
	public void setNoTagName(String noTagName) {
		this.noTagName = noTagName;
	}
	
	
}
