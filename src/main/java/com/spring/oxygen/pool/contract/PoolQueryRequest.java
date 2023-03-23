package com.spring.oxygen.pool.contract;

public class PoolQueryRequest {
	private int poolId;
	private float percentile;
	
	public int getPoolId() {
		return poolId;
	}
	public void setPoolId(int poolId) {
		this.poolId = poolId;
	}
	public float getPercentile() {
		return percentile;
	}
	public void setPercentile(float percentile) {
		this.percentile = percentile;
	}
}
