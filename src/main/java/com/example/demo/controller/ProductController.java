package com.example.demo.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Product")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseEntity<?> productPegeable(@RequestParam(required = true, defaultValue = "0") int page,
			@RequestParam(required = true, defaultValue = "10") @Min(value = 10, message = "size min 10") @Max(value = 100, message = "size max 100") int size,
			@RequestParam(required = true, defaultValue = "id") String sort) {
		
		
		if (size < 10 || size > 100 || size%10 != 0 ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("inputan size harus min 10, max 100 dan kelipatan 10.");
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		
		Page<ProductEntity> result = productRepo.findAll(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(result);


	}

}