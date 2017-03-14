/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.data.jpa.domain.Country;
import sample.data.jpa.service.CityService;
import sample.data.jpa.service.CountryService;


@RestController
public class SampleController {

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;

	@GetMapping("/")
	@Transactional(readOnly = true)
	public String helloWorld() {
		return this.cityService.getCity("Bath", "UK").getName();
	}
	
	@GetMapping("/getCountryByName")
	@Transactional(readOnly = true)
	public String getCountryByName(String name) {
		return countryService.findByName(name).toString();
	}
	
	@GetMapping("/getAll")
	@Transactional(readOnly = true)
	public List<Country> getAll() {
		return countryService.findAll();
	}
}
