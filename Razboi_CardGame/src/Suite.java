
import java.util.Collections;
import java.util.Stack;

public class Suite {
    private Stack<Card> suite = new Stack<>();

    public Suite() {
        for (Pattern pattern : Pattern.values())
            for (Type type : Type.values())
                suite.add(new Card(type, pattern));
    }

    public Card getCard() {
        return suite.pop();
    }

    public int getSize() {
        return suite.size();
    }

    public void shuffleCards() {
        Collections.shuffle(suite);
    }
}
