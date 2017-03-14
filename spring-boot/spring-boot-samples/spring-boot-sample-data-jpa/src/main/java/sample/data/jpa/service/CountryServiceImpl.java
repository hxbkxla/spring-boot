package sample.data.jpa.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.Country;

@Component("countryService")
@Transactional
public class CountryServiceImpl implements CountryService {
	private final CountryRepository countryRepository;
	
	public CountryServiceImpl( CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name);
	}

	@Override
	public List<Country> findAll() {
		return (List<Country>) countryRepository.findAll();
	}
	
	
	
}
