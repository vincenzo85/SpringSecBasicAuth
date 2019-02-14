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

import it.clever.springsec.entities.User;

/**
 * @author robgion
 *
 */
@Repository(value = "userRepository")
public class UserRepositoryImpl extends GenericDaoImpl<User> implements UserRepository {

	@SuppressWarnings("unchecked")
	@Override
	public User findByEmail(String email) {
		
		Query query = this.em.createQuery("select u from User u where u.email = :email");
		User retUser = null;
		List<User> retList = query.setParameter("email", email).getResultList();
		if(retList != null && retList.size() >0) {
			retUser = retList.get(0);
		}

		return retUser;
	}

}
