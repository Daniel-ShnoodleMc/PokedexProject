package pokemon.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pokemon.controller.model.PokemonData;
import pokemon.controller.model.PokemonDexEntry;
import pokemon.controller.model.PokemonLocation;
import pokemon.dao.DexEntryDao;
import pokemon.dao.LocationDao;
import pokemon.dao.PokemonDao;
import pokemon.entity.DexEntry;
import pokemon.entity.Location;
import pokemon.entity.Pokemon;

@Service
public class PokemonService {
	@Autowired
	private PokemonDao pokemonDao;
	@Autowired
	private DexEntryDao dexEntryDao;
	@Autowired
	private LocationDao locationDao;
	
	
	@Transactional(readOnly = false)
	public PokemonData savePokemon(PokemonData pokemonData) {
		Long pokemonId = pokemonData.getPokemonId();
		Pokemon pokemon = findOrCreatePokemon(pokemonId);
		
		copyPokemonFields(pokemon, pokemonData);
		return new PokemonData(pokemonDao.save(pokemon));
	}

	private void copyPokemonFields(Pokemon pokemon, PokemonData pokemonData) {
		pokemon.setPokemonId(pokemon.getPokemonId());
		pokemon.setPokemonName(pokemonData.getPokemonName());
		pokemon.setPokemonType(pokemonData.getPokemonType());
		pokemon.setPokemonAbility(pokemonData.getPokemonAbility());
		pokemon.setPokemonEvoStage(pokemonData.getPokemonEvoStage());
		
	}

	private Pokemon findOrCreatePokemon(Long pokemonId) {
		if(Objects.isNull(pokemonId)) {
			return new Pokemon();
		} else {
			return findPokemonById(pokemonId);
		}
	}

	private void copyDexEntryFields(DexEntry dexEntry, PokemonDexEntry pokemonDexEntry) {
		dexEntry.setDexEntryId(pokemonDexEntry.getDexEntryId());
		dexEntry.setDexEntryClass(pokemonDexEntry.getDexEntryClass());
		dexEntry.setDexEntryText(pokemonDexEntry.getDexEntryText());
	}
	
	private void copyLocationFields(Location location, PokemonLocation pokemonLocation) {
		location.setLocationId(pokemonLocation.getLocationId());
		location.setLocationRegion(pokemonLocation.getLocationRegion());
		location.setLocationPlace(pokemonLocation.getLocationPlace());
		location.setLocationTime(pokemonLocation.getLocationTime());
	}
	
	private DexEntry findOrCreateDexEntry (Long pokemonId, Long dexEntryId) {
		if(Objects.isNull(dexEntryId)) {
			return new DexEntry();
		}
		return findDexEntryById(pokemonId, dexEntryId);
	}
	
	private Location findOrCreateLocation (Long pokemonId, Long locationId) {
		if(Objects.isNull(locationId)) {
			return new Location();
		}
		return findLocationById(pokemonId, locationId);
	}
	
	private Location findLocationById(Long pokemonId, Long locationId) {
		Location location = locationDao.findById(locationId).orElseThrow(() -> new NoSuchElementException("Location with ID=" + locationId + " was not found."));
		boolean found = false;
	
		for (Pokemon pokemon : location.getPokemons()) {
			if(pokemon.getPokemonId() == pokemonId) {
				found = true;
				break;
			}
		}
		if(!found) {
			throw new IllegalArgumentException("The location with ID=" + locationId + " is does not hold a pokemon with the ID=" + pokemonId);
		}
		return location;
	}

	private DexEntry findDexEntryById(Long pokemonId, Long dexEntryId) {
		DexEntry dexEntry = dexEntryDao.findById(dexEntryId).orElseThrow(() -> new NoSuchElementException("Entry with ID=" + dexEntryId + " was not found."));
		if(dexEntry.getPokemon().getPokemonId() != pokemonId) {
			throw new IllegalArgumentException("The Entry with ID=" + dexEntryId + " is not employeed by the Pokemon with ID=" + pokemonId + ".");
		}
		return dexEntry;
	}	
	
	
	private Pokemon findPokemonById(Long pokemonId) {
		return pokemonDao.findById(pokemonId).orElseThrow(() -> new NoSuchElementException("No Pokemon Found!"));
	}
	
	@Transactional (readOnly = false)
	public PokemonDexEntry saveDexEntry(Long pokemonId, PokemonDexEntry pokemonDexEntry) {
		Pokemon pokemon = findPokemonById(pokemonId);
		Long dexEntryId = pokemonDexEntry.getDexEntryId();
		DexEntry dexEntry = findOrCreateDexEntry(pokemonId, dexEntryId);
		copyDexEntryFields(dexEntry, pokemonDexEntry);
		dexEntry.setPokemon(pokemon);
		pokemon.getDexEntries().add(dexEntry);
		DexEntry dbDexEntry = dexEntryDao.save(dexEntry);
		return new PokemonDexEntry(dbDexEntry);
	}
	
	@Transactional
	public PokemonLocation saveLocation(Long pokemonId, PokemonLocation pokemonLocation) {
		Pokemon pokemon = findPokemonById(pokemonId);
		Long locationId = pokemonLocation.getLocationId();
		Location location = findOrCreateLocation(pokemonId, locationId);
		copyLocationFields(location, pokemonLocation);
		location.getPokemons().add(pokemon);
		pokemon.getLocations().add(location);
		Location dbLocation = locationDao.save(location);
		return new PokemonLocation(dbLocation);
	}
	
	@Transactional (readOnly = true)
	public List<PokemonData> retrieveAllPokemon() {
		List<Pokemon> pokemons = pokemonDao.findAll();
		List<PokemonData> result = new LinkedList<>();
		for(Pokemon pokemon : pokemons) {
			PokemonData psd = new PokemonData(pokemon);
			psd.getEntry().clear();
			psd.getLocations().clear();
			result.add(psd);
		}
		return result;
	}

	@Transactional (readOnly = true)
	public PokemonData retrievePokemonById(Long pokemonId) {
		return new PokemonData(findPokemonById(pokemonId));
	}
	
	@Transactional (readOnly = false)
	public void deletePokemonbyId(Long pokemonId) {
		Pokemon pokemon = findPokemonById(pokemonId);
		pokemonDao.delete(pokemon);
	}
	
	
	
}
