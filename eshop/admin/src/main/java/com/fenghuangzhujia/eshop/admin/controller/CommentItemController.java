package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.comment.CommentItemService;
import com.fenghuangzhujia.eshop.comment.dto.CommentItemDto;
import com.fenghuangzhujia.eshop.comment.dto.CommentItemInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping("commentItem")
public class CommentItemController extends SpecificationController<CommentItemDto,CommentItemInputArgs> {

	@Autowired
	private CommentItemService service;
	
	@Override
	protected CommentItemService getService() {
		return service;
	}
}
