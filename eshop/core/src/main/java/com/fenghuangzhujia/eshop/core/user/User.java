package com.fenghuangzhujia.eshop.core.user;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import com.fenghuangzhujia.eshop.core.authentication.authority.Authority;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.foundation.core.entity.UUIDBaseModel;
import com.fenghuangzhujia.foundation.core.enums.BloodType;
import com.fenghuangzhujia.foundation.core.enums.CardType;
import com.fenghuangzhujia.foundation.core.enums.Constellation;
import com.fenghuangzhujia.foundation.core.enums.Sex;
import com.fenghuangzhujia.foundation.media.MediaContent;

@Entity
public class User extends UUIDBaseModel implements UserDetails {
	private static final long serialVersionUID = 8173403045355645001L;
	
	private String username;	
	private String password;	
	private Set<Role> roles;
	private Boolean verified;
	private String salt;
	
	/**
	 * @return the username
	 */
	@Column(name="account",unique=true,updatable=false)
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="verified")
	public Boolean isVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	/**
	 * @return the roles
	 */
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="users")
	public Set<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	/**
	 * 通过是否通过认证给予权限
	 */
	@Transient
	@Override
	public boolean isEnabled() {
		return verified;
	}	
	
	private Set<Authority> authorities;
	/**
	 * @return the authorities
	 * never <code>null</code>
	 */
	@Transient
	@Override
	public Set<Authority> getAuthorities() {
		return authorities==null?Collections.<Authority>emptySet():authorities;
	}
	/**
	 * 在认证之前，服务层要保证authorities被赋值
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	private String[] roleids;
	/**
	 * 方便保存时传值
	 * @return the roleids
	 */
	@Transient
	public String[] getRoleids() {
		return roleids;
	}
	/**
	 * @param roleids the roleids to set
	 */
	public void setRoleids(String[] roleids) {
		this.roleids = roleids;
	}
	
	/**
	 * 加密盐
	 * @return
	 */
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	//授权检测过程无关属性定义
	private String question;
	private String answer;
	private String cnname;
	private String ename;
	private MediaContent avatar;
	private Sex sex;
	private Date birthDay;
	private Constellation astro;
	private BloodType bloodType;
	private String trade;
	private String liveProv;
	private String liveCity;
	private String homeCountry;
	private CardType cardType;
	private String cardNum;
	private String intro;
	private String email;
	private String qqnum;
	private String mobile;
	private String telephone;
	private String addressProv;
	private String addressCity;
	private String addressCountry;
	private String address;
	private String zipcode;
	private Long expVal;
	private Long integra;
	private Date regTime;
	private String regIp;
	private Date loginTime;
	private String loginip;
	private String qqid;
	private String weiboid;
	private String weixinid;

	/**
	 * 提问
	 * @return
	 */
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * 回答
	 * @return
	 */
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * 中文名
	 * @return
	 */
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	
	/**
	 * 英文名
	 * @return
	 */
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	/**
	 * 头像
	 * @return
	 */
	@OneToOne
	public MediaContent getAvatar() {
		return avatar;
	}
	public void setAvatar(MediaContent avatar) {
		this.avatar = avatar;
	}
	
	@Transient
	public String getAvatarUrl() {
		if(avatar==null)return null;
		return avatar.getUrl();
	}
	
	/**
	 * 性别
	 * @return
	 */
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	/**
	 * 生日
	 * @return
	 */
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	/**
	 * 星座
	 * @return
	 */
	public Constellation getAstro() {
		return astro;
	}
	public void setAstro(Constellation astro) {
		this.astro = astro;
	}
	
	/**
	 * 血型
	 * @return
	 */
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
	
	/**
	 * 行业
	 * @return
	 */
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	
	/**
	 * 居住省
	 * @return
	 */
	public String getLiveProv() {
		return liveProv;
	}
	public void setLiveProv(String liveProv) {
		this.liveProv = liveProv;
	}
	
	/**
	 * 居住城市
	 * @return
	 */
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	
	/**
	 * 国家
	 * @return
	 */
	public String getHomeCountry() {
		return homeCountry;
	}
	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}
	
	/**
	 * 证件类型
	 * @return
	 */
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	/**
	 * 证件号码
	 * @return
	 */
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	/**
	 * 个人说明
	 * @return
	 */
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	/**
	 * 邮箱
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * QQ号码
	 * @return
	 */
	public String getQQnum() {
		return qqnum;
	}
	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}
	
	/**
	 * 手机号码
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 固定电话
	 * @return
	 */
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * 通信地址-省
	 * @return
	 */
	public String getAddressProv() {
		return addressProv;
	}
	public void setAddressProv(String addressProv) {
		this.addressProv = addressProv;
	}
	
	/**
	 * 通信地址-城市
	 * @return
	 */
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	
	/**
	 * 通信地址-国家
	 * @return
	 */
	public String getAddressCountry() {
		return addressCountry;
	}
	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}
	
	/**
	 * 通信地址
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 邮编
	 * @return
	 */
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	/**
	 * 经验值
	 * @return
	 */
	public Long getExpVal() {
		return expVal;
	}
	public void setExpVal(Long expVal) {
		this.expVal = expVal;
	}
	
	/**
	 * 积分
	 * @return
	 */
	public Long getIntegra() {
		return integra;
	}
	public void setIntegra(Long integra) {
		this.integra = integra;
	}
	
	/**
	 * 注册时间
	 * @return
	 */
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	/**
	 * 注册地址
	 * @return
	 */
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	
	/**
	 * 登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	/**
	 * 登录ip
	 * @return
	 */
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	
	/**
	 * 绑定QQ
	 * @return
	 */
	public String getQQid() {
		return qqid;
	}
	public void setQQid(String qqid) {
		this.qqid = qqid;
	}
	
	/**
	 * 绑定微博
	 * @return
	 */
	public String getWeiboid() {
		return weiboid;
	}
	public void setWeiboid(String weiboid) {
		this.weiboid = weiboid;
	}
	
	/**
	 * 绑定微信
	 * @return
	 */
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
}
