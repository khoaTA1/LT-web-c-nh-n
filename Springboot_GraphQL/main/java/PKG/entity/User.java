package PKG.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "users")
public class User {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fullname", columnDefinition = "nvarchar(75)")
	private String fullName;

	@Column(name = "email", columnDefinition = "varchar(max)")
	private String email;
	
	@Column(name = "password", columnDefinition = "varchar(50)")
	private String passwd;
	
	@Column(name = "phone", columnDefinition = "varchar(50)")
	private String phone;
	
	@ManyToMany(mappedBy = "users")
    private Set<Category> categories = new HashSet<>();
}
