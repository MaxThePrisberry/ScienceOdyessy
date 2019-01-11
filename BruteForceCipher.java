package caesarCiphers;

import java.util.*; 
import java.io.*;

public class BruteForceCipher {
	public static void main(String [] args) throws FileNotFoundException{
		Scanner scan = new Scanner(System.in);   
		System.out.print("Enter an encrypted word to be decoded: ");
		String str = scan.nextLine(); 
		str.trim();
		BruteForce(str);
	}
	public static void BruteForce(String str) throws FileNotFoundException{
		ArrayList<String> results = new ArrayList<>(); 
		for(int i = 1; i <= 26; i++){
			String message = "";
			for(int j = 0; j < str.length(); j++){
				if (str.charAt(j) != 32){
					message += (char)(97 + ((26 + (str.charAt(j) - 97 - i)) % 26));
				}else{
					message += " ";
				}
			}
			results.add(message); 
			System.out.println("Shift " + i + ": " + message);
		}
		bestGuess(results);
	}
	public static void bestGuess(ArrayList<String> results) throws FileNotFoundException{
		double[] wordMatch = new double[26];
		for (int x = 0; x < results.size(); x++){
			wordMatch[x] = wordSync(results.get(x));
		}
		int index = findGreatest(wordMatch);
		System.out.println("\nWe believe that Shift " + (index + 1) + " is your best shot. It says '" + results.get(index) + "'.");
	}
	private static double wordSync(String message) throws FileNotFoundException{
		ArrayList<String> words = messageBreak(message);
		System.out.print(words.toString());
		int real = 0;
		for (int x = 0; x < words.size(); x++){
			Scanner file = new Scanner(new File("C:/Files/dictionary.txt"));
			boolean isReal = false;
			while (file.hasNext()){
				String test = file.next();
				test = test.toLowerCase();
				if (words.get(x).equals(test)){
					isReal = true;
					break;
				}
			}
			if (isReal){
				real++;
			}
		}
		System.out.println(" " + real);
		return ((double)real/words.size());
	}
	private static ArrayList<String> messageBreak(String message){
		ArrayList<String> addup = new ArrayList<String>();
		String segment = "";
		for (int x = 0; x < message.length(); x++){
			if (message.charAt(x) == 32){
				addup.add(segment);
				segment = "";
			} else {
				segment += message.charAt(x);
			}
		}
		addup.add(segment);
		return addup;
	}
	private static int findGreatest(double[] array){
		double highest = 0.0;
		int index = 0;
		for (int x = 0; x < array.length; x++){
			if (array[x] >= highest){
				index = x;
				highest = array[x];
			}
		}
		return index;
	}
}
