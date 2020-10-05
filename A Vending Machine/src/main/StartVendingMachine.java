package main;

import javax.swing.JFrame;
import gui.VendingMachine;

public class StartVendingMachine {

	// Main method to start application
	public static void main(String[] args) {
		JFrame frame = new VendingMachine();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 20, 500, 800);
		frame.setTitle("A Vending Machine");
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
