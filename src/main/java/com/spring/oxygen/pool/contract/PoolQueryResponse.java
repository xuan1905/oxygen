package com.spring.oxygen.pool.contract;

public class PoolQueryResponse {
	private int quantile;
	private int count;
	

	public int getQuantile() {
		return quantile;
	}
	public void setQuantile(int quantile) {
		this.quantile = quantile;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
