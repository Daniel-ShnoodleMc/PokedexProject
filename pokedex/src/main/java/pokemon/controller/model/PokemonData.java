package pokemon.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pokemon.entity.DexEntry;
import pokemon.entity.Location;
import pokemon.entity.Pokemon;

@Data
@NoArgsConstructor
public class PokemonData {
	private Long pokemonId;
	private String pokemonName;
	private String pokemonType;
	private String pokemonAbility;
	private String pokemonEvoStage;
	
	private Set<PokemonDexEntry> entry = new HashSet<>();
	private Set<PokemonLocation> locations = new HashSet<>();
	
	public PokemonData (Pokemon pokemon) {
		pokemonId = pokemon.getPokemonId();
		pokemonName = pokemon.getPokemonName();
		pokemonType = pokemon.getPokemonType();
		pokemonAbility = pokemon.getPokemonAbility();
		pokemonEvoStage = pokemon.getPokemonEvoStage();
		
		for(DexEntry dexEntry : pokemon.getDexEntries()) {
			entry.add(new PokemonDexEntry(dexEntry));
		}
		for(Location location : pokemon.getLocations()) {
			locations.add(new PokemonLocation(location));
		}
		
	}
}
