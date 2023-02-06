package com.example.memo.common;

import com.example.memo.exception.StatusCode;
import jdk.jshell.Snippet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 커스텀 반환형, 상태코드/메세지, 반환할 데이터(있으면)를 한꺼번에 담아서 내려주는 용도
@Getter @Setter
@NoArgsConstructor
public class CustomResponseBody {
    private Object object;
    private String statusCode;
    private String statusMsg;

    public CustomResponseBody(StatusCode statusCode) {
//        this.statusCode = statusCode.getStatusCode();
//        this.statusMsg = statusCode.getStatusMsg();
        this(null, statusCode);
    }

    public CustomResponseBody(Object object, StatusCode statusCode) {
        this.object = object;
        this.statusCode = statusCode.getStatusCode();
        this.statusMsg = statusCode.getStatusMsg();
    }
}
