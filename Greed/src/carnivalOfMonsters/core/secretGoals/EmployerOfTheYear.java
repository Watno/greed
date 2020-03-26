package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.staff.StaffMember;

public class EmployerOfTheYear extends SecretGoal {
    public EmployerOfTheYear(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        return (int) player.getCardsInPlay().stream().filter(x -> x instanceof StaffMember).count() * 3;
    }
}
