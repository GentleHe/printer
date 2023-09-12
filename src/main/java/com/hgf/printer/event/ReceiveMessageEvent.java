package com.hgf.printer.event;

public class ReceiveMessageEvent extends Event{
    private final String message;

    public ReceiveMessageEvent(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public static void fire(String message){
        Event.publish(new ReceiveMessageEvent(message));
    }

}
