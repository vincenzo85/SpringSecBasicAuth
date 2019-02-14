package it.clever.springsec.repo;

import org.springframework.stereotype.Repository;

import it.clever.springsec.entities.Role;

@Repository("roleRepository")
public interface RoleRepository extends GenericDao<Role>{
	Role findByRole(String role);

}