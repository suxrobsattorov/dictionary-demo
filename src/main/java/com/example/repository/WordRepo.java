package com.example.repository;

import com.example.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Suxrob Sattorov, Mon 11:05 AM. 10/10/2022
 */

public interface WordRepo extends JpaRepository<Word, Long> {

    Word findByEng(String eng);

    Word findByUz(String uz);

}
