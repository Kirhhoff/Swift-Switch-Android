package com.example.win.easy.web.dto;

public class Response {

    private Object body;
    private Status status;
    private String message;


    public Response(Object body,Status status,String message){
        this.body=body;
        this.status=status;
        this.message=message;
    }

    public Object getBody(){return body;}
    public Status getStatus(){return status;}
    public String getMessage(){return message;}

    public enum Status{
        Success,
        Error
    }
}
