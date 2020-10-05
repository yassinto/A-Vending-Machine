package dom;

import dom.Soda;

//interface that calculates change
public interface Change {
	public int[] coins = { 200, 100, 50, 20, 10, 5 }; //integer values of available coin-units
	public String[] times = { "0x", "0x", "0x", "0x", "0x", "0x" }; //array of strings for the amounts for each coin-unit returned as change 

	//Method that calculates change then loops through coin-units to determine the amounts returned and put those in a string
	public default String getChange(Soda soda, double insert){
		int change = (int) (insert * 100) - soda.getPennyPrice();
		int amount = 0;
		String timesCoins = null;
		for (int i = 0; i < coins.length; i++) {
			amount = change / coins[i];
			times[i]= amount + "x";
			change = change % coins[i];
		}
		timesCoins = times[0] + "\n" + times[1] + "\n" + times[2] + "\n" + times[3] + "\n" + times[4] + "\n" + times[5];
		return timesCoins;
	}
}
