package com.instacafe.moments.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractEntityDTO {
    private String id;
    private LocalDateTime created;
    private LocalDateTime modified;
}
