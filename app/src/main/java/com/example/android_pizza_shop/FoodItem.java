package com.example.android_pizza_shop;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodItem implements Parcelable {

    private String imageResource;
    private String name;
    private String kilojoules;
    private String foodInfo;
    private String priceInfo;
    private String productType;

    public FoodItem(String imageResource, String kilojoules, String foodInfo, String price, String name, String productType) {
        this.imageResource = imageResource;
        this.kilojoules = kilojoules;
        this.foodInfo = foodInfo;
        this.priceInfo = price;
        this.name = name;
        this.productType = productType;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getKilojoules() {
        return kilojoules;
    }

    public String getFoodInfo() {
        return foodInfo;
    }

    public String getPriceInfo(){
        return priceInfo;
    }

    public String getName() { return name; }

    // Parcelable implementation
    protected FoodItem(Parcel in) {
        imageResource = in.readString();
        name = in.readString();
        kilojoules = in.readString();
        foodInfo = in.readString();
        priceInfo = in.readString();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageResource);
        dest.writeString(name);
        dest.writeString(kilojoules);
        dest.writeString(foodInfo);
        dest.writeString(priceInfo);
    }

    public String getProductType() {
        return productType;
    }
}