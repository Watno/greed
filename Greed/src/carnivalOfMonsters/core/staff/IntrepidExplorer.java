package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public class IntrepidExplorer extends StaffMember {
    private LandType assignedLandType;

    public IntrepidExplorer(String name) {
        super(name, 2);
    }

    @Override
    public void onPlay(Player playingPlayer, Game game) {
        playingPlayer.getDecisionMaker().chooseLandTypeForExplorer();
        super.onPlay(playingPlayer, game);
    }

    @Override
    public int getProvidedLandPoints(LandType landType) {
        if (landType == assignedLandType) return 1;
        else return 0;
    }


}
