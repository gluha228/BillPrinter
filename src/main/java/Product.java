import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private int price;
    public Product(){}
    public Product(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
