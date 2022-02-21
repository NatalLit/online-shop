package entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonAutoDetect
public class Product {
    int id;
    String name;
    double price;
    Date creationDate;

    public Product() {
    }

    public Product(int id, String name, double price, Date creationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", creationDate=" + creationDate +
                '}';
    }
}
