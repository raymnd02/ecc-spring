package com.exist.dao;
import java.util.Optional;
import java.util.List;
import com.exist.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, Long>{

	@Query("Select p From Person p Where p.firstName = ?1 and p.lastName = ?2" )
	Optional<Person> findPersonByFirstNameAndLastName(String firstName,String lastName);
	
	@Query("Select p From Person p Where p.firstName = ?1 and p.lastName = ?2 and p.personId != ?3" )
	Optional<Person> findPersonByFirstNameAndLastNameEdit(String firstName,String lastName,Long personId);
	
	@Query("Select p From Person p Order By p.lastName" )
	Optional<List<Person>> findPersonOrderByLastName();
	
	@Query("Select p From Person p Order By p.dateHired" )
	Optional<List<Person>> findPersonOrderByDateHired();
}
