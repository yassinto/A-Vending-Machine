package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import dom.*;

public class VendingMachine extends JFrame implements ListSelectionListener, Change { // implements
																						// Change
																						// interface
																						// to
																						// calculate
																						// change
	// variables: components of the gui
	private JList sodaList;
	private JLabel sodaPicture, resultPicture, price, inserted, coin, changeCoins, yourProduct, yourChange;
	private JSplitPane sodaSelection;
	private JButton select, cent5, cent10, cent20, cent50, euro1, euro2, clear, pay;
	private JTextField priceAmount, insertedAmount;
	private JTextArea change;
	// variables: Soda objects array; object for selected soda; value of
	// inserted money
	private Soda products[];
	private Soda selectedSoda;
	private double insertValue = 0.0;

	public VendingMachine() {
		JPanel vendingPanel = new JPanel();
		vendingPanel.setLayout(null); // null Layout for designing without
										// constraints

		products = new Products().getProductList(); // provided soda products
													// pre-determined in
													// Products class

		// initialization of gui components
		sodaList = new JList(products);
		sodaPicture = new JLabel();
		sodaSelection = getSodaSelection(sodaList, sodaPicture);
		price = new JLabel("Price:");
		inserted = new JLabel("Inserted Amount:");
		yourProduct = new JLabel("Your Product");
		yourChange = new JLabel("Your Change");
		coin = new JLabel("Select your coins:");
		cent5 = new JButton();
		cent10 = new JButton();
		cent20 = new JButton();
		cent50 = new JButton();
		euro1 = new JButton();
		euro2 = new JButton();
		clear = new JButton("Clear");
		pay = new JButton("Pay");
		select = new JButton("Select this drink");
		priceAmount = new JTextField();
		insertedAmount = new JTextField();
		change = new JTextArea();
		resultPicture = new JLabel();
		changeCoins = new JLabel();

		// setting design for the labels
		setLabel(price);
		setLabel(inserted);
		setLabel(coin);
		setLabel(yourProduct);
		setLabel(yourChange);

		// setting design for the coin buttons
		setCoin(cent5, "cent5");
		setCoin(cent10, "cent10");
		setCoin(cent20, "cent20");
		setCoin(cent50, "cent50");
		setCoin(euro1, "euro1");
		setCoin(euro2, "euro2");

		// setting design for the other buttons
		setButton(select);
		setButton(clear);
		setButton(pay);

		// setting design for the fields
		setOutput(priceAmount);
		setOutput(insertedAmount);
		setChange(change);

		// adding actionlisterners to various buttons
		cent5.addActionListener(new C5Handler());
		cent10.addActionListener(new C10Handler());
		cent20.addActionListener(new C20Handler());
		cent50.addActionListener(new C50Handler());
		euro1.addActionListener(new E1Handler());
		euro2.addActionListener(new E2Handler());
		select.addActionListener(new SelectHandler());
		clear.addActionListener(new ClearHandler());
		pay.addActionListener(new PayHandler());

		// setting the Panel and adding components through absolute positioning
		vendingPanel.setBackground(Color.YELLOW);
		vendingPanel.add(sodaSelection).setBounds(150, 50, 200, 115);
		vendingPanel.add(select).setBounds(150, 175, 200, 50);
		vendingPanel.add(price).setBounds(50, 250, 200, 50);
		vendingPanel.add(inserted).setBounds(50, 350, 200, 50);
		vendingPanel.add(coin).setBounds(300, 250, 200, 50);
		vendingPanel.add(cent5).setBounds(320, 300, 50, 50);
		vendingPanel.add(cent10).setBounds(380, 300, 50, 50);
		vendingPanel.add(cent20).setBounds(320, 360, 50, 50);
		vendingPanel.add(cent50).setBounds(380, 360, 50, 50);
		vendingPanel.add(euro1).setBounds(320, 420, 50, 50);
		vendingPanel.add(euro2).setBounds(380, 420, 50, 50);
		vendingPanel.add(clear).setBounds(50, 470, 70, 40);
		vendingPanel.add(pay).setBounds(130, 470, 70, 40);
		vendingPanel.add(priceAmount).setBounds(50, 300, 150, 50);
		vendingPanel.add(insertedAmount).setBounds(50, 400, 150, 50);
		vendingPanel.add(resultPicture).setBounds(100, 600, 100, 100);
		vendingPanel.add(change).setBounds(320, 600, 25, 150);
		vendingPanel.add(changeCoins).setBounds(345, 600, 50, 150);
		vendingPanel.add(yourProduct).setBounds(90, 550, 200, 50);
		vendingPanel.add(yourChange).setBounds(300, 550, 200, 50);

		setContentPane(vendingPanel);
	}

	// Methods for creating and designing SodaSelection display

	public JSplitPane getSodaSelection(JList list, JLabel label) {
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.clearSelection();
		// list.setSelectedIndex(0);
		list.setFont(new Font("Candara", Font.BOLD, 12));
		list.setForeground(Color.LIGHT_GRAY);
		list.setSelectionForeground(Color.WHITE);
		list.setSelectionBackground(Color.DARK_GRAY);
		list.addListSelectionListener(this);
		JScrollPane listPane = new JScrollPane(list);
		JScrollPane picturePane = new JScrollPane(label);

		listPane.setSize(100, 50);
		picturePane.setSize(100, 50);
		listPane.getViewport().getView().setBackground(Color.BLACK);

		sodaSelection = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPane, picturePane);
		sodaSelection.setDividerLocation(100);
		sodaSelection.setDividerSize(0);

		return sodaSelection;
	}

	public void valueChanged(ListSelectionEvent e) {
		JList sodaList = (JList) e.getSource();
		getPic(products[sodaList.getSelectedIndex()].getName(), sodaPicture);
	}

	// Various methods for designing the components

	public void setButton(JButton button) {
		button.setFont(new Font("Corbel", Font.BOLD, 15));
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
	}

	public void setOutput(JTextField field) {
		field.setEditable(false);
		field.setFont(new Font("SimSun", Font.BOLD, 30));
		field.setForeground(Color.WHITE);
		field.setBackground(Color.BLACK);
	}

	public void setLabel(JLabel label) {
		label.setFont(new Font("Candara", Font.BOLD, 20));
		label.setForeground(Color.DARK_GRAY);
	}

	public void setCoin(JButton button, String name) {
		getButtonPic(name, button);
		button.setBorder(BorderFactory.createEmptyBorder());
	}

	public void setChange(JTextArea area) {
		area.setFont(new Font("SimSun", Font.BOLD, 20));
		area.setForeground(Color.DARK_GRAY);
		area.setBackground(Color.YELLOW);
		area.setEditable(false);
	}

	public void getPic(String name, JLabel area) {
		if (name != "") {
			ImageIcon icon = createImageIcon("/img/" + name + ".png");
			area.setIcon(icon);
		}
	}

	public void getButtonPic(String name, JButton button) {
		if (name != "") {
			ImageIcon icon = createImageIcon("/img/" + name + ".png");
			button.setIcon(icon);
		}
	}

	public ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// Innerclasses for button actionlisteners

	class SelectHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			selectedSoda = products[sodaList.getSelectedIndex()];
			priceAmount.setText("€" + String.format("%.2f", selectedSoda.getPrice()));
		}
	}

	class C5Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			insertValue += 0.05;
			insertedAmount.setText("€" + String.format("%.2f", insertValue));
		}
	}

	class C10Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			insertValue += 0.10;
			insertedAmount.setText("€" + String.format("%.2f", insertValue));
		}
	}

	class C20Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			insertValue += 0.20;
			insertedAmount.setText("€" + String.format("%.2f", insertValue));
		}
	}

	class C50Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			insertValue += 0.50;
			insertedAmount.setText("€" + String.format("%.2f", insertValue));
		}
	}

	class E1Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			insertValue += 1.00;
			insertedAmount.setText("€" + String.format("%.2f", insertValue));
		}
	}

	class E2Handler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			insertValue += 2.00;
			insertedAmount.setText("€" + String.format("%.2f", insertValue));
		}
	}

	class ClearHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			insertValue = 0.00;
			insertedAmount.setText("");
		}
	}

	class PayHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (selectedSoda != null) {
				if (selectedSoda.getPrice() <= insertValue) {
					getPic(selectedSoda.getName(), resultPicture);
					insertedAmount.setText("");
					change.setText(getChange(selectedSoda, insertValue));
					getPic("allcents", changeCoins);
					insertValue = 0.00;
				} else {
					getPic("Insuffient funds", resultPicture);
				}
			}
		}
	}
}
