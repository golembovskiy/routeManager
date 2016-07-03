package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Packet implements Comparable<Packet> {
	
	public static final int SYSTEM_PACKET = 1;
	public static final int CLIENT_PACKET = 2;
	
	public static final int MIN_SIZE = 1;
	public static final int MAX_SIZE = 10000;
	
	public static final int MIN_PRIORITY = 0;
	public static final int MAX_PRIORITY = 10;
	private static final int DEFAULT_PRIORITY = 5;
	
	protected int packetType;
	private int priority;
	private int size;
	private long addingTime = System.currentTimeMillis();
	
	public Packet(int size) {
		if (size < MIN_SIZE || size > MAX_SIZE)
			throw new IllegalArgumentException("Incorrect value: " + "\"" + size + "\"");
		this.size = size;
		
		priority = DEFAULT_PRIORITY;
	}
	
	public Packet(int size, int priority) {
		if (size < MIN_SIZE || size > MAX_SIZE)
			throw new IllegalArgumentException("Incorrect value: " + "\"" + size + "\"");
		this.size = size;
		
		this.priority = (priority < MIN_PRIORITY || priority > MAX_PRIORITY) ? DEFAULT_PRIORITY : priority;
	}
	
	public static Packet packetsFactory(int size) {
		return generateSystemPacket() ?
				new SystemPacket(size) : new ClientPacket(size);
	}
	
	public static Packet packetsFactory(int size, int priority) {
		return generateSystemPacket() ?
				new SystemPacket(size, priority) : new ClientPacket(size, priority);
	}
	
	/*
	 * The system packets will be created with 50% probability
	 */
	private static boolean generateSystemPacket() {
		int val = ThreadLocalRandom.current().nextInt(100);
		return val < 50;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public int compareTo(Packet p) {
		if (getClass() != p.getClass())
			return packetType == SYSTEM_PACKET ? -1 : 1;
		
		if (priority != p.priority)
			return p.priority - priority;
		
		if (size != p.size)
			return p.size - size;
		
		if (addingTime != p.addingTime)
			return addingTime < p.addingTime ? -1 : 1;
		
		return 0;
	}
	
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss.SSS");
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(addingTime);
		String strTime = simpleDateFormat.format(cal.getTime());
		
		StringBuilder strBuilder = new StringBuilder(
			packetType == SYSTEM_PACKET ?
					"system packet; " : "client packet; "
	    )
		.append("priority: " + priority + "; ")
	    .append("size: " + size + "; ")
		.append("time of creation: " + strTime);
		
		return strBuilder.toString();
	}
	
}
