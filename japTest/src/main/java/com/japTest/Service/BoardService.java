package com.japTest.Service;

import com.japTest.Dto.BoardDto;
import com.japTest.Dto.BoardListDto;
import com.japTest.Entity.Board;
import com.japTest.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    //글목록 불러오기
    public List<BoardListDto> all(){
        List<Board> boardList= boardRepository.findAll();
        //1. JPA 메서드를 통한 테이블 전체 데이터 가져오기.(전체 데이터는 엔티티 객체에 하나씩 저장되어야 함)

        List<BoardListDto> boardListDtos = new ArrayList<>();
        for(Board board : boardList){
            boardListDtos.add(BoardListDto.of(board));
        //2. 엔티티 리스트의 값을 dto 리스트에 저장하기
        }
        return boardListDtos;
    }

    public void save(@Valid BoardDto boardDto) {
        //DB저장 할 수 있도록 DAO클래스의 메서드를 호출하기
        Board board = boardDto.createBoard();
            //1. Dto 객체를 Entity 객체로 저장
        boardRepository.save(board);
            //2.JAP 메서드 호출하기 = repository 호출하기(interface 형성 필요)
    }
}
