package ma.ac.emi.coursemi.metier;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ma.ac.emi.coursemi.entities.Utilisateur;

@Service
public class UserDetailserviceImpl implements UserDetailsService{
	@Autowired
	private UserMetier userMetier;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = this.userMetier.findUserByEmail(username);
		if(user==null) throw new UsernameNotFoundException("invalid user ");
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
		return new User(user.getEmail(), user.getPassword(), authorities);
	}

}
