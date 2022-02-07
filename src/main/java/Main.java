import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        Product p1 = new Product(22837, "Ice Americano", 2000);
        Product p2 = new Product(22837, "Ice Cappucino", 2500);
        Store s1 = new Store("RO9101", "Units St.50, Ulju-Gun, Ulsan", "Pan Dorothy", "107-86-09325");
        Buying buying = new Buying(new Date(), s1, new ArrayList<>() {{add(p1); add(p2);}}, new ArrayList<>(){{add(1); add(2);}}, "Lee");
         */
        Buying b = new Buying();
        Buying buying = b.getFromFile("src/test/pics/input.txt");
        buying.createBill("src/test/pics/test.jpg");
    }
}
