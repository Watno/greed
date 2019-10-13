package spacealert.core;

public class Gravolift implements IDamageable {
    private boolean isDamaged = false;

    public boolean causesDelay(){
        return isDamaged;
    }

    @Override
    public void damage(){
        isDamaged = true;
    }
}
