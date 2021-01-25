package br.com.dlcars.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.dlcars.model.Client;
import br.com.dlcars.model.Dealership;
import br.com.dlcars.model.Seller;
import br.com.dlcars.model.Vehicle;
import br.com.dlcars.model.VehicleInsurer;
import br.com.dlcars.model.dto.SellerDto;
import br.com.dlcars.model.dto.InsurerVehicleDto;
import br.com.dlcars.model.dto.SellerDealershipDto;
import br.com.dlcars.model.dto.VehicleDto;
import br.com.dlcars.model.dto.VehicleInsurerDto;
import br.com.dlcars.repository.ClientRepository;
import br.com.dlcars.repository.DealershipRepository;
import br.com.dlcars.repository.SellerRepository;
import br.com.dlcars.repository.VehicleInsurerRepository;
import br.com.dlcars.repository.VehicleRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private VehicleRepository vhRepository;

	@Autowired
	private VehicleInsurerRepository vhInsurerRepository;

	@Autowired
	private ClientRepository clRepository;

	@Autowired
	private DealershipRepository dlRepository;

	@Autowired
	private SellerRepository slRepository;

	@Override
	public void run(String... args) throws Exception {

		VehicleInsurer c1 = new VehicleInsurer("1", "Colombano's Seguros");
		VehicleInsurer c2 = new VehicleInsurer("2", "Maia Seguros");
		VehicleInsurer c3 = new VehicleInsurer("3", "Pedro Seguros");

		VehicleInsurerDto cd1 = new VehicleInsurerDto(c1);
		VehicleInsurerDto cd2 = new VehicleInsurerDto(c2);

		Vehicle v1 = new Vehicle("01", "Car", "BMW", "M5", "ABCD-1234", 1, 679950.00, 9.0, cd1);
		Vehicle v2 = new Vehicle("02", "Car", "MERCEDES-BENZ", "GLC 250", "QWER-1234", 2, 320000.00, 8.7, cd1);
		Vehicle v3 = new Vehicle("03", "Motorcycle", "BMW", "R 1200 RT", "ASDF-1234", 6, 89900.00, 18.5, cd1);
		Vehicle v4 = new Vehicle("04", "Motorcycle", "Honda", "CBR 600RR", "ABCD-1235", 3, 47000.00, 18.0, cd2);
		Vehicle v5 = new Vehicle("05", "Motorcycle", "Yamaga", "YZF-R3", "XYZW-3332", 8, 19.300, 22.0, cd2);
		Vehicle v6 = new Vehicle("06", "Car", "Honda", "Civic LXS 1.8 AT 2010", "ZXCV-1234", 5, 38000.72, 6.5, cd2);

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

		Client cl1 = new Client("01", "Pedro Guilherme Maia Colombano", "540384653", "42699178810", "",
				"pedromecf@gmail.com", "Rua street, 140", 50000.0);
		Client cl2 = new Client("02", "Fulano da Silva Cicrano", "302784639", "76449564091", "", "fulano@outlook.com",
				"Avenida Paulista, 123", 1000000.0);
		Client cl3 = new Client("03", "Thomas Turbano", "149384555", "", "42571733000126", "thomas@gmail.com",
				"Rua Avenue, 233", 679240.45);

		Dealership dl1 = new Dealership("0001", "Vini's Best Vehicles", "11912345678", "vini.vehicles@contato.com",
				"Avenida Paulista 187");
		Dealership dl2 = new Dealership("0002", "Bamblobee Vehicles", "11943215678", "bamblo.beeV@enterprise.com",
				"Avenida São Miguel, 130");

		Seller s1 = new Seller("01", "Gustavo Henrique", "Employee", "gus@hotmail.com", 1230.78,
				new SellerDealershipDto(dl1));
		Seller s2 = new Seller("02", "João Pedro", "Employee", "jp123@hotmail.com", 2045.00,
				new SellerDealershipDto(dl1));
		Seller s3 = new Seller("03", "Paluh", "Employee's Manager", "foca.iluminada@hotmail.com", 7000.00,
				new SellerDealershipDto(dl1));
		Seller s4 = new Seller("04", "Zigueira", "Employee's Manager", "ziG@contato.com", 8900.00,
				new SellerDealershipDto(dl2));
		Seller s5 = new Seller("05", "Lagonis", "Employee", "vem.comigo.velho@lagoContato.com", 4500.00,
				new SellerDealershipDto(dl2));
		Seller s6 = new Seller("06", "NeskWga", "Employee's Manager", "neskin@hotmail.com", 8900.00,
				new SellerDealershipDto(dl2));

		SellerDto emp1 = new SellerDto(s1);
		SellerDto emp2 = new SellerDto(s2);
		SellerDto emp3 = new SellerDto(s3);
		SellerDto emp4 = new SellerDto(s4);
		SellerDto emp5 = new SellerDto(s5);
		SellerDto emp6 = new SellerDto(s6);

		// Add employees
		dl1.getEmployess().addAll(Arrays.asList(emp1, emp2, emp3));
		dl2.getEmployess().addAll(Arrays.asList(emp4, emp5, emp6));

		// Add vehicles
		dl1.getVehicles().addAll(Arrays.asList(vd1, vd2, vd3));
		dl2.getVehicles().addAll(Arrays.asList(vd4, vd5, vd6));

		vhInsurerRepository.saveAll(Arrays.asList(c1, c2, c3));
		vhRepository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6));
		clRepository.saveAll(Arrays.asList(cl1, cl2, cl3));
		dlRepository.saveAll(Arrays.asList(dl1, dl2));
		slRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6));

	}

}
