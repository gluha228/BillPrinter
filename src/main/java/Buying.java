import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

public class Buying {
    @Getter
    @Setter
    private Date buyingTime;
    @Getter
    @Setter
    private Store store;
    @Getter
    @Setter
    private List<Product> products;
    @Getter
    @Setter
    private List<Integer> quantity;
    private int totalPrice;
    @Getter
    @Setter
    private String manager;

    public Buying() {}

    public void createBill(String path) throws IOException {
        for (int i = 0; i < products.size(); i++) {
            totalPrice += products.get(i).getPrice() * quantity.get(i);
        }

        int line = 60; //start
        int nl = 20;  //line height
        int bl = 5; //border left right

        int width = 400;
        int height = 400 + products.size() * nl;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();

        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.black);

        g.setFont(new Font("Monospace", Font.BOLD, 28));
        g.drawString("RECEIPT", 150, line);
        line += 2 * nl;
        g.setFont(new Font("Monospace", Font.PLAIN, 15));
        g.drawString("Store: " + store.getName(), bl, line);
        line += nl;
        g.drawString("Store number: " + store.getTelNumber() + "  Manager: " + manager, 5, line);
        line += nl;
        g.drawString("Adress: " + store.getAdress(), bl, line);
        line += nl;
        g.drawString(buyingTime.toString().substring(4, 16), bl, line);
        g.drawString("No:" + store.getId(), bl + width - 15 * store.getId().length(), line);
        line += nl;
        g.drawLine(bl, line, width - bl, line);
        line += 5;
        g.drawLine(bl, line, width - bl, line);
        line += nl;
        g.drawString("Menu", bl, line);
        g.drawString("Price", 130, line);
        g.drawString("Quant.", 210, line);
        g.drawString("Total price", 300, line);
        line += 5;
        g.drawLine(bl, line, width - bl, line);
        line += 5;
        g.drawLine(bl, line, width - bl, line);

        for (int i = 0; i < products.size(); i++) {
            line += nl;
            g.drawString(products.get(i).getName(), bl, line);
            g.drawString("" + products.get(i).getPrice(), 130, line);
            //toString тут почему-то не работает потому прибавляю к пустой строке
            g.drawString("" + quantity.get(i), 210, line);
            g.drawString("" + products.get(i).getPrice() * quantity.get(i), 300, line);
        }

        line += 5;
        g.drawLine(bl, line, width - bl, line);

        line += nl;
        g.drawString("Total price", bl, line);
        g.drawString("" + totalPrice, 300, line);
        line += nl;
        g.drawString("Before tax", 130, line);
        g.drawString("" + (int)(totalPrice * 0.92), 300, line);
        line += nl;
        g.drawString("Tax(VAT)", 130, line);
        g.drawString("" + (int)(totalPrice * 0.08), 300, line);
        line += 5;
        g.drawLine(130, line, width - bl, line);
        line += nl;
        g.drawString("Total price", bl, line);
        g.drawString("" + totalPrice, 300, line);
        line += nl;
        g.drawString("Amount of pay", bl, line);
        g.drawString("" + totalPrice, 300, line);
        line += nl;
        g.drawString("Credit card", 130, line);
        g.drawString("" + totalPrice, 300, line);
        line += 5;
        g.drawLine(bl, line, width - bl, line);
        line += 5;
        g.drawLine(bl, line, width - bl, line);

        g.dispose();

        File file = new File(path);
        ImageIO.write(bufferedImage, "jpg", file);
    }

    public Buying getFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Files.readString(Path.of(filePath)), Buying.class);
    }
}
