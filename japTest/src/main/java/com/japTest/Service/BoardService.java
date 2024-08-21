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
//        if(boardDto.getId() ==0) {
            //DB저장 할 수 있도록 DAO클래스의 메서드를 호출하기
            Board board = boardDto.createBoard();
            //1. Dto 객체를 Entity 객체로 저장
            boardRepository.save(board);
            //2.JAP 메서드 호출하기 = repository 호출하기(interface 형성 필요)
//        }else {
//            Board board = boardRepository.findById(boardDto.getId()).orElse(null);
//            board.setWriter(board.getWriter());
//            board.setContent(boardDto.getContent());
//            board.setTitle(boardDto.getTitle());
//
//
//        }
    }

    public BoardDto getBoard(int id) {
        //1.DB 조회 후 해당 테이블의 데이터 받아오기
        Board board = boardRepository.findById(id).orElse(new Board());
            //null값 오류 : 가장 많이 발생하는 오류 = null값인지 아닌지를 검사해야 함.
            //  처리 방법 : if(xxx!=null)과 같은 if문 사용 혹은 try-catch 방식
            // ★optional : null값 체크하는 새로운 방법.
            //              NullPointException(NPE)오류가 발생하지 않도록 돕는 방식.
            // 무조건 .get()을 사용하면 null값을 보낼 수도 있어서 문제 발생 가능
            // 그러나 .get() 과 optional을 동시에 쓰면 오류가 날 수 있으니까 둘 중 하나만 써야 한다.
        //2. DB에서 조회한 결과를 DTO로 변환
        BoardDto boardDto = BoardDto.of(board);
        return boardDto;
    }

    //글 삭제하기
    public void boardDelete(int id) {
        Board board = boardRepository.findById(id).orElse(null);
        //=조회된 값이 없으면 null로 지정하여 삭제가 안 되게 만들 것.
        boardRepository.delete(board);
    }


}
