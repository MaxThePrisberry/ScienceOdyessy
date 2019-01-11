package caesarCiphers;

import java.util.*; 
import java.io.*;

public class BruteForceCipher {
	public static String ignoreCharacters = " .!?-_*@#$%^&()/\\|~`'\"<>+=";
	public static void main(String [] args) throws FileNotFoundException{
		Scanner scan = new Scanner(System.in);   
		System.out.print("Enter an encrypted word to be decoded: ");
		String str = scan.nextLine(); 
		str.trim();
		BruteForce(str);
	}
	public static void BruteForce(String str) throws FileNotFoundException{
		ArrayList<String> results = new ArrayList<>(); 
		str = str.toLowerCase();
		for(int i = 1; i <= 26; i++){
			String message = "";
			for(int j = 0; j < str.length(); j++){
				boolean escapeChar = false;
				for (int x = 0; x < ignoreCharacters.length(); x++){
					if (str.charAt(j) == ignoreCharacters.charAt(x)){
						escapeChar = true;
						message += ignoreCharacters.charAt(x);
					}
				}
				if (!escapeChar){
					message += (char)(97 + ((26 + (str.charAt(j) - 97 - i)) % 26));
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
			//char letter = words.get(x).charAt(0);
			//Scanner file = new Scanner(new File("C:/Files/dictionary" + letter + ".txt"));
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
				boolean wordPart = true;
				for (int y = 0; y < ignoreCharacters.length(); y++){
					if (message.charAt(x) == ignoreCharacters.charAt(y)){
						wordPart = false;
					}
				}
				if (wordPart) {segment += message.charAt(x);}
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
