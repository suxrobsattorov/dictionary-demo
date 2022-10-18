package com.example.service;

import com.example.dto.WordRequest;
import com.example.dto.WordResponse;
import com.example.entity.Word;
import com.example.mapper.WordMapper;
import com.example.repository.WordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Suxrob Sattorov, Mon 11:13 AM. 10/10/2022
 */


@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepo wordRepo;

    private final WordMapper wordMapper;

    @Override
    public WordResponse save( WordRequest request ) {
        Word word = wordRepo.findByEng(request.getEng());
        if ( word != null ) {
            if ( word.getUz().equals(request.getUz()) ) {
                throw new RuntimeException("These words exist");
            }
        }
        return wordMapper.toResponse(wordRepo.save(wordMapper.toEntity(request)));
    }

    @Override
    public WordResponse get( Long id ) {
        return wordMapper.toResponse(getById(id));
    }

    @Override
    public List<WordResponse> get() {
        List<WordResponse> wordResponseList = new LinkedList<>();
        for ( Word w : wordRepo.findAll() ) {
            wordResponseList.add(wordMapper.toResponse(w));
        }
        return wordResponseList;
    }

    @Override
    public WordResponse edit(Long id,  WordRequest request ) {

        Word word = getById(id);

        if ( request.getEng() != null ) word.setEng(request.getEng());
        if ( request.getUz() != null ) word.setUz(request.getUz());

        return wordMapper.toResponse(wordRepo.save(word));
    }

    @Override
    public WordResponse delete( Long id ) {
        Word word = getById(id);
        wordRepo.delete(word);
        return wordMapper.toResponse(word);
    }

    @Override
    public Word getById( Long id ) {
        return wordRepo.findById(id).orElseThrow(() -> new RuntimeException("Word not found"));
    }

    @Override
    public WordResponse findByEng( String eng ) {
        return wordMapper.toResponse(wordRepo.findByEng(eng));
    }

    @Override
    public WordResponse findByUz( String uz ) {
        return wordMapper.toResponse(wordRepo.findByUz(uz));
    }
}
