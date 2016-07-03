package main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
	
	public static void main(String[] args) {
		
		final BlockingQueue<Packet> queue = new PriorityBlockingQueue<>();
		final int numPackets = 100000;
		
		new Thread(new Producer(queue, numPackets)).start(); //producer
		new Thread(new Router(queue, numPackets)).start(); //consumer
	}

}

