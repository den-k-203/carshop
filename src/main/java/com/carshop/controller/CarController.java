package com.carshop.controller;

import com.carshop.model.Car;
import com.carshop.repo.CarRepo;
import com.carshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    CarService carService;

    @GetMapping("/")
    public List<Car> home(){
        return  carRepo.findAll();
    }
    @PostMapping("/new-car")
    public Car createCar(@RequestBody String name,
                            @RequestBody String description,
                            @RequestBody String image,
                            @RequestBody Double price){

        String imageSource = carService.getImage(image);
        Car car = new Car();
        car.setName(name);
        car.setDescription(description);
        car.setPrice(price);
        car.setImage(imageSource );

        return carRepo.save(car);
    }
    @PostMapping("/remove-car")
    public String removeCar(@RequestParam("id") Long id){
        carRepo.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/edit-car")
    public Car editCar(@RequestParam("id") Long id,
                          @RequestBody String name,
                          @RequestBody String description,
                          @RequestBody String image,
                          @RequestBody double price){
        Date date = new Date();
        String imageSource = carService.getImage(image);
        Car car = carRepo.getById(id);
        car.setName(name);
        car.setPrice(price);
        car.setDescription(description);
        car.setImage(imageSource);
        car.setDate(date);

        return carRepo.save(car);
    }

}
