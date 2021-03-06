package com.fenghuangzhujia.eshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.materialManage.brand.BrandService;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandVo;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class MaterialController {
	
	@Autowired
	private BrandService brandService;

	@RequestMapping(value="materials",method=RequestMethod.GET)
	public String materials() {
		List<BrandVo> result=brandService.getDetailedBrands();
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="product/package/{id}/brands",method=RequestMethod.GET)
	public String packageBrands(@PathVariable String id) {
		List<BrandVo> result=brandService.findByPackage(id);
		return RequestResult.success(result).toJson();
	}
}
