
public class Room {

	static final int ROOMS = 10;
	final int roomNumber;
	Room next = null, prev;
	Patient patient;
	
	private static int nextRoomNumber = 100;

	Room() {
		roomNumber = nextRoomNumber;
		nextRoomNumber = nextRoomNumber +1;
		patient = new Patient();
	}

	//create the rooms, one patient per room
	static Room createRooms() {
		Room r = new Room();
		Room prevRoom = r;
		for (int i = 1; i<ROOMS; i=i+1) {
			Room s= new Room();
			s.prev = prevRoom;
			s.next = r;
			prevRoom.next = s;
			r.prev = s;
			prevRoom = s;
		}
		return r;
	}

}
