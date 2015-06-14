package com.fenghuangzhujia.eshop.appoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.eshop.appoint.dto.AppointInputArgs;
import com.fenghuangzhujia.eshop.common.remind.impl.DtoUnreadRemindSpecificationService;
import com.fenghuangzhujia.eshop.core.base.Dics;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Service
@Transactional
public class AppointService extends DtoUnreadRemindSpecificationService<Appoint, AppointDto, AppointInputArgs, String> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	@Autowired
	private MessageManager messageManager;
	
	/**
	 * 为用户预约设置单独的方法，将业务逻辑封装在里面
	 * @param userid
	 * @param areaid
	 * @param mobile
	 * @return
	 */
	public AppointDto appointByUser(AppointInputArgs args) {
		Appoint appoint=new Appoint();
		User user=userRepository.findOne(args.getUserid());
		if(user==null)throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户id错误");
		//用户信息完整才能预约
		if(!user.getInfoComplete())throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户信息不完整，不能预约");
		appoint.setUser(user);
		//暂时只允许用户用绑定手机号码预约，不能更换手机号。如果以后业务逻辑调整，这里要做相应调整。
		String mobile=user.getMobile();
		//检验短信验证码
		messageManager.validate(mobile, args.getValidater());
		appoint.setMobile(mobile);
		CategoryItem type=categoryItemRepository.findOne(args.getTypeid());
		if(type==null || !type.getType().equals(Dics.APPOINT_TYPE))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "预约类型错误");
		appoint.setType(type);
		appoint=getRepository().save(appoint);
		return adapter.convertToDetailedDto(appoint);
	}
	
	public PagedList<AppointDto> findByTypeId(String typeid, int page, int size) {
		PageRequest pageable=new PageRequest(page, size);
		Page<Appoint> list=getRepository().findByTypeId(typeid, pageable);
		Page<AppointDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<AppointDto> findByUserId(String userid, int page, int size) {
		PageRequest pageable=new PageRequest(page, size);
		Page<Appoint> list=getRepository().findByUserId(userid, pageable);
		Page<AppointDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public AppointDto getUserAppoint(String userid, String id) {
		Appoint appoint=getRepository().findOne(id);
		if(!appoint.getUser().getId().equals(userid))return null;
		return adapter.convert(appoint);
	}
	
	@Autowired
	public void setAppointRepository(AppointRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public AppointRepository getRepository() {
		return (AppointRepository)super.getRepository();
	}
}
