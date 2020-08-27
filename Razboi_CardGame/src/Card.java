public class Card {
    private Type type;
    private Pattern pattern;

    Card(Type type, Pattern pattern) {
        this.type = type;
        this.pattern = pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Type getType() {
        return type;
    }
}
