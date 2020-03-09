package carnivalOfMonsters.core.staff;

public class Jagermeister extends StaffMember {

    public Jagermeister(String name) {
        super(name, 2);
    }

    @Override
    public int getDangerLevel() {
        return -1;
    }

}
