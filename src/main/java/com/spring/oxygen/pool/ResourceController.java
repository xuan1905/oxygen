package com.spring.oxygen.pool;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.oxygen.pool.contract.PoolQueryRequest;
import com.spring.oxygen.pool.contract.PoolQueryResponse;
import com.spring.oxygen.pool.contract.PoolSaveRequest;
import com.spring.oxygen.pool.contract.PoolSaveResponse;
import com.spring.oxygen.pool.service.ResourceService;

@RestController
@RequestMapping("/pool")
public class ResourceController {

	@Autowired
	ResourceService resourceService;

	@PostMapping("/v1/update")
	public ResponseEntity<PoolSaveResponse> savePool(@Valid @RequestBody PoolSaveRequest request) {
		PoolSaveResponse res = resourceService.savePool(request);
		System.out.println("ID: " + request.getPoolId());
		return ResponseEntity.ok(res);
	}

	@PostMapping("/v1/query")
	public ResponseEntity<PoolQueryResponse> getTeacherDetails(@Valid @RequestBody PoolQueryRequest request) {
		PoolQueryResponse res = resourceService.getQuantile(request);
		return ResponseEntity.ok(res);
	}

}
