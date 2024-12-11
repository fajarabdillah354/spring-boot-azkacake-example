package com.azka_cake_project.service;


import com.azka_cake_project.entity.AppUser;
import com.azka_cake_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder encoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("username : {} not found ", username)
                ));
    }

    public AppUser register(AppUser user){
        boolean isExistUser = userRepository.findByUsername(user.getUsername()).isPresent();
        if (isExistUser){
            throw new RuntimeException(String.format("username : {} is exist", user));//jika username sudah ada dalam database maka lempar error
        }


        //jika belum password dari user di encode, lalu kita set itu sebagai password login
        String passwordEncoder = encoder.encode(user.getPassword());
        user.setPassword(passwordEncoder);
        return userRepository.save(user);
    }

}
