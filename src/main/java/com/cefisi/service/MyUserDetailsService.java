package com.cefisi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefisi.dao.UserDao;
import com.cefisi.modeles.Personne;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Personne user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user);

        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
//    private User buildUserForAuthentication(Personne user, List<GrantedAuthority> authorities) {
//        return new User(user.getEmail(), user.getPassword(), true /*user.isEnabled()*/, true, true, true, authorities);
//    }
    
     // Converts com.mkyong.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(Personne user, List<GrantedAuthority> authorities) {
        return new CustomUser(user.getEmail(), user.getPassword(), true /*user.isEnabled()*/, true, true, true, authorities,
          user.getIdPersonne(),user.getNom(),user.getPrenom(), user.getPromo(),user.getRole());
    }

    private List<GrantedAuthority> buildUserAuthority(Personne user) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority(user.getRole()));

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

}
