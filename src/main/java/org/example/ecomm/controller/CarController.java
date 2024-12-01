package org.example.ecomm.controller;

import org.example.ecomm.model.Car;
import org.example.ecomm.model.Wishlist;
import org.example.ecomm.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(@RequestParam(required = false) Long carId) {
        try {
            List<Car> cars = new ArrayList<Car>();

            carRepository.findAll().forEach(cars::add);

            if (cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/cars/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> getItemByItemId(@PathVariable(value = "carId") Long carId) {
        Optional<Car> carData = carRepository.findById(carId);
        if (carData.isPresent()) {
            return new ResponseEntity<>(carData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity<HttpStatus> deleteAllCars() {
        try {
            carRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car newCar) {
        try {
            System.out.println(newCar.getUser());
            System.out.println(newCar.getMake());
            Car car = new Car();
            car.setUser(newCar.getUser());
            car.setMake(newCar.getMake());
            car.setModel(newCar.getModel());
            car.setYear(newCar.getYear());
            car.setColor(newCar.getColor());
            Car _car = carRepository.save(car);
            return new ResponseEntity<>(_car, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cars/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable("carId") Long carId, @RequestBody Car carEntry) {
        Optional<Car> carData = carRepository.findById(carId);
        if (carData.isPresent()) {
            Car _car = carData.get();
            _car.setUser(carEntry.getUser());
            _car.setMake(carEntry.getMake());
            _car.setModel(carEntry.getModel());
            _car.setYear(carEntry.getYear());
            _car.setColor(carEntry.getColor());
            return new ResponseEntity<>(carRepository.save(_car), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars/{car_id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable(value = "car_id") Long car_id) {
        try {
            carRepository.deleteById(car_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
