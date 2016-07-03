package main;

import java.util.concurrent.BlockingQueue;

public class Router implements Runnable {
	
	private BlockingQueue<Packet> queue;
	private int numPackets;
	
	Router(BlockingQueue<Packet> queue, int numPackets) {
		this.queue = queue;
		this.numPackets = numPackets;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int cnt = 0;
		while(cnt < numPackets) {
			try {
				if (!queue.isEmpty()) {
					cnt++;
					final Packet packet = queue.take();
					new Thread() {
						public void run() {
							try {
								processing(packet);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}.start();
				}
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void processing(Packet packet) throws InterruptedException {
		System.out.println("Processing: " + packet);
		Thread.sleep(packet.getSize() * 100);
		System.out.println("End processing: " + packet);
	}
	
}
