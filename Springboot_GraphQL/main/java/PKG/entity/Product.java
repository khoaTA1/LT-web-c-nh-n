package PKG.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", columnDefinition = "nvarchar(500) not null")
	private String title;
	
	@Column(name = "quantity", columnDefinition = "int")
	private int quantity;
	
	@Column(name = "description", columnDefinition = "nvarchar(500) not null")
	private String description;
	
	@Column(name = "price", columnDefinition = "decimal(6,2)")
	private double price;
	
	@Column(name = "userid")
	private int userId;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
	@JsonBackReference
	private Category category;
}
