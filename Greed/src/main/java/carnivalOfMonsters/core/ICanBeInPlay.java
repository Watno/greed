package carnivalOfMonsters.core;

public interface ICanBeInPlay extends ICanBePlayed {

    int getProvidedLandPoints(LandType landType);

    int getConsumedLandPoints(LandType landType);

    int getDangerLevel();

}
