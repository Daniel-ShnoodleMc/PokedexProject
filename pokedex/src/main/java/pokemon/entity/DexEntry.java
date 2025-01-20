package pokemon.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class DexEntry {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long dexEntryId;
	private String dexEntryClass;
	private String dexEntryText;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "pokemon_id")
	private Pokemon pokemon;
}