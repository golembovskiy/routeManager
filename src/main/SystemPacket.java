package main;

public class SystemPacket extends Packet {
	
	{
		packetType = SYSTEM_PACKET;
	}

	public SystemPacket(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}
	
	public SystemPacket(int size, int priority) {
		super(size, priority);
	}

}
