package com.spring.oxygen.pool;

public class PoolSaveRequest {
	private int poolId;
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
