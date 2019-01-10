package caesarCiphers;

//imports the scanner we use to take input from the console
import java.util.Scanner;

public class BasicCipher {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		//this boolean controls whether the basic code should loop and run again
		boolean going = true;
		while (going){
			//this 'correct' boolean and the while loop run until a valid answer is given
			boolean correct = false;
			String result = null;
			while (!correct){
				System.out.println("Are you encoding or decoding a message ('encoding' or 'decoding')?");
				result = scan.next();
				if (result.equals("encoding") || result.equals("decoding")){
					correct = true;
				} else {
					System.out.println("Your input does not meet either answer. Please try again.");
				}
			}
			//gets the answer, and determines if encoding or decoding runs
			if (result.equals("encoding")){
				encoding();
			} else if (result.equals("decoding")){
				decoding();
			}
			//once everything is done, asks if the program should loop
			System.out.println("Would you like to run this program again? (Y/N)");
			result = scan.next();
			if (!result.equals("Y")){going = false;}
		}
	}
	
	private static void encoding(){
		//ask for the message and the shift index
		Scanner scan = new Scanner(System.in);
		String output = "";
		System.out.println("What message are you going to be encoding?");
		String message = scan.nextLine();
		message = message.toLowerCase();
		System.out.println("What index would you like to shift the message by?");
		int index = scan.nextInt();
		//shifts everything by the index, unless it is a space, then print space
		for (int x = 0; x < message.length(); x++){
			if (message.charAt(x) != 32){
				output += (char)(97 + ((message.charAt(x) + index - 97) % 26));
			} else {
				output += " ";
			}
		}
		//ouptus the message to the console
		System.out.println("Your message is " + output + ".");
	}
	
	private static void decoding(){
		//asks for the message and the shift index
		Scanner scan = new Scanner(System.in);
		String output = "";
		System.out.println("What message are you going to be decoding?");
		String message = scan.nextLine();
		message = message.toLowerCase();
		System.out.println("What index is the message shifted by?");
		int index = scan.nextInt();
		//shifts everything back by the index, unless it is a space, then print space
		for (int x = 0; x < message.length(); x++){
			if (message.charAt(x) != 32){
				output += (char)(97 + ((26 + (message.charAt(x) - 97 - index)) % 26));
			} else {
				output += " ";
			}
		}
		//outputs the message to the console
		System.out.println("Your message is " + output + ".\n");
	}
}
