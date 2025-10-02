package khoa.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "videos")
public class Video {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "videoname", columnDefinition = "varchar(50)")
	private String videoName;

	@Column(name = "path", columnDefinition = "varchar(50)")
	private String videopath;

	@Column(name = "cateid", columnDefinition = "varchar(50)")
	private int cateid;
}
