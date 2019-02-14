package it.clever.springsec.repo;

import it.clever.springsec.entities.User;


public interface UserRepository extends GenericDao<User> {
	 User findByEmail(String email);
	 
}
