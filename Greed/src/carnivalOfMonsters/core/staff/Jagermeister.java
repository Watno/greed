package carnivalOfMonsters.core.staff;

public class Jagermeister extends StaffMember {

    public Jagermeister() {
        super(2);
    }

    @Override
    public int getDangerLevel() {
        return -1;
    }

}
