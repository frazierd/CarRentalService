package com.nashss.se.rentalcarservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.nashss.se.rentalcarservice.models.AvailabilityEnum;
import com.nashss.se.rentalcarservice.models.CarClassEnum;

import java.math.BigDecimal;
import java.util.Objects;

@DynamoDBTable(tableName = "cars")
public class Car {
    private static final String CAR_AVAILABILITY_INDEX = "AvailabilityClassIndex";

    private String VIN;
    private String make;
    private String model;
    private CarClassEnum classOfVehicle;
    private BigDecimal costPerDay;
    private AvailabilityEnum availability;
    private String year;
    private Integer capacity;

    public Car(String VIN, String make, String model, CarClassEnum classOfVehicle, BigDecimal costPerDay, AvailabilityEnum availability, String year, Integer capacity) {
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.classOfVehicle = classOfVehicle;
        this.costPerDay = costPerDay;
        this.availability = availability;
        this.year = year;
        this.capacity = capacity;
    }

    @DynamoDBHashKey(attributeName = "VIN")
    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    @DynamoDBAttribute(attributeName = "Make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @DynamoDBAttribute(attributeName = "Model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = CAR_AVAILABILITY_INDEX, attributeName = "Class")
    public CarClassEnum getClassOfVehicle() {
        return classOfVehicle;
    }

    public void setClassOfVehicle(CarClassEnum classOfVehicle) {
        this.classOfVehicle = classOfVehicle;
    }

    @DynamoDBAttribute(attributeName = "Cost")
    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexHashKey(globalSecondaryIndexName = CAR_AVAILABILITY_INDEX, attributeName = "Availability")
    public AvailabilityEnum getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityEnum availability) {
        this.availability = availability;
    }

    @DynamoDBAttribute(attributeName = "Year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @DynamoDBAttribute(attributeName = "Capacity")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Car{" +
                "VIN='" + VIN + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", classOfVehicle=" + classOfVehicle +
                ", costPerDay=" + costPerDay +
                ", availability=" + availability +
                ", year='" + year + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getVIN().equals(car.getVIN()) && getMake().equals(car.getMake()) && getModel().equals(car.getModel()) && getClassOfVehicle() == car.getClassOfVehicle() && getCostPerDay().equals(car.getCostPerDay()) && getAvailability() == car.getAvailability() && getYear().equals(car.getYear()) && getCapacity().equals(car.getCapacity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVIN(), getMake(), getModel(), getClassOfVehicle(), getCostPerDay(), getAvailability(), getYear(), getCapacity());
    }
}