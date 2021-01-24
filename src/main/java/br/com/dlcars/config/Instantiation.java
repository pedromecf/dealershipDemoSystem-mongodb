package br.com.dlcars.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.dlcars.model.Vehicle;
import br.com.dlcars.model.VehicleInsurer;
import br.com.dlcars.model.dto.InsurerVehicleDto;
import br.com.dlcars.model.dto.VehicleDto;
import br.com.dlcars.model.dto.VehicleInsurerDto;
import br.com.dlcars.repository.VehicleInsurerRepository;
import br.com.dlcars.repository.VehicleRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private VehicleRepository vhRepository;

	@Autowired
	private VehicleInsurerRepository vhInsurerRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
		VehicleInsurer c1 = new VehicleInsurer("1", "Colombano's Seguros");
		VehicleInsurer c2 = new VehicleInsurer("2", "Maia Seguros");
		VehicleInsurer c3 = new VehicleInsurer("3", "Pedro Seguros");
		
		
		VehicleInsurerDto cd1 = new VehicleInsurerDto(c1);
		VehicleInsurerDto cd2 = new VehicleInsurerDto(c2);
		
		
		Vehicle v1 = new Vehicle("01","Car", "BMW" ,"M5", "ABCD-1234", 1, 679950.00, 9.0, cd1);
		Vehicle v2 = new Vehicle("02","Car", "MERCEDES-BENZ","GLC 250", "QWER-1234", 2, 320000.00, 8.7, cd1);
		Vehicle v3 = new Vehicle("03","Motorcycle", "BMW","R 1200 RT", "ASDF-1234", 6, 89900.00, 18.5, cd1);
		Vehicle v4 = new Vehicle("04","Motorcycle", "Honda","CBR 600RR", "ABCD-1235", 3, 47000.00, 18.0, cd2);
		Vehicle v5 = new Vehicle("05", "Motorcycle", "Yamaga", "YZF-R3", "XYZW-3332", 8, 19.300, 22.0, cd2);
		Vehicle v6 = new Vehicle("06", "Car", "Honda","Civic LXS 1.8 AT 2010", "ZXCV-1234", 5, 38000.72, 6.5, cd2);
		
		
		VehicleDto vd1 = new VehicleDto(v1);
		VehicleDto vd2 = new VehicleDto(v2);
		VehicleDto vd3 = new VehicleDto(v3);
		VehicleDto vd4 = new VehicleDto(v4);
		VehicleDto vd5 = new VehicleDto(v5);
		VehicleDto vd6 = new VehicleDto(v6);
		
		InsurerVehicleDto vdt1 = new InsurerVehicleDto(vd1);
		InsurerVehicleDto vdt2 = new InsurerVehicleDto(vd2);
		InsurerVehicleDto vdt3 = new InsurerVehicleDto(vd3);
		InsurerVehicleDto vdt4 = new InsurerVehicleDto(vd4);
		InsurerVehicleDto vdt5 = new InsurerVehicleDto(vd5);
		InsurerVehicleDto vdt6 = new InsurerVehicleDto(vd6);
		
		c1.getVehicles().add(vdt1);
		c1.getVehicles().add(vdt2);
		c1.getVehicles().add(vdt3);
		c2.getVehicles().add(vdt4);
		c2.getVehicles().add(vdt5);
		c2.getVehicles().add(vdt6);
		
		vhInsurerRepository.saveAll(Arrays.asList(c1, c2, c3));
		vhRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6));
		
	}

}
