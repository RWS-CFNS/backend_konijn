package nl.cfns.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Column(name = "ID")
	private Long id;

	@Column(name = "BOX_NAME", length = 50, nullable = false, unique = false)
	private String name;

	@Column(nullable = false)
	@Min(value = 0, message = "Value should be greater then, or equal to 0")
	@Max(value = 100, message = "Value should be less then, or equal to 100")
	private Integer value1;

	@Column
	private Integer tempValue1;
	
    public void generateNewId() {
        this.id = null; // Set the current ID to null and generate a new one
    }

}
