package com.pedro.demojsf.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.pedro.demojsf.model.Car;
import com.pedro.demojsf.repositories.CarRepository;

import lombok.Data;

@Named
@ViewScoped
@Data
public class CarViewController {

	private CarRepository carRepository;
	private List<Car> cars;
	private Car car;

	// injection de dependance par setter
	@Autowired
	public void setCarRepository(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	// s'excute avant le constructeur
	@PostConstruct
	public void init() {
		car = new Car();
		// recuperer la liste des cars avant l'execution du constructeur
		cars = carRepository.findAll();
	}
	
	public CarViewController() {
	}
	
	public CarViewController(Car car) {
		this.car = car;
	}

	public String gotoCreateCar() {
		return "create-car.xhtml?faces-redirect=true";
	}
	
	public String gotoEmpresas() {
		return "GestaoEmpresas.xhtml?faces-redirect=true";
	}

	public String saveCar() {
		carRepository.save(car);
		cars = carRepository.findAll();
		return "index.xhtml?faces-redirect=true";
	}
	
	public List<Car> getCars() {
		return this.cars;
	}
	
	public Car getCar(){
		return this.car;
	}

}
