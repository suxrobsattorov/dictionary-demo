package com.example.mapper;

import com.example.dto.WordRequest;
import com.example.dto.WordResponse;
import com.example.entity.Word;
import org.mapstruct.Mapper;

/**
 * @author Suxrob Sattorov, Mon 11:06 AM. 10/10/2022
 */

@Mapper( componentModel = "spring" )
public interface WordMapper {

    WordResponse toResponse( Word entity );

    Word toEntity( WordRequest request );

}
