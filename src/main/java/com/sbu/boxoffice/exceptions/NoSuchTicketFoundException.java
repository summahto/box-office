package com.sbu.boxoffice.exceptions;

public class NoSuchTicketFoundException extends Exception {

    @Override
    public String toString() {
        return "No Such Ticket found!";
    }
}
