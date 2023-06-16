package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.service.*;

@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final BrandService brandService;
    private final BodyService bodyService;
    private final FuelService fuelService;
    private final TransmissionService transmissionService;
    private final EngineService engineService;

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

}
