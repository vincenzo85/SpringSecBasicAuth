package it.clever.springsec.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.clever.springsec.entities.Role;
import it.clever.springsec.entities.User;
import it.clever.springsec.repo.GenericDaoImpl;
import it.clever.springsec.repo.RoleRepository;
import it.clever.springsec.repo.UserRepository;

@Service("userService")
public class UserServiceImpl extends GenericDaoImpl<User> implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(SecurityUtils.AUTHORITY_ROLE_USER);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.create(user);
	}

	@Override
	@Transactional
	public User update(User user) {
		userRepository.update(user);
		return user;
	}
	
	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean isUserExist(User user) {
		User userExists = findUserByEmail(user.getEmail());
		if (userExists != null) {
			return true;
		}
		return false;
	}

}