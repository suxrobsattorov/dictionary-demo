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
@RequestMapping( "/admin" )
public class WordController {

    private final WordService wordService;

    @GetMapping()
    public String index( Model model ) {
        model.addAttribute("wordList", wordService.get());
        return "word_index";
    }

    @GetMapping( "/save" )
    public String create( Model model ) {
        model.addAttribute("word", new WordRequest());
        model.addAttribute("isEdit", false);
        return "word_create";
    }

    @PostMapping( "/save" )
    public String save( @ModelAttribute WordRequest request ) {
        wordService.save(request);
        return "redirect:/admin";
    }

    @PostMapping( "/get/{id}" )
    public String get( @PathVariable( "id" ) Long id, Model model ) {
        model.addAttribute(wordService.get(id));
        return "word_index";
    }

    @GetMapping( "/edit/{id}" )
    public String edit( @PathVariable( "id" ) Long id, Model model ) {
        model.addAttribute("word", wordService.get(id));
        model.addAttribute("isEdit", true);
        return "word_create";
    }

    @PostMapping( "/edit/{id}" )
    public String edit( @PathVariable( "id" ) Long id,
                        @ModelAttribute WordRequest request ) {
        wordService.edit(id, request);
        return "redirect:/admin";
    }

    @GetMapping( "/delete/{id}" )
    public String delete( @PathVariable( "id" ) Long id ) {
        wordService.delete(id);
        return "redirect:/admin";
    }
}
