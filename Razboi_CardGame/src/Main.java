import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            System.out.println("The number of players (must be between 2 and 4):");
            int nrOfPlayers = Integer.parseInt(reader.readLine());
            while(nrOfPlayers>4 || nrOfPlayers<2)
            {
                System.out.println("Please pick a valid number for the number of players!!!  It must be between 2 and 4!");
                nrOfPlayers = Integer.parseInt(reader.readLine());
            }
            String[] names = new String[5];
            for (int i = 0; i < nrOfPlayers; i++) {
                int nr=i+1;
                System.out.println("The name of player number "+ nr +":");
                names[i]=reader.readLine();
            }
            Game game = new Game(nrOfPlayers, names);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
