package com.example.sstuart_sizebook;
import java.io.Serializable;
import java.util.Date;

//Class that stores the data related to a person entry
//Includes getters/setters for each data member
//Overrides toString() to ensure readable data saved as Json and printed by MainActivity

//Cannot figure out how to save date without the time attached, results in improper date format
//when printed in MainActivity or set to EditView in EditEntryActivity
public class Person implements Serializable {

    private String name;
    private Date date;
    private double neck;
    private double bust;
    private double chest;
    private double waist;
    private double hip;
    private double inseam;
    private String comment;


    public Person(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {

            return date.toString();
    }

    public void setDate(Date date) {

        this.date = date;
    }

    public double getNeck() {

        return this.neck;
    }

    public void setNeck(double neck) {

        this.neck = neck;
    }

    public double getChest() {

        return this.chest;
    }

    public void setChest(double chest) {

        this.chest = chest;
    }

    public double getBust() {

        return this.bust;
    }

    public void setBust(double bust) {

        this.bust = bust;
    }

    public double getWaist() {
        return this.waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHip() {
        return this.hip;
    }

    public void setHip(double hip) {
        this.hip = hip;
    }

    public double getInseam() {
        return this.inseam;
    }

    public void setInseam(double inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString(){
        return " "+this.name + " | " + this.date.toString() + " | Neck: " + this.neck + " | Bust: "
                + this.bust+ " | Chest: " + this.chest + " | Waist: " + this.waist + " | Hip: "
                + this.hip+" | Inseam: " + this.inseam+ " | Comment: " + this.comment ;



    }

}
