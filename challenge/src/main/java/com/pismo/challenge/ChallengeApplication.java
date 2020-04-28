package com.pismo.challenge;

import com.pismo.challenge.model.OperationType;
import com.pismo.challenge.repository.OperationTypeRepository;
import com.pismo.challenge.service.SequenceGeneratorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private OperationTypeRepository operationTypeRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		operationTypeRepository.deleteAll();

		OperationType ot1 = new OperationType(sequenceGeneratorService.generateSequence(OperationType.SEQUENCE_NAME), "COMPRA A VISTA");
		OperationType ot2 = new OperationType(sequenceGeneratorService.generateSequence(OperationType.SEQUENCE_NAME), "COMPRA PARCELADA");
		OperationType ot3 = new OperationType(sequenceGeneratorService.generateSequence(OperationType.SEQUENCE_NAME),"SAQUE");
		OperationType ot4 = new OperationType(sequenceGeneratorService.generateSequence(OperationType.SEQUENCE_NAME), "PAGAMENTO");

		operationTypeRepository.save(ot1);
		operationTypeRepository.save(ot2);
		operationTypeRepository.save(ot3);
		operationTypeRepository.save(ot4);

		List<OperationType> operationTypes = operationTypeRepository.findAll();

		for(OperationType ot: operationTypes){
			System.out.println(ot);
		}
	}
}
