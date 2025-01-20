package pokemon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pokemon.controller.model.PokemonData;
import pokemon.controller.model.PokemonDexEntry;
import pokemon.controller.model.PokemonLocation;
import pokemon.service.PokemonService;

@RestController
@RequestMapping("/pokedex")
@Slf4j
public class PokemonController {
	@Autowired
	private PokemonService pokemonService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PokemonData insertPokemon(@RequestBody PokemonData pokemonData) {
		log.info("Creating Pokemon {}", pokemonData);
		return pokemonService.savePokemon(pokemonData);
	}
	
	@PutMapping("/{pokemonId}")
	public PokemonData updatePokemon(@PathVariable Long pokemonId,
			@RequestBody PokemonData pokemonData) {
		log.info("Updating Pokemon {}", pokemonData);
		return pokemonService.savePokemon(pokemonData);
	}
	
	@PostMapping("/{pokemonId}/dexEntry")
	@ResponseStatus (code = HttpStatus.CREATED)
	public PokemonDexEntry addDexEntryToPokemon (@PathVariable Long pokemonId,
			@RequestBody PokemonDexEntry pokemonDexEntry) {
			log.info("Adding entry {} to pokemon with ID={}", pokemonDexEntry, pokemonId);
			return pokemonService.saveDexEntry(pokemonId, pokemonDexEntry);
	}
	
	@PostMapping("/{pokemonId}/location")
	@ResponseStatus (code = HttpStatus.CREATED)
	public PokemonLocation addLocationToPokemon (@PathVariable Long pokemonId,
			@RequestBody PokemonLocation pokemonLocation) {	
		log.info("Adding location {} to pokemon with ID={}", pokemonLocation, pokemonId);
		return pokemonService.saveLocation(pokemonId, pokemonLocation);
	}
	
	@GetMapping
	public List<PokemonData> retrieveAllPokemons() {
		log.info("Retrieving all Pokemon!");
		return pokemonService.retrieveAllPokemon();
	}
	
	@GetMapping("/{pokemonId}")
	public PokemonData retrievePokemonById(@PathVariable Long pokemonId) {
		log.info("Retrieving Pokemon with ID={}", pokemonId);
		return pokemonService.retrievePokemonById(pokemonId);
	}
	
	@DeleteMapping("/{pokemonId}")
	public Map<String, String> deletePokemonById(@PathVariable Long pokemonId) {
		pokemonService.deletePokemonbyId(pokemonId);
		
		return Map.of("message", "Pokemon with ID=" + pokemonId + " deleted.");
	}
	
	
	
	
	
	
	
	
}
