

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("input_data/test_input.txt"));
			while (scanner.hasNextLine()) {
				//System.out.println(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
