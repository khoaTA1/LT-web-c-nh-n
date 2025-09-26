package PKG.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "categories")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "categoryname", columnDefinition = "nvarchar(255)")
	private String categoryName;

	@Column(name = "images", columnDefinition = "nvarchar(max)")
	private String images;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_categories", // Tên bảng trung gian
            joinColumns = @JoinColumn(name = "category_id"), // Khóa ngoại từ bảng Category
            inverseJoinColumns = @JoinColumn(name = "user_id") // Khóa ngoại từ bảng User
    )
	@JsonManagedReference
    private Set<User> users = new HashSet<>();
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Product> products;
}
