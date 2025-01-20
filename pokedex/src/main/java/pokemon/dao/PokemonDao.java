package pokemon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pokemon.entity.Pokemon;

public interface PokemonDao extends JpaRepository<Pokemon, Long>{

}
