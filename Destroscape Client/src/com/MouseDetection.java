package com;

final class MouseDetection implements Runnable {

	@Override
	public void run() {
		while (running) {
			synchronized (syncObject) {
				if (coordsIndex < 500) {
					coordsX[coordsIndex] = RSApplet.mouseX;
					coordsY[coordsIndex] = RSApplet.mouseY;
					coordsIndex++;
				}
			}
			try {
				Thread.sleep(50L);
			} catch (Exception _ex) {
			}
		}
	}

	public MouseDetection(Client client1) {
		syncObject = new Object();
		coordsY = new int[500];
		running = true;
		coordsX = new int[500];
	}

	public final Object syncObject;
	public final int[] coordsY;
	public boolean running;
	public final int[] coordsX;
	public int coordsIndex;
}
