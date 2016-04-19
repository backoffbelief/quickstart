package com.kael.websocket.msg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PacketManager {

	private static Map<Short, String> packets = new HashMap<Short, String>();

	private static Map<String, Short> packets_v_k = new HashMap<String, Short>();

	public static void register(Set<Class<?>> clazzes) {
		for(Class c : clazzes){
			if(c.isAnnotationPresent(PacketMsg.class)){
				PacketMsg annotation = (PacketMsg) c.getAnnotation(PacketMsg.class);
				packets.put(annotation.code(), c.getName());
				packets_v_k.put(c.getName(), annotation.code());
			}
			
		}
	}
	
	public static short getCode(String clsName){
		return packets_v_k.get(clsName);
	}

}
