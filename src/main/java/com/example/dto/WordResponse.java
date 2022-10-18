package com.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * @author Suxrob Sattorov, Mon 11:00 AM. 10/10/2022
 */

@Getter
@Setter
@Builder
public class WordResponse {
    private Long id;
    private String eng;
    private String uz;
    private LocalDateTime createdAt;
}
