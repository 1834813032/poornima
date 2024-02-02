package com.greatLearning.collegeFest.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import java.util.*;
import com.greatLearning.collegeFest.entity.User;
import com.greatLearning.collegeFest.repository.UserRepository;
import com.greatLearning.collegeFest.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/*private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}*/
	 @Autowired
	 UserRepository userRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        
//        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        
//        return new MyUserDetails(user);
//    }
	@Override
	/*public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return new MyUserDetails(user);
	}*/
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUserDetails(user);
	}

}
