package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.FoodEntity;
import com.sist.web.projection.FoodListProjection;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer>{
	@Query(value = "SELECT fno, name, poster, num "
				 + "FROM (SELECT fno, name, poster, rownum as num "
				 + "FROM (SELECT fno, name, poster "
				 + "FROM project_food ORDER BY fno ASC)) "
				 + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	List<FoodListProjection> findPagedFoodList(@Param("start") int start, @Param("end") int end);
	
	FoodEntity findByFno(int fno);
}
