package sample.data.jpa.service;

import java.util.List;

import sample.data.jpa.domain.Country;

public interface CountryService {
	public Country findByName(String name);
	public List<Country> findAll();

}
