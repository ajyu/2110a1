import java.util.Scanner;

public class Hospital {

	Room firstRoom;
	Doctor doctor;

	public static void main(String[] args) {
		Hospital h = new Hospital();
		h.play();
	}

	Hospital() {
		firstRoom= Room.createRooms();
		doctor= new Doctor(firstRoom);
	}

	void play() {

		System.out.println();      
		System.out.println("*******************************************");      
		System.out.println("* Welcome to Krzmrgystan General Hospital *");      
		System.out.println("*******************************************");      
		System.out.println();      
		System.out.println("Type 'h' for instructions");      

		Scanner scanner = new Scanner(System.in);
		while (!this.done()) {
		displayStatus();
		System.out.print("n=next, p=prev, t=treat, h=help, q=quit? ");
		processCommand(scanner.nextLine());
		}
		
		displayStatus();
		System.out.println("Medicine Remaining: "+this.doctor.medicine);
		System.out.println("Patients cured: "+this.cured());
	}

	private void processCommand(String cmd) {
		String s = cmd;
		for (int i=0; i<s.length() && !this.done(); i=i+1) {
			char c= s.charAt(i);
			if (c=='n') this.move('n');
			else if (c=='p') this.move('p');
			else if (c=='t') this.treat();
			else if (c=='h') this.displayHelp();
			else if (c=='q') this.quit();
			else System.out.println("Unknown command "+c);
		}
	}

	private void quit() {
		System.exit(0);
	}

	private void treat() {
		this.doctor.location.patient.treat();
		this.doctor.useMedicine();
		Room r = this.doctor.location.next;
		do {
			r.patient.untreated();
			r= r.next;
		} while (r!= this.doctor.location);
	}

	private void move(char ch) {
		if (ch=='n') this.doctor.location = this.doctor.location.next;
		else if (ch=='p') this.doctor.location = this.doctor.location.prev;
		Room r = this.doctor.location;
		do {
			r.patient.untreated();
			r= r.next;
		} while (r.next!= this.doctor.location);
	}

	//count the number of cured patients
	private int cured() {
		Room room = doctor.location;
		int n = 0;
		do {
			if (room.patient.cured()) n++;
			room = room.next;
		} while (room != doctor.location);
		return n;
	}

	private boolean done() {
		if (!this.doctor.medicineLeft()) return true;
		Room r = this.doctor.location;
		do {
			if (r.patient.treatable()==true) return false;
			r= r.next;
		} while (r!=this.doctor.location);
		return true;
	}

	private void displayStatus() {
		System.out.format("\n%-4s %-17s %-3s %s\n", "Room", "Patient", "Age", "Health");
		Room room = firstRoom;
		do {
			System.out.format("%3d  %-17s %2d  %d",
					room.roomNumber, room.patient.name, room.patient.age, room.patient.health);
			if (room.patient.cured()) System.out.print(" recovered!");
			if (room.patient.died()) System.out.print(" died!");
			System.out.println();
			room = room.next;
		} while (room != firstRoom);
		System.out.format("\nThe doctor is in room %d with %d units of medicine\n",
				doctor.location.roomNumber, doctor.medicine);
	}

	private void displayHelp() {
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println("Krzmrgystan General Hospital Help");
		System.out.format("There are %d rooms arranged in a ring, each with 1 patient.\n", Room.ROOMS);
		System.out.println("Enter a command at the prompt.  The commands are:");
		System.out.println("  n  move to the next (higher-numbered) room");
		System.out.println("  p  move to the previous (lower-numbered) room");
		System.out.println("  t  treat the patient in this room");
		System.out.println("  h  display this help screen");
		System.out.println("  q  quit");
		System.out.println("You can enter a sequence of commands on the same line,");
		System.out.println("  like this: ttttn");
		System.out.println("Treating a patient uses one unit of medicine, after which the");
		System.out.format("  treated patient's health improves by %d.\n", Patient.TREATED_GAIN);
		System.out.println("Treating a patient or moving takes one unit of time, during");
		System.out.format("  which all untreated patients' health deteriorates by %d.\n", Patient.UNTREATED_LOSS);
		System.out.format("A patient is cured when his/her health reaches %d.\n", Patient.CURED);
		System.out.format("A patient dies when his/her health reaches %d.\n", Patient.DEAD);
		System.out.println("How many patients can you save?");
		System.out.println("-------------------------------------------------------------");
	}

}
