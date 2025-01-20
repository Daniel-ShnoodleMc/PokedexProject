package pokemon.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pokemon.entity.DexEntry;

@Data
@NoArgsConstructor
public class PokemonDexEntry {
	private Long dexEntryId;
	private String dexEntryClass;
	private String dexEntryText;
	
	public PokemonDexEntry (DexEntry dexEntry) {
		dexEntryId = dexEntry.getDexEntryId();
		dexEntryClass = dexEntry.getDexEntryClass();
		dexEntryText = dexEntry.getDexEntryText();
	}
}
