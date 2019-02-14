package it.clever.springsec.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class SecurityUtils {

	public final static String AUTHORITY_ROLE_USER = "USER";
	public final static String AUTHORITY_ROLE_ADMIN = "ADMIN";
	public final static String AUTHORITY_ROLE_SUPERADMIN = "SUPERADMIN";
	
	
	public static boolean hasRoleAdmin(Collection<GrantedAuthority> authorities) {
		for(GrantedAuthority auth : authorities) {
			if(auth.getAuthority().equals(AUTHORITY_ROLE_ADMIN)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasRoleSuperAdmin(Collection<? extends GrantedAuthority> collection) {
		for(GrantedAuthority auth : collection) {
			if(auth.getAuthority().equals(AUTHORITY_ROLE_SUPERADMIN)) {
				return true;
			}
		}
		return false;
	}
}
