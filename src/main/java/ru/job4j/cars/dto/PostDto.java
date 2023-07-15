package ru.job4j.cars.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDto {
    private String carName;
    private int bodyId;
    private int brandId;
    private String location;
    private boolean carNew;
    private int mileage;
    private int price;
    private int fuelId;
    private String transmission;
    private int produced;
    private int engineId;
    private String description;
    private String ownerPhone;

}
