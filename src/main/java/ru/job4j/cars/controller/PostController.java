package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final BrandService brandService;
    private final BodyService bodyService;
    private final FuelService fuelService;
    private final TransmissionService transmissionService;
    private final EngineService engineService;
    private final OwnerService ownerService;
    private final FileService fileService;
    private final CarService carService;

    @GetMapping({"/", "/index"})
    public String getMainPage() {
        return "post/main";
    }

    @GetMapping("/addForm")
    public String addPost(Model model) {
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("bodies", bodyService.findAll());
        model.addAttribute("fuel", fuelService.findAll());
        model.addAttribute("engines", engineService.findAll());
        model.addAttribute("transmissions", transmissionService.findAll());
        return "post/add";
    }

    @PostMapping("/create")
    public String createPost(Model model, @ModelAttribute PostDto postDto, @SessionAttribute User user, @RequestPart("files") List<MultipartFile> files) {
        try {
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
            car.setTransmission(transmissionService.findById(postDto.getTransmissionId()).get());
            car.setEngine(engineService.findById(postDto.getEngineId()).get());
            car.setProduced(postDto.getProduced());
            car.setOwner(owner);
            car.getOwners().add(owner);
            System.out.println("****************  " + carService.save(car).get());
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
            System.out.println("*******************  " + postService.save(post));
            model.addAttribute("message", "New post added!");
            return "success/success";
        } catch (Exception e) {
            model.addAttribute("message", e);
            return "error/failedPost";
        }
    }

}
