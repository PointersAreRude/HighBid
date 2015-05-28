package View;

import java.util.ArrayList;

import javax.swing.JFrame;

import Model.Item;

public class StatsFrameTemp<E> extends JFrame {

	private final int WIDTH = 1200;
	
	private final int HEIGHT = 800;
	
	StatsPanel<E> myPanel;
	
	ArrayList<E> myList;
	
	public StatsFrameTemp() {
		setTitle("Stats");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createList();
		myPanel = new StatsPanel<E>(myList);
		add(myPanel);
		
		setUp();
		//pack();
	}
	
	private void setUp() {
		
	}
	
	@SuppressWarnings("unchecked")
	private void createList() {
		myList = (ArrayList<E>) new ArrayList<Item>();
		myList.add((E) new Item("Speakers", "Some Speakers", 20, 60, 98989898));
		myList.add((E) new Item("Pillow", "A cozy blue pillow!", 5, 10, 76535796));
		myList.add((E) new Item("Laptop", "Durable, long battery life, and a sleek case!", 50, 500, 112334534));
		myList.add((E) new Item("Pen Set", "Black ink pens.", 2, 10, 556212354));
		myList.add((E) new Item("Rubber Ducky", "A cute, yellow, squeeky ducky!", 1, 5, 7655432));
		myList.add((E) new Item("Fresh apples", "a description", 20, 60, 78369));
		myList.add((E) new Item("Teddy Bear", "beeeeeeeees", 20, 60, 8989875));
		myList.add((E) new Item("Cornbread", "Some Speakers", 20, 60, 4000009));
		myList.add((E) new Item("Dog Collor", "", 20, 60, 332211));
		myList.add((E) new Item("Elephant statue", "", 20, 60, 432211));
		myList.add((E) new Item("Froyo Maker", "", 20, 60, 54545454));
		myList.add((E) new Item("Giant Cheese Wheel", "", 20, 60, 876641));
		myList.add((E) new Item("Hair Salon Coupons", "", 20, 60, 397544));
		myList.add((E) new Item("Igloo Maker", "", 20, 60, 543578000));
		myList.add((E) new Item("Juggler's Guide for Dummy's", "", 20, 60, 300098));
		myList.add((E) new Item("Kangaroo tracker", "", 20, 60, 287521));
		myList.add((E) new Item("Nail Salon tickets", "", 20, 60, 452413));
		myList.add((E) new Item("Opera Tickets", "", 20, 60, 52314111));
		myList.add((E) new Item("Purse", "", 20, 60, 32456547));
		myList.add((E) new Item("IQ Test", "", 20, 60, 45455767));
		myList.add((E) new Item("Pet Frog", "", 20, 60, 777777));
		myList.add((E) new Item("Rock-em Sock-em Robots game", "", 20, 60, 65656677));
		myList.add((E) new Item("Sailboat", "", 20, 60, 8775421));
		myList.add((E) new Item("Truffles", "", 20, 60, 01010100));
		myList.add((E) new Item("Umbrella", "", 20, 60, 65543111));
		myList.add((E) new Item("Violin", "", 20, 60, 39398279));
		myList.add((E) new Item("Wallet", "", 20, 60, 878909));
		myList.add((E) new Item("Xyliphone", "", 20, 60, 651123));
		myList.add((E) new Item("Zebra plushie", "", 20, 60, 33445643));
	}
	
}