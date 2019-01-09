package caesarCiphers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FrequencyAnalysis {
	public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	public static String frequency = "etaoinsrhldcumfpgwybvkxjqz";
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		boolean going = true;
		while (going) {
			runAnalysis();
			System.out.println("\nWould you like to run this again? (Y/N)");
			String answer = scan.next();
			if (!answer.equals("Y")){
				going = false;
			}
		}
	}
	public static void runAnalysis(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the string you would like to decode:");
		String result = scan.nextLine();
		result = result.toLowerCase();
		result = eliminateSpaces(result);
		char[] paired = findFrequency(result);
		result = replace(result, paired);
		System.out.println("Your decoded string roughly translates to:\n\n" + result);
	}
	
	private static String replace(String message, char[] possibility){
		StringBuilder answer = new StringBuilder("");
		for (int x = 0; x < message.length(); x++){
			answer.append(message.charAt(x));
		}
		for (int x = 0; x < 26; x++){
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			for (int y = 0; y < message.length(); y++){
				if (message.charAt(y) == possibility[x]){
					indexes.add(y);
				}
			}
			for (int z = 0; z < indexes.size(); z++){
				answer.replace((int)indexes.get(z), indexes.get(z) + 1, String.valueOf(frequency.charAt(x)));
			}
		}
		return answer.toString();
	}
	private static char[] findFrequency(String message){
		char[] frequency = new char[26];
		ArrayList<LetterFrequency> intFrequency = new ArrayList<LetterFrequency>();
		for (int x = 0; x < 26; x++){
			intFrequency.add(new LetterFrequency(alphabet.charAt(x), 0));
		}
		for (int x = 0; x < message.length(); x++){
			intFrequency.get(message.charAt(x)-97).add();
		}
		for (int x = 0; x < 26; x++){
			int index = findIndex(intFrequency);
			frequency[x] = intFrequency.get(index).getLetter();
		}
		System.out.println(Arrays.toString(frequency));
		return frequency;
	}
	private static String eliminateSpaces(String message){
		String output = "";
		for (int x = 0; x < message.length(); x++){
			if (message.charAt(x) != 32){
				output += message.charAt(x);
			}
		}
		return output;
	}
	private static int findIndex(ArrayList<LetterFrequency> arrayList){
		int highestVal = 0; int highestIndex = -1;
		for (int x = 0; x < arrayList.size(); x++){
			if (arrayList.get(x).getFrequency() >= highestVal){
				highestVal = arrayList.get(x).getFrequency();
				highestIndex = x;
			}
		}
		return highestIndex;
	}
}
