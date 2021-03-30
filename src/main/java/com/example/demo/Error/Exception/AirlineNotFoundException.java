package com.example.demo.Error.Exception;

import com.example.demo.Error.ErrorCode;

public class AirlineNotFoundException extends CustomException{
    public AirlineNotFoundException(){
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
