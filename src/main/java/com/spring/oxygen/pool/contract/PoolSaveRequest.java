package com.spring.oxygen.pool.contract;

import javax.validation.constraints.NotNull;


public class PoolSaveRequest {
	@NotNull
	private int poolId;
	@NotNull
	private int[] poolValues;
	
	public int getPoolId() {
		return poolId;
	}
	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}
	public int[] getPoolValues() {
		return poolValues;
	}
	public void setPoolValues(int[] poolValues) {
		this.poolValues = poolValues;
	}
}
