package com.japTest.Dto;

import com.japTest.Entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardDto {
    private String id;

    @NotBlank (message="작성자명을 입력해주세요.")
    private String writer;

    @Size(min=4, max=200, message = "제목은 4자~200자 내로 작성해주세요")
    private String title;

    @NotEmpty(message = "내용 입력은 필수입니다.")
    private String content;

    private static ModelMapper modelMapper = new ModelMapper();
    public Board createBoard(){
        return modelMapper.map(this, Board.class);
        //조건 : 변수 이름이 같아야만 가능
    }

//    public Board createBoard(){
//        Board board =new Board();
//        board.setContent(this.content);
//        board.setTitle(this.title);
//        board.setWriter(this.writer);
//        return board;
//    }


}
