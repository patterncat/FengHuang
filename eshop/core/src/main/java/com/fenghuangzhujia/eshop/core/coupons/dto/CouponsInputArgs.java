package com.fenghuangzhujia.eshop.core.coupons.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public class CouponsInputArgs extends DtoBaseModel {

	private String userId;
	private String name;
	private Double couponsMoney;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expireTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCouponsMoney() {
		return couponsMoney;
	}
	public void setCouponsMoney(Double couponsMoney) {
		this.couponsMoney = couponsMoney;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
}
