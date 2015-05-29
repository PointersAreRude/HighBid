package View;

import java.util.ArrayList;

import javax.swing.JFrame;

import Model.Bidder;
import Model.Donor;
import Model.Item;

public class StatsFrameTemp<E> extends JFrame {

	private final int WIDTH = 1200;
	
	private final int HEIGHT = 800;
	
	StatsPanel<E> myPanel;
	
	ArrayList<E> myItemList;
	ArrayList<E> myBidderList;
	ArrayList<E> myDonorList;
	
	public StatsFrameTemp() {
		setTitle("Stats");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//createLists();
		myItemList = (ArrayList<E>) new ArrayList<Item>();
		myPanel = new StatsPanel();
		myPanel.createList(myItemList);
		add(myPanel);
		
	}
	
	@SuppressWarnings("unchecked")
	private void createLists() {
		myItemList = (ArrayList<E>) new ArrayList<Item>();
		myBidderList = (ArrayList<E>) new ArrayList<Bidder>();
		myDonorList = (ArrayList<E>) new ArrayList<Donor>();
		
		Item itm1 = new Item("Speakers", "These speakers are equipped with ear splitting base technology that will"
				+ " shatter all windows in a 30 foot radius!  Super durable, super loud, and super awesome!", 20, 60, 98989898);
		Item itm2 = new Item("Laptop", "Durable, long battery life, and a sleek case!", 50, 500, 112334534);
		Item itm3 = new Item("Giant Cheese Wheel", "It's yellow, and holy is it big!  Good for up to 1000 crackers!  Or to garnish you're wine cellar!", 15, 30, 876641);
		Item itm4 = new Item("Froyo Maker", "For all your froyo cravings!  Just put in any flavor of base you want and voila, you got froyo!  "
				+ "*Froyo base, toppings, bowls, spoons, batteries, box, and ice not included*", 10, 50, 54545454);
		Item itm5 = new Item("Juggler's Guide for Dummies", "Tired of dropping balls all over the floor?  Want to impress your friends at a party? "
				+ " Don't have time to learn how to juggle?  Are you a dummy?  Well then this is the book for you!  Learn to juggle in one hour!", 5, 15, 300098);
		Item itm6 = new Item("Pillow", "A cozy blue pillow to rest your pretty little head on!", 5, 10, 76535796);
		Item itm7 = new Item("Rubber Ducky", "If it looks like a duck, swims like a duck, and quacks like a duck... it's probably not a rubber ducky.", 1, 5, 7655432);
		Item itm8 = new Item("Rock-em Sock-em Robots Game", "It's a blast from the past!  Try to knock out your opponenet's robot, or get knocked out yourself!", 5, 20, 65656677);
		Item itm9 = new Item("Pen Set", "Black ink pens, for everyday use (or sticking to the ceiling of your classroom).", 2, 10, 556212354);
		Item itm10 = new Item("Opera Tickets", "For all you opera fans, come and listen to the fat lady sing!", 20, 60, 52314111);
		
		Bidder bddr1 = new Bidder("Sally", "Seashore", "seashellSeller@oceans.com", "879 Beaches Ave, Portland, OR", 
				"byTheShore", "123-456-Shells");
		bddr1.setid(999999);
		Bidder bddr2 = new Bidder("Peter", "Piper", "pepperPicker@pickles.com", "7777 Pickle st, Pepper Fields, TX", 
				"pickledPeppers", "573-990-Peck");
		bddr2.setid(666);
		Bidder bddr3 = new Bidder("Optimus", "Prime", "letsroll@autobots.com", "The Ark, OR", "FlameTruck", "489-000-auto");
		bddr3.setid(877676);
		Bidder bddr4 = new Bidder("Frank", "Sinatra", "flymetothemoon@singers.com", "90000 7th st, New York, NY", "SmoothTalker", "123-456-7890");
		bddr4.setid(0101001);
		Bidder bddr5 = new Bidder("Purdy", "Bird", "tweety@inacage.com", "344 Mongolia Ave, Hot Springs, CA", "Singing in the cage", "000-111-2222");
		bddr5.setid(2222222);
		Bidder bddr6 = new Bidder("Steve", "Harvey", "xxx@FamilyFued.com", "Hollywood", "Talk Show Host", "900-887-xxxx");
		bddr6.setid(8181888);
		
		Donor dnr1 = new Donor("Toys r We", "Company", "getSomeToys@rus.com", "3444 7th St Provo, UT", "677-100-9898");
		Donor dnr2 = new Donor("Joe", "Smoe", "IveGotStuff@yahoo.com", "2334 3rd Ave, Seattle, WA", "360-992-7889");
		Donor dnr3 = new Donor("Imma", "Donater", "donatestuff@whatever.com", "100 2nd way, aPlace, nowhere", "966-000-0002");
		Donor dnr4 = new Donor("Betty", "Crocker", "gotFood@cakes.com", "888 9th st, Phoenix, AZ", "765-432-1000");
		
		itm1.setDonor(dnr3);
		dnr3.add(itm1);
		itm3.setDonor(dnr4);
		dnr4.add(itm3);
		itm4.setDonor(dnr4);
		dnr4.add(itm4);
		itm5.setDonor(dnr2);
		dnr2.add(itm5);
		itm6.setDonor(dnr1);
		dnr1.add(itm6);
		itm7.setDonor(dnr1);
		dnr1.add(itm7);
		itm8.setDonor(dnr1);
		dnr1.add(itm8);
		itm9.setDonor(dnr2);
		dnr2.add(itm9);
		itm10.setDonor(dnr3);
		dnr3.add(itm10);
		
		bddr3.placeBid(itm8);
		bddr4.placeBid(itm1);
		bddr6.placeBid(itm8);
		bddr3.placeBid(itm8);
		bddr2.placeBid(itm3);
		bddr2.placeBid(itm4);
		bddr5.placeBid(itm3);
		bddr6.placeBid(itm9);
		bddr1.placeBid(itm5);
		bddr6.placeBid(itm8);
		bddr1.placeBid(itm1);
		bddr4.placeBid(itm1);
		bddr3.placeBid(itm8);
		bddr1.placeBid(itm10);
		bddr5.placeBid(itm6);
		bddr6.placeBid(itm5);
		
		bddr3.addItemWon(itm8);
		bddr4.addItemWon(itm1);
		bddr5.addItemWon(itm3);
		bddr2.addItemWon(itm4);
		bddr6.addItemWon(itm9);
		bddr6.addItemWon(itm5);
		bddr1.addItemWon(itm10);
		
		myItemList.add((E) itm1);
		myItemList.add((E) itm2);
		myItemList.add((E) itm3);
		myItemList.add((E) itm4);
		myItemList.add((E) itm5);
		myItemList.add((E) itm6);
		myItemList.add((E) itm7);
		myItemList.add((E) itm8);
		myItemList.add((E) itm9);
		myItemList.add((E) itm10);
		
		myBidderList.add((E) bddr1);
		myBidderList.add((E) bddr2);
		myBidderList.add((E) bddr3);
		myBidderList.add((E) bddr4);
		myBidderList.add((E) bddr5);
		myBidderList.add((E) bddr6);
		
		myDonorList.add((E) dnr1);
		myDonorList.add((E) dnr2);
		myDonorList.add((E) dnr3);
		myDonorList.add((E) dnr4);
		
//		myList.add((E) new Item("Fresh apples", "a description", 20, 60, 78369));
//		myList.add((E) new Item("Teddy Bear", "beeeeeeeees", 20, 60, 8989875));
//		myList.add((E) new Item("Cornbread", "Some Speakers", 20, 60, 4000009));
//		myList.add((E) new Item("Dog Collor", "", 20, 60, 332211));
//		myList.add((E) new Item("Elephant statue", "", 20, 60, 432211));
//		myList.add((E) new Item("Hair Salon Coupons", "", 20, 60, 397544));
//		myList.add((E) new Item("Igloo Maker", "", 20, 60, 543578000));
//		myList.add((E) new Item("Kangaroo tracker", "", 20, 60, 287521));
//		myList.add((E) new Item("Nail Salon tickets", "", 20, 60, 452413));
//		myList.add((E) new Item("Opera Tickets", "", 20, 60, 52314111));
//		myList.add((E) new Item("Purse", "", 20, 60, 32456547));
//		myList.add((E) new Item("IQ Test", "", 20, 60, 45455767));
//		myList.add((E) new Item("Pet Frog", "", 20, 60, 777777));
//		myList.add((E) new Item("Sailboat", "", 20, 60, 8775421));
//		myList.add((E) new Item("Truffles", "", 20, 60, 01010100));
//		myList.add((E) new Item("Umbrella", "", 20, 60, 65543111));
//		myList.add((E) new Item("Violin", "", 20, 60, 39398279));
//		myList.add((E) new Item("Wallet", "", 20, 60, 878909));
//		myList.add((E) new Item("Xyliphone", "", 20, 60, 651123));
//		myList.add((E) new Item("Zebra plushie", "", 20, 60, 33445643));
	}
	
}