package com.example.demo.Error.Exception;

import com.example.demo.Error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ErrorCode errorCode;

    CustomException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
    CustomException(String msg,ErrorCode errorCode){
        super(msg);
        this.errorCode=errorCode;
    }
}
