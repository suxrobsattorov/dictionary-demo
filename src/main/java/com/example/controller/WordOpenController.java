package com.example.controller;

import com.example.dto.WordRequest;
import com.example.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Suxrob Sattorov, Mon 2:08 PM. 10/10/2022
 */

@Controller
@RequiredArgsConstructor
@RequestMapping( "/word" )
public class WordOpenController {

    private final WordService wordService;

    @GetMapping()
    public String index( Model model ) {
        model.addAttribute("wordList", wordService.get());
        return "word_open_index";
    }

//    @GetMapping()
//    public String getByEng( @ModelAttribute String eng, Model model ) {
//        model.addAttribute("wordList", wordService.findByEng(eng));
//        return "word_open_index";
//    }
//
//    @GetMapping()
//    public String getByUz( @ModelAttribute String uz, Model model ) {
//        model.addAttribute("wordList", wordService.findByUz(uz));
//        return "word_open_index";
//    }

}
