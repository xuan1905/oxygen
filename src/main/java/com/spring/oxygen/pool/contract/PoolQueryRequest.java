package com.spring.oxygen.pool.contract;

import javax.validation.constraints.NotNull;

public class PoolQueryRequest {
	@NotNull
	private int poolId;
	@NotNull
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
