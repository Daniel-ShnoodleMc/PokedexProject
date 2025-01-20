package pokemon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pokemon.entity.DexEntry;

public interface DexEntryDao extends JpaRepository<DexEntry, Long>{

}
