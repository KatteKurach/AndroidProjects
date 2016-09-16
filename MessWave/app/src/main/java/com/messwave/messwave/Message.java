package com.messwave.messwave;

/**
 * Created by ekaterinakurach on 9/14/16.
 */
public class Message {

    private int ID;
    private String title;
    private String body;
    private String messanger;
    private boolean is_added;

    public Message(int ID, String type, String title, String body){
        this.ID = ID;
        this.title = title;
        this.body = body;
        this.messanger = type;
        this.is_added = false;
    }

    public void setIs_added(){
        is_added = true;
    }

    public int getID(){ return ID;}

    public boolean getIs_added(){
        return is_added;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public String getMessanger(){
        return messanger;
    }

}
