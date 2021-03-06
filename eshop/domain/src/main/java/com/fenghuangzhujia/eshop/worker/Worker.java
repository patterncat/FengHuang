package com.fenghuangzhujia.eshop.worker;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.dics.CategoryItem;

@Entity
@Table(name="fhzj_worker")
public class Worker extends UUIDBaseModel {

	private long expVal;
	private User user;
	/**工人姓名*/
	private String name;
	/**工种，字典类型worker*/
	private CategoryItem type;
	
	/**
	 * 工人经验值
	 * @return
	 */
	public long getExpVal() {
		return expVal;
	}
	public void setExpVal(long expVal) {
		this.expVal = expVal;
	}
	
	/**
	 * 唯一关联用户
	 * @return
	 */
	@OneToOne
	@JoinColumn(unique=true,nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToOne
	public CategoryItem getType() {
		return type;
	}
	public void setType(CategoryItem type) {
		this.type = type;
	}
}
