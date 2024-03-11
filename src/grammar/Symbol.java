package grammar;

public abstract class Symbol implements Comparable {

    public Symbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object obj) {
        return name.compareTo(((Symbol) obj).name);
    }

    private final String name;
}
