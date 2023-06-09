package com.spring.oxygen.pool.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.oxygen.pool.contract.PoolQueryRequest;
import com.spring.oxygen.pool.contract.PoolQueryResponse;
import com.spring.oxygen.pool.contract.PoolSaveRequest;
import com.spring.oxygen.pool.contract.PoolSaveResponse;
import com.spring.oxygen.pool.domain.Pool;
import com.spring.oxygen.pool.repository.ResourceRepository;

import cern.colt.list.DoubleArrayList;
import cern.jet.stat.Descriptive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ResourceService {
	@Autowired
	ResourceRepository repository;
	
	Logger logger = LoggerFactory.getLogger(ResourceService.class);
	private static final String APPEND_STATUS = "appended";
	private static final String INSERT_STATUS = "inserted";
	
	
	public PoolSaveResponse savePool(PoolSaveRequest request) {
		PoolSaveResponse res = new PoolSaveResponse();
		Pool p = repository.getPool(request.getPoolId());
		Pool pool = new Pool();
		boolean isIdExists = (p == null) ? false : true;
		if (isIdExists) {
			int[] oldValues = p.getValues();
			int[] newValues = request.getPoolValues();
			int oldLen = oldValues.length;
			int newLen = newValues.length;
			int sourcePos = 0;
			int[] combinedValues = Arrays.copyOf(oldValues, oldLen + newLen);			
			System.arraycopy(newValues, sourcePos, combinedValues, oldLen, newLen);
			
			pool.setId(request.getPoolId());
			pool.setValues(combinedValues);
			repository.updatePool(request.getPoolId(), combinedValues);
			
			res.setStatus(APPEND_STATUS);
			return res;
		} else {			
			pool.setId(request.getPoolId());
			pool.setValues(request.getPoolValues());
			repository.savePool(pool);
			
			res.setStatus(INSERT_STATUS);
			return res;
		}

	}

	public PoolQueryResponse getQuantile(PoolQueryRequest req) {
		int breakLine = 100;
		PoolQueryResponse res = new PoolQueryResponse();
		Pool p = repository.getPool(req.getPoolId());
		int[] values = p.getValues();
		int length = values.length;
		float percentile = req.getPercentile() / 100;
		if (length <= breakLine) {
		int quantileIndex = this.getQuantileIndex(length, percentile);
		Arrays.sort(values);
		int quantile = values[quantileIndex];
		
		res.setQuantile(quantile);
		res.setCount(length);
		return res;	
		} else {
			DoubleArrayList list = new DoubleArrayList();
			Arrays.stream(values).forEach(i -> list.add((double) i));
			int quantile = (int) Descriptive.quantile(list, percentile);
			
			res.setQuantile(quantile);
			res.setCount(length);
			return res;	
		}
	}
	
	private int getQuantileIndex(Integer length, Float percentile) {
		int indexOffset = 1;
		Float converstionUnit = 1F;
		Float floatedLength = length*converstionUnit;
		Float quantile = percentile * (floatedLength + converstionUnit);
		int roundedQuantile = (int) Math.floor(quantile);
		logger.debug("Debug: " + floatedLength + ", " + percentile+ ", " +  quantile + ", " + roundedQuantile);
		return roundedQuantile - indexOffset;
	}

}
