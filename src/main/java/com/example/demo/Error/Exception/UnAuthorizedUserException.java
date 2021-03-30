package com.example.demo.Error.Exception;

import com.example.demo.Error.ErrorCode;

public class UnAuthorizedUserException extends CustomException{
    public UnAuthorizedUserException(){
        super(ErrorCode.UNAUTHORIZED_USER);
    }
}
