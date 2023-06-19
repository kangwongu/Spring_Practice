package com.example.mysql.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCursor<T> {
    CursorRequest nextCursorRequest;
    List<T> content;
}
