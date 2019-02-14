package it.clever.springsec.services;

import java.util.List;

import it.clever.springsec.entities.User;
import it.clever.springsec.repo.GenericDao;

public interface UserService extends GenericDao<User> {

	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> findAllUsers();
	public boolean isUserExist(User user);
}
