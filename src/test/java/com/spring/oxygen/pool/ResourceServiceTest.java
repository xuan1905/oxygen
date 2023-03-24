package com.spring.oxygen.pool;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.Matchers.equalTo;

import com.spring.oxygen.BaseUnitTest;
import com.spring.oxygen.pool.contract.PoolSaveRequest;
import com.spring.oxygen.pool.contract.PoolSaveResponse;
import com.spring.oxygen.pool.domain.Pool;
import com.spring.oxygen.pool.repository.ResourceRepository;
import com.spring.oxygen.pool.service.ResourceService;

public class ResourceServiceTest extends BaseUnitTest {
	@Mock
	private ResourceRepository repo;
	
	@InjectMocks
	private ResourceService svc;
	
	@BeforeEach
	void setup() {
	}
	
	@Test
	void savePoolAppendTest() {
		PoolSaveRequest request = new PoolSaveRequest();
		request.setPoolId(123);
		int[] mockValues = {1, 2, 1};
		request.setPoolValues(mockValues);
		
		Pool p = new Pool();
		p.setId(123);
		p.setValues(new int[] {6, 2, 9});
		when(repo.getPool(request.getPoolId())).thenReturn(p);
		int[] mockCombinedValues = {1, 2, 1, 6, 2, 9};
		verify(repo, times(0)).updatePool(request.getPoolId(), mockCombinedValues);
	
		PoolSaveResponse res = new PoolSaveResponse();
		res.setStatus("appended");
		assertAll(
				() -> assertThat(svc.savePool(request).getStatus(), equalTo(res.getStatus()))
				);
		
	}
}
