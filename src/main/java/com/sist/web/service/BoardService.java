package com.sist.web.service;

import java.util.List;
import java.util.Map;

import com.sist.web.entity.BoardEntity;
import com.sist.web.projection.BoardProjection;

public interface BoardService {
	BoardEntity boardDetailData(int no);
	Map<String, Object> boardListData(int page);
	void boardInsert(BoardEntity vo);
}
