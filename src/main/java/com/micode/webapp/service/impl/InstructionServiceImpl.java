package com.micode.webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micode.webapp.entity.Instruction;
import com.micode.webapp.repository.InstructionRepository;
import com.micode.webapp.service.InstructionService;

@Service
public class InstructionServiceImpl implements InstructionService {
	@Autowired
	InstructionRepository instructionRepository;
	@Override
	public Instruction getInstructionById(Integer id) {
		return instructionRepository.getOne(id);
	}

	

}
