package com.example.demo.Error.Exception;

import com.example.demo.Error.ErrorCode;

public class AirlineNameDuplicationException extends CustomException{
    public AirlineNameDuplicationException(){
        super("항공사이름이 중복됩니다.", ErrorCode.DUPLICATED_ENTITY);
    }
}
