package com.sbu.boxoffice.exceptions;

public class NoSuchDataException extends Exception {
    @Override
    public String toString() {
        return "No such Data Found!";
    }
}
