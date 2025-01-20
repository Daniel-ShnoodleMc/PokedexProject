package pokemon.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pokemon.entity.Location;

@Data
@NoArgsConstructor
public class PokemonLocation {
	private Long locationId;
	private Long locationRegion;
	private Long locationPlace;
	private Long locationTime;
	
	public PokemonLocation (Location location) {
		locationId = location.getLocationId();
		locationRegion = location.getLocationRegion();
		locationPlace = location.getLocationPlace();
		locationTime = location.getLocationTime();
	}
}
