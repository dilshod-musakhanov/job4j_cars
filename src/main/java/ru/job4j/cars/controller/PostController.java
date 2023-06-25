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
    public String getMainPage(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
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
            postService.save(post);
            model.addAttribute("message", "New post added!");
            return "success/successPost";
        } catch (Exception e) {
            model.addAttribute("message", e);
            return "error/failedPost";
        }
    }

    @GetMapping("/post/{postId}")
    public String getPostById(Model model, @PathVariable int postId) {
        var postOptional = postService.findById(postId);
        if (postOptional.isEmpty()) {
            model.addAttribute("message", "Post not found");
            return "error/failedPost";
        }
        model.addAttribute("post", postOptional.get());
        return "post/post";
    }

    @GetMapping("/state/{id}")
    public String changeStatus(Model model, @PathVariable int id, @SessionAttribute User user) {
        var isStateUpdated = postService.updateState(id);
        model.addAttribute("user", user);
        if (!isStateUpdated) {
            model.addAttribute("message", "Error in updating status");
            return "error/failedPost";
        }
        model.addAttribute("message", "Your car is marked as SOLD");
        return "success/successPost";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(Model model, @PathVariable int id, @SessionAttribute User user) {
        var isDeleted = postService.delete(id);
        model.addAttribute("user", user);
        if (!isDeleted) {
            model.addAttribute("message", "Error in deleting post. Try again.");
            return "error/failedPost";
        }
        model.addAttribute("message", "Your post deleted successfully");
        return "success/successPost";
    }

    @GetMapping("/carNew")
    public String getPostsWithNewCars(Model model) {
        List<Post> newCars = postService.byCondition(true);
        if (newCars.isEmpty()) {
            model.addAttribute("message", "No posts found with new cars");
            return "error/failedPost";
        }
        model.addAttribute("posts", newCars);
        return "post/main";
    }

    @GetMapping("/carUsed")
    public String getPostsWithUsedCars(Model model) {
        List<Post> newCars = postService.byCondition(false);
        if (newCars.isEmpty()) {
            model.addAttribute("message", "No posts found with used cars");
            return "error/failedPost";
        }
        model.addAttribute("posts", newCars);
        return "post/main";
    }

    @GetMapping("/carElectric")
    public String getPostsWithElectricCars(Model model) {
        List<Post> electricCars = postService.byFuelType("Electric");
        if (electricCars.isEmpty()) {
            model.addAttribute("message", "No posts found with electric cars");
            return "error/failedPost";
        }
        model.addAttribute("posts", electricCars);
        return "post/main";
    }

    @GetMapping("/withPhotos")
    public String getPostsWithPhoto(Model model) {
        List<Post> postsWithPhotos = postService.findWithPhotos();
        if (postsWithPhotos.isEmpty()) {
            model.addAttribute("message", "No posts found with photos");
            return "error/failedPost";
        }
        model.addAttribute("posts", postsWithPhotos);
        return "post/main";
    }

    @GetMapping("/carSold")
    public String getPostsWithSoldCars(Model model) {
        List<Post> soldCars = postService.byStatus(true);
        if (soldCars.isEmpty()) {
            model.addAttribute("message", "No posts found with sold cars");
            return "error/failedPost";
        }
        model.addAttribute("posts", soldCars);
        return "post/main";
    }

    @GetMapping("/onSale")
    public String getPostsWithCarsOnSale(Model model) {
        List<Post> onSaleCars = postService.byStatus(false);
        if (onSaleCars.isEmpty()) {
            model.addAttribute("message", "No posts found with on sale cars");
            return "error/failedPost";
        }
        model.addAttribute("posts", onSaleCars);
        return "post/main";
    }

    @GetMapping("/recentlyPosted")
    public String getRecentlyPosted(Model model) {
        List<Post> newCars = postService.findByCreatedInThreeLastDays();
        if (newCars.isEmpty()) {
            model.addAttribute("message", "Recently created posts are not found");
            return "error/failedPost";
        }
        model.addAttribute("posts", newCars);
        return "post/main";
    }

    @GetMapping("/brandName")
    public String getPostsByBrandName(Model model, @RequestParam String brandName) {
        List<Post> postsByBrand = postService.findByBrandName(brandName);
        if (postsByBrand.isEmpty()) {
            model.addAttribute("message", "Posts by brand are not found. A brand spelling might be wrong.");
            return "error/failedPost";
        }
        model.addAttribute("posts", postsByBrand);
        return "post/main";
    }
}
