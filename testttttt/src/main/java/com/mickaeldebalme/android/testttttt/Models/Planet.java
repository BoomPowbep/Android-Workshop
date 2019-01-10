package com.mickaeldebalme.android.testttttt.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Planet implements Parcelable {

    private String image, nom, description;

    public Planet(String image, String nom, String description) {
        this.image = image;
        this.nom = nom;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.nom);
        dest.writeString(this.description);
    }

    protected Planet(Parcel in) {
        this.image = in.readString();
        this.nom = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Planet> CREATOR = new Parcelable.Creator<Planet>() {
        @Override
        public Planet createFromParcel(Parcel source) {
            return new Planet(source);
        }

        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };
}
