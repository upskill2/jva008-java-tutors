package com.luxoft.jva008.module04;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

/*
 * Implement the compareTo() method in order. Print orders in ReadOrderThread so that order with priority==true is the first.
 */

public class PriorityQueueTutor {
    static StringBuffer buf = new StringBuffer();

	PriorityBlockingQueue<Order> orderQueue = new PriorityBlockingQueue<>();

	class Order implements Comparable<Order> {
		public String title;
		public boolean priority;

		@Override
		public String toString() {
			return "Order " + title + ", priority=" + priority;
		}

		public Order(String title, boolean priority) {
			this.title = title;
			this.priority = priority;
		}

		@Override
		public int compareTo(Order o) {
			return o.priority ? 1 : -1;
		}

	}

	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class AddOrderThread implements Runnable {
		@Override
		public void run() {
			orderQueue.put(new Order("books",false));
			sleep(10);
			orderQueue.put(new Order("table",false));
			sleep(10);
			orderQueue.put(new Order("computer",true));
			sleep(10);
			orderQueue.put(new Order("dog",false));

			System.out.println(orderQueue);
		}
	}

	class ReadOrderThread implements Runnable {
		int orderNum = 0;
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			while(orderNum<4) {
				try {
					Order order = orderQueue.take();
					// check that first taken order has priority==true
					if (order.priority && orderNum==0) {
						priorityAhead = true;
						log(order.toString());

					}
					orderNum++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

    static void log(String s) {
        buf.append(s + "\n");
    }

	boolean priorityAhead = false;

	@Test
	public void testName() throws Exception {
		Thread addOrderThread = new Thread(new AddOrderThread());
		Thread readOrderThread = new Thread(new ReadOrderThread());
		addOrderThread.start();
		/**
		 * TODO: we should wait while orders will appear in the list,
		 * otherwise we will read orders in order it was added to the queue
		 */


		readOrderThread.start();

		addOrderThread.join();
		readOrderThread.join();

		assertTrue("Order marked as priority should be the first", priorityAhead);
	}


}
