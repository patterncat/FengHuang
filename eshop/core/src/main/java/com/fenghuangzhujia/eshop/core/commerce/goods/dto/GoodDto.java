package com.fenghuangzhujia.eshop.core.commerce.goods.dto;

import com.fenghuangzhujia.eshop.core.commerce.eshop.dto.ShopDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;

public abstract class GoodDto extends DtoBaseModel {
	private String name;
	private double price;
	private double realPrice;
	private ShopDto shop;
	private String type;
	private String mainPic;
	private String mobile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(double realPrice) {
		this.realPrice = realPrice;
	}
	public ShopDto getShop() {
		return shop;
	}
	public void setShop(ShopDto shop) {
		this.shop = shop;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
