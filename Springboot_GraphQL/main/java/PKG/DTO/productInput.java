package PKG.DTO;

import lombok.Data;

@Data
public class productInput {
    private String title;
    private int quantity;
    private String description;
    private double price;
    private int userId;
    private int categoryId;
}