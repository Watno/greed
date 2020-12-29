package greed.game;

public abstract class Thug extends GreedCard {
	private int guns;
	private int cars;
	private int keys;
	//base values needed for Rubberface/Napoleon
	private int baseCars;
	private int baseGuns;
	private int baseKeys;
	 
	public Thug(int timingNumber, String name, int  cars, int guns, int keys){
		super(timingNumber, name);
		this.guns=guns;
		this.baseGuns=guns;
		this.cars=cars;
		this.baseCars=cars;
		this.keys=keys;
		this.baseKeys=keys;
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		thePlayer.addThug(this);
		thePlayer.changeGuns(guns);
		thePlayer.changeCars(cars);
		thePlayer.changeKeys(keys);
	}
	
	@Override
	public void removeFromPlay(GreedPlayer thePlayer, GreedGame theGame, Reason reason) {
		super.removeFromPlay(thePlayer, theGame, reason);
		thePlayer.getThugs().remove(this);
		thePlayer.changeGuns(-guns);
		thePlayer.changeCars(-cars);
		thePlayer.changeKeys(-keys);
	}
	
	public void changeGuns (int amount) {
		guns+=amount;
		if (owner!=null) {
			owner.changeGuns(amount);
		}
	}
	
	public void changeCars (int amount) {
		cars+=amount;
		if (owner!=null) {
			owner.changeCars(amount);
		}
	}
	
	public void changeKeys (int amount) {
		keys+=amount;
		if (owner!=null) {
			owner.changeKeys(amount);
		}
	}

	public int getGuns() {
		return guns;
	}

	public int getCars() {
		return cars;
	}

	public int getKeys() {
		return keys;
	}

	public int getBaseCars() {
		return baseCars;
	}

	public int getBaseGuns() {
		return baseGuns;
	}

	public int getBaseKeys() {
		return baseKeys;
	}
}
