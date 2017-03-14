package sample.data.jpa.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public abstract class BaseRepository implements CrudRepository<Object, String> {
	public  <T> T findById(Class<T> requiredType, String id) {
		return (T) findOne(id);
		
	}
	
	public  <T> List<T> findAll(Class<T> requiredType) {
		return (List<T>) findAll();
		
	}
	
}
