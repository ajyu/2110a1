
public class Doctor {

	private final int MEDICINE = 120;
	private static final int DOSAGE = 1; //enough for one treatment

	int medicine = MEDICINE;
	Room location;

	Doctor(Room room) {
		location = room;
	}

	//decrease the amount of medicine left by DOSAGE
	void useMedicine() {
		if (medicineLeft()) medicine -= DOSAGE;
	}

	//check if enough medicine left for one dose
	boolean medicineLeft() {
		return medicine >= DOSAGE;
	}

}
