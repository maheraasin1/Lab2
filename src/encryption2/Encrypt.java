//Mahera Asin 
// writing a program that will encrypt files 
// Septemeber 27, 2024 
package encryption2;
import java.io.File;
import java.util.Scanner;

public class Encrypt {
	 public static void main(String[] args) {
	        Scanner inputScanner = new Scanner(System.in);
	        boolean encryptMore = true;
	        while (encryptMore) {
	            String inputFileName;
	            String outputFileName;

	            // Asking for input file until a valid file is entered
	            while (true) {
	                System.out.print("Enter input file name: ");
	                inputFileName = inputScanner.nextLine();
	                File inputFile = new File(inputFileName);
	                if (inputFile.exists()) {
	                    break;
	                } else {
	                    System.out.println("File not found. Please try again.");
	                }
	            }

	            // Asking for output file name
	            System.out.print("Enter output file name: ");
	            outputFileName = inputScanner.nextLine();

	            // Processing the input file and writing the output
	            processFile(inputFileName, outputFileName);

	            // Asking if the user wants to encrypt more files
	            System.out.print("Would you like to encrypt more text (yes/no)? ");
	            String response = inputScanner.nextLine().toLowerCase();
	            encryptMore = response.equals("yes");
	        }

	        inputScanner.close();

	    }

	private static void processFile(String inputFileName, String outputFileName) {
		// TODO Auto-generated method stub
		
	}

}

