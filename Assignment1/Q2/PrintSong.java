// Zancheng Wang
// zw2316@nyu.edu
// Assignment #1
// 10 Feb 2020
// 1 hour
//
// This program prints the song "The Ants Go Marching"

class PrintSong{

	public static void getTitle(){
		System.out.print("      \'The Ants Go Marching\'\n\n");
	}

	//the input string will be one. two, three...
	public static void antsMarch(String str){
		System.out.print("The ants go marching " + str + " by " + str + ",\n\"Hurrah!, Hurrah!\"\nThe ants go marching " + str + " by " + str + ",\n\"Hurrah!, Hurrah!\"\nThe ants go marching " + str + " by " + str + ",");
	}

	//the little one will do different behavior
	public static void littleOne(String str){
		System.out.print("\nThe little one stops to " + str + "\n");
	}

	public static void goMarchingDown(){
		System.out.print("And they all go marching down,\nTo the ground, to get out, of the rain.\n       BOOM/'\\BOOM/'\\BOOM/'\\" + "\n" + "\n");
	}

	//divide the rhyme into several pieces and each piece is combined with a function
	public static void driver(String[] count, String[] stopToDo){
		getTitle();
		for(int i = 0; i < count.length; i++){
			antsMarch(count[i]);
			littleOne(stopToDo[i]);
			goMarchingDown();
		}
	}

	public static void main(String[] args) {

		//test
		PrintSong ps = new PrintSong();
		String[] count = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
		String[] stopToDo = {"suck his thumb.", "tie her shoe.", "climb a tree.", "shut the door.", "take a dive.", "pick up sticks.", 
							"pray to heaven.", "check the gate.", "check the time.", "say \"The End!\""};
		ps.driver(count, stopToDo);
		
	}
}