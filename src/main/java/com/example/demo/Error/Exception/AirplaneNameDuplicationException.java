package com.example.demo.Error.Exception;

import com.example.demo.Error.ErrorCode;

public class AirplaneNameDuplicationException extends CustomException{
    public AirplaneNameDuplicationException(){
        super("비행기 이름이 중복됩니다.", ErrorCode.DUPLICATED_ENTITY);
    }
}
