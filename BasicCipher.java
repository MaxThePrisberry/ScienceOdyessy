package caesarCiphers;

import java.util.Scanner;

public class BasicCipher {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		boolean going = true;
		while (going){
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
			if (result.equals("encoding")){
				encoding();
			} else if (result.equals("decoding")){
				decoding();
			}
			System.out.println("Would you like to run this program again? (Y/N)");
			result = scan.next();
			if (!result.equals("Y")){going = false;}
		}
	}
	
	private static void encoding(){
		Scanner scan = new Scanner(System.in);
		String output = "";
		System.out.println("What message are you going to be encoding?");
		String message = scan.nextLine();
		message = message.toLowerCase();
		System.out.println("What index would you like to shift the message by?");
		int index = scan.nextInt();
		for (int x = 0; x < message.length(); x++){
			if (message.charAt(x) != 32){
				output += (char)(97 + ((message.charAt(x) + index - 97) % 26));
			} else {
				output += " ";
			}
		}
		System.out.println("Your message is " + output + ".");
	}
	
	private static void decoding(){
		Scanner scan = new Scanner(System.in);
		String output = "";
		System.out.println("What message are you going to be decoding?");
		String message = scan.nextLine();
		message = message.toLowerCase();
		System.out.println("What index is the message shifted by?");
		int index = scan.nextInt();
		for (int x = 0; x < message.length(); x++){
			if (message.charAt(x) != 32){
				output += (char)(97 + ((26 + (message.charAt(x) - 97 - index)) % 26));
			} else {
				output += " ";
			}
		}
		System.out.println("Your message is " + output + ".\n");
	}
}
