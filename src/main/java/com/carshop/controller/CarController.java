package com.carshop.controller;

import com.carshop.model.Car;
import com.carshop.repo.CarRepo;
import com.carshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private CarService carService;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("cars", carRepo.findAll());
        return "index";
    }

    @GetMapping("/add-car")
    public String newCarMenu(){
        return "new-car";
    }
    @PostMapping("/create-car")
    public String createCar(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String image,
                            @RequestParam Double price){

        Car car = new Car();
        car.setName(name);
        car.setDescription(description);
        car.setPrice(price);
        car.setImage(image);

        carRepo.save(car);

        return "redirect:/";
    }
    @PostMapping("/remove-car")
    public String removeCar(@RequestParam Long id){
        carRepo.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/edit-car")
    public String editCar(Model model,
                          @RequestParam("id") Long id,
                          @RequestBody String name,
                          @RequestBody String description,
                          @RequestBody String image,
                          @RequestBody double price){
        Date date = new Date();
        String imageSource = carService.getImage(image);
        Car car = carRepo.getById(id);
        model.addAttribute("car", car);
        car.setName(name);
        car.setPrice(price);
        car.setDescription(description);
        car.setImage(imageSource);
        car.setDate(date);

        return "edit-car";
    }

}
