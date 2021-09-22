package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.CharacterDao;

@Service
public class CharacterServiceImpl implements CharacterService {
	CharacterDao charDao;
	
	@Autowired
	public CharacterServiceImpl(CharacterDao charDao) {
		super();
		this.charDao = charDao;
	}

}
