package pokemon.entity;

import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Location {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long locationId;
	private String locationRegion;
	private String locationPlace;
	private String locationTime;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany (mappedBy = "locations", cascade = CascadeType.PERSIST)
	private Set<Pokemon> pokemons = new HashSet<>();
}