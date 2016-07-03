package main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
	
	private BlockingQueue<Packet> queue;
	private int numPackets;
	
	Producer(BlockingQueue<Packet> queue, int numPackets) {
		this.queue = queue;
		this.numPackets = numPackets;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < numPackets; i++) {
			int size = ThreadLocalRandom.current().nextInt(Packet.MIN_SIZE, Packet.MAX_SIZE);
			int priority = ThreadLocalRandom.current().nextInt(Packet.MIN_PRIORITY, Packet.MAX_PRIORITY);
			try {
				Packet packet = Packet.packetsFactory(size, priority);
				System.out.println("Добавлен в очередь: " + packet);
				queue.put(packet);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
