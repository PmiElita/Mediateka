package com.mediateka.pair;

public class PathCountPair {
	private String path;
	private Integer count;
	
	public PathCountPair(String path,Integer count ){
		this.path = path;
		this.count = count;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	
}
