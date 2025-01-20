package pokemon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pokemon.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long>{

}
