package com.company.exceptions;

/**
 * Created by Marius on 3/10/2017.
 */
public class MyNumberFormatException extends RuntimeException {

    public MyNumberFormatException(String str) {
        super(str);
    }
}
