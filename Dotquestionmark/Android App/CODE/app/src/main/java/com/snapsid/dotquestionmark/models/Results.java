package com.snapsid.dotquestionmark.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Results implements Serializable {
    private Photos[] photos;

    private String id;

    private String place_id;

    private String icon;

    private String vicinity;

    private String scope;

    private String name;

    private String rating;

    private String[] types;

    private String reference;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    private Geometry geometry;

    private String international_phone_number;







    @SerializedName("opening_hours")
    private OpeningHours openingHours;

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public Photos[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photos[] photos) {
        this.photos = photos;
    }

    public String getRating() {
        return rating;
    }


    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getInternational_phone_number() {
        return international_phone_number;
    }

    public void setInternational_phone_number(String international_phone_number) {
        this.international_phone_number = international_phone_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {

        //String s="ClassPojo [photos = " + photos + ", id = " + id + ", place_id = " + place_id + ", icon = " + icon + ", vicinity = " + vicinity + ", international_phone_number = "+ international_phone_number + ", scope = " + scope + ", name = " + name + ", types = " + types + ", reference = " + reference + ", geometry = " + geometry + "]";
        //Log.d("aaaa",s);

        return "ClassPojo [photos = " + photos + ", url" + url + ", id = " + id + ", place_id = " + place_id + ", icon = " + icon + ", vicinity = " + vicinity + ", international_phone_number = " + international_phone_number + ", scope = " + scope + ", name = " + name + ", types = " + types + ", reference = " + reference + ", geometry = " + geometry + "]";

    }


}
