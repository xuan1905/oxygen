package com.spring.oxygen.pool;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
	@Autowired
	ResourceRepository repository;
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
			repository.updatePool(request.getPoolId(), combinedValues);;
			
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

	public int getTeacherDetails(int id) {
		return repository.getPoolDetail(id);
	}

}
