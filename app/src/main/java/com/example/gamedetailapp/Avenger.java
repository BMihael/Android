package com.example.gamedetailapp;

public class Avenger {


    public String title;
    public String genre;
    public String imageLink;

    public Avenger(){

    }

    public Avenger(String title, String genre,String imageLink) {
        this.title = title;
        this.genre = genre;
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageLink(){
        return this.imageLink;
    }

    public void setImageLink(String imageLink){
        this.imageLink = imageLink;
    }

}
