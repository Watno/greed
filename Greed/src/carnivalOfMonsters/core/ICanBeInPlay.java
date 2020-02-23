package carnivalOfMonsters.core;

public interface ICanBeInPlay extends ICanBePlayed {
	
	public int getProvidedLandPoints(LandType landType);
	
	public int getConsumedLandPoints(LandType landType);

	int getDangerLevel();

}
