package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.BoardEntity;
import com.sist.web.projection.BoardProjection;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	
	BoardEntity findByNo(int no);
	
	@Query(value = "SELECT no, name, subject, TO_CHAR(content) as content, "
				 + "TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
				 + "FROM (SELECT no, name, subject, content, regdate, hit, rownum as num "
				 + "FROM (SELECT no, name, subject, content, regdate, hit "
				 + "FROM kkh_board ORDER BY no DESC)) "
				 + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	List<BoardProjection> boardListData(@Param("start") int start, @Param("end") int end);
	
	@Query(value = "SELECT NVL(MAX(no) + 1, 1) FROM kkh_board", nativeQuery = true)
	int getMaxNo();
}
