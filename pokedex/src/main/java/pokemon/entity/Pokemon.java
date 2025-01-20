package pokemon.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Pokemon {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long pokemonId;
	private String pokemonName;
	private String pokemonType;
	private String pokemonAbility;
	private String pokemonEvoStage;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable (name = "pokemon_location",
	joinColumns = @JoinColumn(name = "pokemon_id"),
	inverseJoinColumns = @JoinColumn (name = "location_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Location> locations = new HashSet<>();
	
	@OneToMany (mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<DexEntry> dexEntries = new HashSet<>();
}