package com.example.fantasycli.fixture.event.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EventTimeRepository extends JpaRepository<EventTime, Integer>{
	@Modifying
	@Query(value = "DELETE FROM event_time where fixture_id = ?1", nativeQuery = true)
	void deleteAllByFixtureId(int id);
}
