package br.com.dlcars.model.util;

import br.com.dlcars.model.Client;
import br.com.dlcars.model.Vehicle;
import br.com.dlcars.model.dto.ClientDto;
import br.com.dlcars.model.dto.ClientVehicleDto;

public class Util {

	public static Client fromClientDto(ClientDto clientDto, Client newClient) {
		newClient = new Client(clientDto.getId(), clientDto.getName(), clientDto.getRg(), clientDto.getCpf(),
				clientDto.getCnpj(), clientDto.getCpf(), clientDto.getAdress(), clientDto.getBalance());
		return newClient;
	}

	public static ClientVehicleDto toClientVehicleDto(Vehicle vehicle) {
		ClientVehicleDto clientVehicle = new ClientVehicleDto(vehicle);
		return clientVehicle;
	}
}
