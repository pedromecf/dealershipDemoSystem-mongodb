package br.com.dlcars.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.dlcars.model.Car;
import br.com.dlcars.model.Motorcycle;
import br.com.dlcars.model.Vehicle;
import br.com.dlcars.model.VehicleInsurer;
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
		
		VehicleInsurer c1 = new VehicleInsurer("1001", "Colombano's seguros");
		VehicleInsurer c2 = new VehicleInsurer("1002", "Bibiano's seguros");
		VehicleInsurer c3 = new VehicleInsurer("1003", "Maia's seguros");
		vhInsurerRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Vehicle v1 = new Car("001", "Celta", 0, 5000.00, 13.5, c1, "ABCD-1234");
		Vehicle v2 = new Motorcycle("002", "Honda CBR 600RR", 3, 54835.00, 4.7, c3, "ABCD-1235");
		Vehicle v3 = new Motorcycle("003", "Yamaga YZF-R3", 12, 22300.00, 9.3, c2, "XYZW-3332");
		vhRepository.saveAll(Arrays.asList(v1, v2, v3));
		
	}

}
