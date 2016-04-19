package com.kael.websocket.msg;

import com.alibaba.druid.support.json.JSONUtils;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public abstract class Packet {

	@Protobuf
	protected short code;

	public short getCode() {
		return code;
	}

	public void setCode(short code) {
		this.code = code;
	}

	public Packet() {
		this.code = PacketManager.getCode(this.getClass().getName());
	}
	
	public String toString(){
		return JSONUtils.toJSONString(this);
	}
}
