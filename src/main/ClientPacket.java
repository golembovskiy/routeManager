package main;

public class ClientPacket extends Packet {
	
	{
		packetType = CLIENT_PACKET;
	}

	public ClientPacket(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}
	
	public ClientPacket(int size, int priority) {
		super(size, priority);
	}

}
