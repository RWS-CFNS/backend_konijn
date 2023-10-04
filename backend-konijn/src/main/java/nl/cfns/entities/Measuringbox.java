package nl.cfns.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor	@AllArgsConstructor //generator constructors with and without variables. not included in @data annotation!
@Table(name = "MTABLE")
public class Measuringbox {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "BOX_NAME", length = 50, nullable = false, unique = false)
	private String name;

	@Column
	private Integer value1;

	@Column
	private Integer tempValue1;

}
