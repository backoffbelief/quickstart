package com.kael.model;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class Barrack {
	// 士兵数量
	@Protobuf
    private long footmanNum;
	@Protobuf
    private long riderNum;
	@Protobuf
    private long archerNum;
	@Protobuf
    private long specialNum;
	public long getFootmanNum() {
		return footmanNum;
	}
	public void setFootmanNum(long footmanNum) {
		this.footmanNum = footmanNum;
	}
	public long getRiderNum() {
		return riderNum;
	}
	public void setRiderNum(long riderNum) {
		this.riderNum = riderNum;
	}
	public long getArcherNum() {
		return archerNum;
	}
	public void setArcherNum(long archerNum) {
		this.archerNum = archerNum;
	}
	public long getSpecialNum() {
		return specialNum;
	}
	public void setSpecialNum(long specialNum) {
		this.specialNum = specialNum;
	}
	
}
