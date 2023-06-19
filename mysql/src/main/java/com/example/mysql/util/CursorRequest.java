package com.example.mysql.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursorRequest {
    public static final Long NONE_KEY = -1L;
    private Long key;
    private Long size;

    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }

    public Boolean hasKey() {
        return key != null;
    }
}
