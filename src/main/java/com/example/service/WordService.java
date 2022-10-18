package com.example.service;

import com.example.dto.WordRequest;
import com.example.dto.WordResponse;
import com.example.entity.Word;

import java.util.List;

/**
 * @author Suxrob Sattorov, Mon 11:11 AM. 10/10/2022
 */

public interface WordService {

    WordResponse save( WordRequest request );

    WordResponse get( Long id );

    List<WordResponse> get();

    WordResponse edit( Long id, WordRequest request );

    WordResponse delete( Long id );

    Word getById( Long id );

    WordResponse findByEng(String eng);

    WordResponse findByUz(String uz);

}
