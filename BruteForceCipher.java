import java.util.*; 
import java.io.*;
public class BruteForceCipher {
	public static void main(String [] args) throws FileNotFoundException{
		Scanner scan = new Scanner(System.in);   
		System.out.print("Enter an encrypted word to be decoded: ");
		String str = scan.next(); 
		BruteForce(str); 
	}
	public static void BruteForce(String str){
		ArrayList<String> results = new ArrayList<>(); 
		String message = "";
		for(int i = 1; i < 26; i++){
			for(int j = 0; j < str.length(); j++){
				if (str.charAt(j) != 32){
					message += (char)(97 + ((26 + (str.charAt(j) - 97 - i)) % 26));
				}else{
					message += " ";
				}
				results.add(message); 
				System.out.println("Result " + i + ": " + message);
				message = ""; 
			}
		}
	}
	public static int bestGuess(ArrayList<String> results) throws FileNotFoundException{
		Scanner input = new Scanner(new File("C:/files/Files/dictionary"));
		int[] tally = new int[results.size()];
		String message  = ""; 
		for(int i = 0; i < results.size(); i++){
			message = results.get(i); 
			
		}
	}
}
