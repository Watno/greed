package spacealert.core;

public enum Phase {
    ONE,
    TWO,
    THREE;

    public Phase next() {
        switch (this) {
            case ONE -> {return TWO;}
            case TWO -> {return THREE;}
        }
        throw new IllegalStateException();
    }
}
