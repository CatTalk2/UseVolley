package com.coder.usevolley;

/**
 * Created by QiWangming on 2015/5/18.
 */
public class Movie {
    private String name;
    private String rating;
    private String image;
    private String year;

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setImage(String imageUrl) {
        this.image = imageUrl;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }


    public Movie(){}
    public Movie(String name,String rating,String imageUrl,String year){
        this.name=name;
        this.rating=rating;
        this.image=imageUrl;
        this.year=year;
    }


}
