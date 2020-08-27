import java.util.ArrayList;
import java.util.Stack;

public class Player {
    private Stack<Card> pack = new Stack<>();
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPack(Stack<Card> pack) {
        this.pack = pack;
    }

    public void addCards(ArrayList<Card> cards) {
        for (Card card : cards)
            pack.add(0, card);
    }

    public void addCard(Card card) {
        pack.add(0, card);
    }

    public Card popCard() {
        return pack.pop();
    }

    public int getPackSize() {
        return pack.size();
    }

    /*public void afisare()
    {
        for(Card card:pack)
        {
            System.out.println(card.getPattern()+" "+card.getType());
        }
    }*/
}
