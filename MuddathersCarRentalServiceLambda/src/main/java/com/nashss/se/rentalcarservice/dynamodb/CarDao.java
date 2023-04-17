package com.nashss.se.rentalcarservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.rentalcarservice.dynamodb.models.Car;

import javax.inject.Inject;
import java.util.*;

public class CarDao {

    private static final String CAR_AVAILABILITY_INDEX = "AvailabilityClassIndex";
    private final DynamoDBMapper mapper;

    @Inject
    public CarDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public List<Car> searchCars(String[] criteria) {

        ArrayList<Car> carList = new ArrayList<>();

        if (Objects.equals(criteria[1], "none")) {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":availability", new AttributeValue().withS(criteria[0]));

            DynamoDBQueryExpression<Car> queryExpression = new DynamoDBQueryExpression<Car>()
                    .withIndexName(CAR_AVAILABILITY_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("availability = :availability")
                    .withExpressionAttributeValues(valueMap);

            PaginatedQueryList<Car> carsFromGSI = mapper.query(Car.class, queryExpression);

            for (Car car : carsFromGSI) {
                Car actualCar = mapper.load(Car.class, car.getVIN());
                carList.add(actualCar);
            }
        }
        else {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":availability", new AttributeValue().withS(criteria[0]));
            valueMap.put(":class", new AttributeValue().withS(criteria[1]));

            DynamoDBQueryExpression<Car> queryExpression = new DynamoDBQueryExpression<Car>()
                    .withIndexName(CAR_AVAILABILITY_INDEX)
                    .withConsistentRead(false)
                    .withKeyConditionExpression("availability = :availability and classOfVehicle = :class")
                    .withExpressionAttributeValues(valueMap);

            PaginatedQueryList<Car> carsFromGSI = mapper.query(Car.class, queryExpression);

            for (Car car : carsFromGSI) {
                Car actualCar = mapper.load(Car.class, car.getVIN());
                carList.add(actualCar);
            }
        }
        return carList;
    }
}
