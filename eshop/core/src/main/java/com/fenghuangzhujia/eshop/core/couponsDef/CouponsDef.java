package com.fenghuangzhujia.eshop.core.couponsDef;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.coupons.Coupons;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Entity
@Table(name="fhzj_coupons_def")
public class CouponsDef extends UUIDBaseModel {

	/**触发优惠券分发的事件类型，唯一*/
	private String event;
	/**优惠券名称*/
	private String name;
	/**优惠券金额*/
	private Double money;
	/**总共优惠券数量*/
	private Integer remainCount=0;
	/**已经分发优惠券数量*/
	private Integer consumedCount=0;
	/**优惠券有效期截止时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expireTime;
	/**是否分发优惠券，true，则按照定义分发优惠券*/
	private boolean inUse;
	
	@Transient
	public Coupons generateCoupons(User user) {
		if(!inUse) {
			throw new ErrorCodeException(SystemErrorCodes.OTHER, "优惠券尚未开始发放");
		}
		Coupons coupons=new Coupons();
		coupons.setUser(user);
		coupons.setName(name);
		coupons.setCouponsMoney(money);
		coupons.setExpireTime(expireTime);
		
		//优惠券的类型即优惠券定义的事件类型
		coupons.setType(event);
		
		//判断数量限制
		if(remainCount!=null) {
			if(consumedCount==null)consumedCount=0;
			if(remainCount>consumedCount) {
				consumedCount=consumedCount+1;
			} else {
				throw new ErrorCodeException(SystemErrorCodes.OTHER, "本次优惠券已抢光");
			}
		}
		return coupons;
	}

	@Column(unique=true)
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isInUse() {
		return inUse;
	}
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	public Integer getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}
	public Integer getConsumedCount() {
		return consumedCount;
	}
	public void setConsumedCount(Integer consumedCount) {
		this.consumedCount = consumedCount;
	}
}
