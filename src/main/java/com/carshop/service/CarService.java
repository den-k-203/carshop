package com.carshop.service;

import com.carshop.model.Car;
import com.carshop.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class CarService {
    public String getImage(String imageSource){
        Path sourcePath = Path.of(imageSource);

        File file = new File(imageSource);
        String fileName = file.getName();
        String fileExtension = getFileExtension(fileName);
        String destination = "image/"+file.getName()+ "/" + fileExtension;
        Path destinationPath = Path.of(destination);

        try {
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл переміщено успішно.");
        } catch (Exception e) {
            System.out.println("Сталася помилка при переміщенні файлу: " + e.getMessage());
        }
        return destination;
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return ""; // Якщо немає розширення або крапка - останній символ
        }
        return fileName.substring(dotIndex + 1);
    }
}
