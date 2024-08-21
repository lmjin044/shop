package com.japTest.control;


import com.japTest.Dto.BoardDto;
import com.japTest.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/community")
    public String cmt(Model model){
        //작성글을 가져와서 출력하는 역할
        model.addAttribute("boardListDto", boardService.all());
        return "board/index";
    }

    //글작성을 클릭하여 온 요청을 처리하는 부분
    @GetMapping("/community/write")
    public String write(Model model){
        model.addAttribute("boardDto", new BoardDto());
        return "board/write";
    }

    @GetMapping("/community/writeSave")
    public String save(@Valid BoardDto boardDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "board/write";
        }
        boardService.save(boardDto);
        return "redirect:/community";
    }

    //상세페이지 보기 요청
    @GetMapping("/community/detail")
    public String detail(@RequestParam("id") int id, Model model){
        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);
            //detail에서 boardDto라는 이름으로 받기로 결정했으므로 ""안은 동일하게 유지
        return "board/detail";
    }

    //글 삭제하는 방법
    @GetMapping("/community/delete")
    public String delete(@RequestParam("id") int id){
        boardService.boardDelete(id);
        return "redirect:/community";
            //삭제 했으면 글 목록으로 돌아가기
    }

    //글 수정하는 방법
    @GetMapping("/community/update")
    public String update(@RequestParam("id") int id, Model model){

        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);

        return "board/write";
    }

}
