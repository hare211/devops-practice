package com.sist.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.sist.web.dao.BoardRepository;
import com.sist.web.entity.BoardEntity;
import com.sist.web.projection.BoardProjection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository bDao;
	
	@Override
	public BoardEntity boardDetailData(int no) {
		BoardEntity vo = bDao.findByNo(no);
		vo.setHit(vo.getHit() + 1);
		bDao.save(vo);
		vo = bDao.findByNo(no);
		return vo;
	}
	
	@Override
	public void boardInsert(BoardEntity vo) {
		vo.setHit(0);
		vo.setRegdate(new Date());
		vo.setNo(bDao.getMaxNo());
		
		bDao.save(vo);
	}
	
	@Override
	public Map<String, Object> boardListData(int page) {
		Map<String, Object> map = new HashMap<>();
		
		int rowSize = 10;
		int start = (rowSize * page) - (rowSize - 1);
		int end = rowSize * page;
		
		List<BoardProjection> list = bDao.boardListData(start, end);
		int count = (int) bDao.count();
		int totalPage = (int) (Math.ceil(count / 10.0));
		
		map.put("list", list);
		map.put("totalPage", totalPage);
		map.put("curPage", page);
		
		return map;
	}
}
