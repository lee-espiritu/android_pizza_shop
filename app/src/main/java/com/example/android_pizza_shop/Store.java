package com.example.android_pizza_shop;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {
    private int storeId;
    private String storeName;
    private String storeAddress;
    private String storePhone;

    public Store(int storeId, String storeName, String storeAddress, String storePhone) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storePhone = storePhone;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStorePhone() {
        return storePhone;
    }

    // Parcelable implementation

    protected Store(Parcel in) {
        storeId = in.readInt();
        storeName = in.readString();
        storeAddress = in.readString();
        storePhone = in.readString();
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(storeId);
        dest.writeString(storeName);
        dest.writeString(storeAddress);
        dest.writeString(storePhone);
    }
}
