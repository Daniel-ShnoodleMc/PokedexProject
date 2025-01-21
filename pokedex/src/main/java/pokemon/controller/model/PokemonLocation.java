package pokemon.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pokemon.entity.Location;

@Data
@NoArgsConstructor
public class PokemonLocation {
	private Long locationId;
	private String locationRegion;
	private String locationPlace;
	private String locationTime;
	
	public PokemonLocation (Location location) {
		locationId = location.getLocationId();
		locationRegion = location.getLocationRegion();
		locationPlace = location.getLocationPlace();
		locationTime = location.getLocationTime();
	}
}
