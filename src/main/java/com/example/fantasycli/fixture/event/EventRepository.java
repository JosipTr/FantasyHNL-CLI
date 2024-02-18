package com.example.fantasycli.fixture.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<Event, Integer>{
	@Modifying
	@Query(value = "DELETE FROM event where fixture_id = ?1", nativeQuery = true)
	void deleteAllByFixtureId(int id);
}
