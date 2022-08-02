package com.fei.playground.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yuans on 2017-11-08.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception  {

}
