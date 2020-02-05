

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("../input_data/test_input.txt"));
			//Scanner scanner = new Scanner(new File("input_data/test_input.txt")); // user this scanner while running the app locally

			EventScheduler eventScheduler = new EventScheduler();
			while (scanner.hasNextLine()) {
				eventScheduler.processEvent(scanner.nextLine());
			}
			eventScheduler.displaySchedule();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}

}
