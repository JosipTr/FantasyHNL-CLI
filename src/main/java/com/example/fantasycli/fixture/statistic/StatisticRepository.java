package com.example.fantasycli.fixture.statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StatisticRepository extends JpaRepository<Statistic, Integer>{

	@Modifying
	@Query(value= "delete from statistics where fixture_id = ?1 cascade", nativeQuery = true)
	void deleteByFixtureId(int fixtureId);
}
