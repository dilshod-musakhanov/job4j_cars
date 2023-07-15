package ru.job4j.cars.mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.*;

import java.io.IOException;
import java.util.List;

@Component
public class PostMapper {
    private final OwnerService ownerService;
    private final BrandService brandService;
    private final BodyService bodyService;
    private final FuelService fuelService;
    private final EngineService engineService;
    private final CarService carService;
    private final FileService fileService;

    private PostMapper(
            OwnerService ownerService,
            BrandService brandService,
            BodyService bodyService,
            FuelService fuelService,
            EngineService engineService,
            CarService carService,
            FileService fileService
    ) {
        this.ownerService = ownerService;
        this.brandService = brandService;
        this.bodyService = bodyService;
        this.fuelService = fuelService;
        this.engineService = engineService;
        this.carService = carService;
        this.fileService = fileService;
    }

    public Post toPost(PostDto postDto, User user, List<MultipartFile> files) throws IOException {
        var owner = ownerService.findByUser(user).orElseGet(() -> {
            Owner newOwner = new Owner();
            newOwner.setUser(user);
            newOwner.setName(user.getName());
            newOwner.setPhone(postDto.getOwnerPhone());
            ownerService.save(newOwner);
            return newOwner;
        });
        var car = new Car();
        car.setName(postDto.getCarName());
        car.setBody(bodyService.findById(postDto.getBodyId()).get());
        car.setBrand(brandService.findById(postDto.getBrandId()).get());
        car.setFuel(fuelService.findById(postDto.getFuelId()).get());
        car.setTransmission(Transmission.valueOf(postDto.getTransmission()));
        car.setEngine(engineService.findById(postDto.getEngineId()).get());
        car.setProduced(postDto.getProduced());
        car.setOwner(owner);
        car.getOwners().add(owner);
        carService.save(car).get();
        var post = new Post();
        post.setDescription(postDto.getDescription());
        post.setPrice(postDto.getPrice());
        post.setCarNew(postDto.isCarNew());
        post.setLocation(postDto.getLocation());
        post.setMileage(postDto.getMileage());
        post.setCar(car);
        post.setUser(user);
        List<File> photos = fileService.convertMultipartFileToFile(files);
        post.setFiles(photos);
        return post;
    }
}
