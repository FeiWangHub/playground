package com.fei.playground.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Fei on 2020-04-20.
 * 脏数据异常
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class DirtyDataException extends Exception  {

    public DirtyDataException(String message) {
        super(message);
    }
}
