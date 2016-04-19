package com.kael.websocket.msg;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

@PacketMsg(code = 100)
public class HellWorldRequest extends Request {
	@Protobuf
	private int battleId;

	public HellWorldRequest() {
		super();
	}

	public int getBattleId() {
		return battleId;
	}

	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}

}
