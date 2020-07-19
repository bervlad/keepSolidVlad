package com.example.app.collections;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private int maxSpeed, capacity; String color, name;
    public Car() {

    }

    public Car (String name, String color,  int maxSpeed, int capacity) {
        this.maxSpeed=maxSpeed;
        this.capacity=capacity;
        this.color=color;
        this.name=name;
    }

    protected Car(Parcel in) {
        maxSpeed = in.readInt();
        capacity = in.readInt();
        color = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maxSpeed);
        dest.writeInt(capacity);
        dest.writeString(color);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {

       String output =  "Car name: " + this.getName()+ "\n"
                + "Car color: " + this.getColor() + "\n"
                + "Car max speed: " + this.getMaxSpeed() + "\n"
                + "Car capacity: " + this.getCapacity();
        return output;
    }
}
