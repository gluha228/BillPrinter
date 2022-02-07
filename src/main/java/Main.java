import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Buying b = new Buying();
        Buying buying = b.getFromFile("src/test/pics/input.txt");
        buying.createBill("src/test/pics/test.jpg");
    }
}
