package com.japTest.Dto;

import com.japTest.Entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListDto {
    private int id; //번호
    private String title;   //제목

    public static BoardListDto of(Board board){
        //엔티티를 Dto로 바꿔주는 메서드
        BoardListDto boardListDto = new BoardListDto();
        boardListDto.setId(board.getId());
        boardListDto.setTitle(board.getTitle());
    }
}


