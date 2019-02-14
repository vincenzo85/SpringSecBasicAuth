/**
 * UserRepositoryImpl.java
 *
 * robgion
 * www.2clever.it
 * 
 * 31 ott 2017
 * For further information please write to info@2clever.it
 */
package it.clever.springsec.repo;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.clever.springsec.entities.Role;
import it.clever.springsec.entities.User;

/**
 * @author robgion
 *
 */
@Repository(value = "roleRepository")
public class RoleRepositoryImpl extends GenericDaoImpl<Role> implements RoleRepository {

	@Override
	public Role findByRole(String roleCode) {
		
		Role role = null;
		Query query = this.em.createQuery("select o from Role o where o.role = :role");
		query.setParameter("role", roleCode);
		role = (Role)query.getSingleResult();
		return role;
	}

}
