package carnivalOfMonsters.core;

import java.util.Random;

public class HuntDie {
	private Random randomizer;
	
	protected HuntDie(Random randomizer) {
		super();
		this.randomizer = randomizer;
	}

	private int roll() {
		var roll = randomizer.nextInt(6);
		if (roll == 0) return 2;
		if (roll == 1) return 1;
		return 0;
	}

}
