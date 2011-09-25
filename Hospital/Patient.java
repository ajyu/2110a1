import java.util.Random;

public class Patient {

	//a couple ways to initialize a char array
	private static final char[] CONSONANTS = "bcdfghjklmnprstvz".toCharArray();
	private static final char[] VOWELS = {'a','e','i','o','u'};

	private static final int INITIAL_HEALTH = 50;
	static final int CURED = 100;
	static final int DEAD = 0;
	static final int TREATED_GAIN = 10;
	static final int UNTREATED_LOSS = 1;
	
	private Random rand = new Random();

	final String name;
	int health;
	int age;

	Patient() {
		name = getName();
		health = INITIAL_HEALTH;
		age = rand.nextInt(70)+10;
	}

	boolean cured() {
		return health >= CURED;
	}

	boolean died() {
		return health <= DEAD;
	}

	boolean treatable() {
		return health<CURED && health>DEAD;
	}

	void treat() {
		if (this.treatable() && this.health<CURED) {
			if (this.health + TREATED_GAIN > CURED) this.health = CURED;
			else this.health = this.health + TREATED_GAIN;
		}
	}

	void untreated() {
		if (this.treatable() && this.health>DEAD) {
			if (this.health - UNTREATED_LOSS < DEAD) this.health = DEAD;
			else this.health = this.health - UNTREATED_LOSS;
		}
	}   

	private char consonant() {
		return CONSONANTS[rand.nextInt(CONSONANTS.length)];
	}

	private char vowel() {
		return VOWELS[rand.nextInt(VOWELS.length)];
	}

	//generate a random Krzmrgystani name
	private String getName() {
		StringBuilder s = new StringBuilder();
		s.append(Character.toUpperCase(consonant()));
		s.append(vowel());
		if (rand.nextInt(2) == 0) {
			s.append(consonant());
		}
		s.append(consonant());
		s.append(vowel());
		if (rand.nextInt(2) == 0) {
			s.append(consonant());
		}
		if (rand.nextInt(2) == 0) {
			s.append(consonant());
			s.append(vowel());
		}
		s.append(' ');
		s.append(Character.toUpperCase(consonant()));
		s.append(vowel());
		if (rand.nextInt(2) == 0) {
			s.append(consonant());
		}
		s.append(consonant());
		s.append(vowel());
		if (rand.nextInt(2) == 0) {
			s.append(consonant());
		}
		if (rand.nextInt(2) == 0) {
			s.append(consonant());
			s.append(vowel());
		}
		return s.toString();      
	}
}
