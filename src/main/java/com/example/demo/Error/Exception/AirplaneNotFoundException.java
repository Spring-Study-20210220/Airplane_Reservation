package com.example.demo.Error.Exception;

import com.example.demo.Error.ErrorCode;

public class AirplaneNotFoundException extends CustomException{
    public AirplaneNotFoundException(){
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
