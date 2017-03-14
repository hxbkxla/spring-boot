package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.Repository;

import sample.data.jpa.domain.Country;

public interface CountryRepository extends Repository<Country, String> {
	public Country findByName(String name);
	public List<Country> findAll();

}
