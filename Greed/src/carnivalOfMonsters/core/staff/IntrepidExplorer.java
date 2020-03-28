package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.logging.ILogEntry;

import java.util.Optional;

public class IntrepidExplorer extends StaffMember {
    private LandType assignedLandType;

    public IntrepidExplorer(String name) {
        super(name, 2);
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {
        assignedLandType = playingPlayer.getDecisionMaker().chooseLandTypeForExplorer();
        super.onPlay(playingPlayer, game, loggingContext);
    }

    @Override
    public int getProvidedLandPoints(LandType landType) {
        if (landType == assignedLandType) return 1;
        else return 0;
    }


}
