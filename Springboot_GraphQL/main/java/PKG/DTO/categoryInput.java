package PKG.DTO;

import java.util.Set;

import lombok.Data;

@Data
public class categoryInput {
    private String categoryName;
    private String images;
    private Set<Integer> userIds; // id users liên kết
}