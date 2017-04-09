package com.cefisi.service;

import com.cefisi.dao.AdresseRepository;
import com.cefisi.dao.UserRepository;
import com.cefisi.dao.VerificationTokenRepository;
import com.cefisi.modeles.Adresse;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.VerificationToken;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

//import org.baeldung.persistence.dao.PasswordResetTokenRepository;
//import org.baeldung.persistence.dao.RoleRepository;
//import org.baeldung.persistence.dao.UserRepository;
//import org.baeldung.persistence.dao.VerificationTokenRepository;
//import org.baeldung.persistence.model.PasswordResetToken;
//import org.baeldung.persistence.model.User;
//import org.baeldung.persistence.model.VerificationToken;
//import org.baeldung.web.dto.UserDto;
//import com.cefisi.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IUserServiceImpli implements IUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

//    @Autowired
//    private PasswordResetTokenRepository passwordTokenRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private RoleRepository roleRepository;
    @Autowired
  //  private SessionRegistry sessionRegistry;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "SpringRegistration";

    // API
    @Override
    
//    public Personne registerNewUserAccount(final Personne accountDto) {
//        if (emailExist(accountDto.getEmail())) {
//            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
//        }
    public Adresse registerNewUserAccount(final Personne accountDto) {
        final Personne user = new Personne();

        
        Adresse adresse = new Adresse();
        adresse.setRue(accountDto.getRue());
        adresse.setVille(accountDto.getVille());
        adresse.setCodePostal(accountDto.getCodePostal());
        adresse.setMobile(accountDto.getMobile());
        adresse.setFixe(accountDto.getFixe());
        adresse.setPays(accountDto.getPays());
        
        user.setNom(accountDto.getNom());
        user.setPrenom(accountDto.getPrenom());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setProfil(Boolean.FALSE);
        
        Set<Personne> newUsers = new HashSet<Personne>();
        newUsers.add(user);
        adresse.setPersonnes(newUsers);
        System.out.println("com.cefisi.service.IUserServiceImpli.registerNewUserAccount()"+user.getProfil());
        adresse = adresseRepository.save(adresse);
        for (Personne per : adresse.getPersonnes()) {
            System.out.println("com.cefisi.service.IUserServiceImpli.registerNewUserAccount()// personne ajoute"+per.getIdPersonne());
            
        }
        
        return adresse;

        //return repository.save(user);
    }

//    @Override
//    public Personne getUser(final String verificationToken) {
//        final VerificationToken token = tokenRepository.findByToken(verificationToken);
//        if (token != null) {
//            return token.getUser();
//        }
//        return null;
//    }

//    @Override
//    public VerificationToken getVerificationToken(final String VerificationToken) {
//        return tokenRepository.findByToken(VerificationToken);
//    }

    @Override
    public void saveRegisteredUser(final Personne user) {
        
        repository.save(user);
        System.out.println("com.cefisi.service.IUserServiceImpli.saveRegisteredUser()");
    }

    @Override
    public void saveRegisteredAdressUser(final Adresse adresse) {
        System.out.println("com.cefisi.service.IUserServiceImpli.saveRegisteredAdressUser()");
        adresseRepository.save(adresse);
    }

//    @Override
//    public void deleteUser(final User user) {
//        final VerificationToken verificationToken = tokenRepository.findByUser(user);
//
//        if (verificationToken != null) {
//            tokenRepository.delete(verificationToken);
//        }
//
//        final PasswordResetToken passwordToken = passwordTokenRepository.findByUser(user);
//
//        if (passwordToken != null) {
//            passwordTokenRepository.delete(passwordToken);
//        }
//
//        repository.delete(user);
//    }
    @Override
    public void createVerificationTokenForUser(final Personne user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

//    @Override
//    public void createPasswordResetTokenForUser(final User user, final String token) {
//        final PasswordResetToken myToken = new PasswordResetToken(token, user);
//        passwordTokenRepository.save(myToken);
//    }
    @Override
    public Personne findUserByEmail(final String email) {
        return repository.findByEmail(email);
    }

//    @Override
//    public PasswordResetToken getPasswordResetToken(final String token) {
//        return passwordTokenRepository.findByToken(token);
//    }
//    @Override
//    public User getUserByPasswordResetToken(final String token) {
//        return passwordTokenRepository.findByToken(token).getUser();
//    }
//    @Override
//    public Personne getUserByID(final long id) {
//        return repository.findOne(id);
//    }

//    @Override
//    public void changeUserPassword(final User user, final String password) {
//        user.setPassword(passwordEncoder.encode(password));
//        repository.save(user);
//    }
//
//    @Override
//    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
//        return passwordEncoder.matches(oldPassword, user.getPassword());
//    }
    
    
    ///// c'est pour plus tard
//    @Override
//    public String validateVerificationToken(String token) {
//        final VerificationToken verificationToken = tokenRepository.findByToken(token);
//        if (verificationToken == null) {
//            return TOKEN_INVALID;
//        }
//
//        final Personne user = verificationToken.getUser();
//        final Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            tokenRepository.delete(verificationToken);
//            return TOKEN_EXPIRED;
//        }
//
//        Personne.setEnabled(true);
//        // tokenRepository.delete(verificationToken);
//        repository.save(user);
//        return TOKEN_VALID;
//    }

//    @Override
//    public String generateQRUrl(User user) throws UnsupportedEncodingException {
//        return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
//    }
//
//    @Override
//    public User updateUser2FA(boolean use2FA) {
//        final Authentication curAuth = SecurityContextHolder.getContext().getAuthentication();
//        User currentUser = (User) curAuth.getPrincipal();
//        currentUser.setUsing2FA(use2FA);
//        currentUser = repository.save(currentUser);
//        final Authentication auth = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), curAuth.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        return currentUser;
//    }
//    private boolean emailExist(final String email) {
//        return repository.findByEmail(email) != null;
//    }
//    @Override
//    public List<String> getUsersFromSessionRegistry() {
//        return sessionRegistry.getAllPrincipals().stream().filter((u) -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString).collect(Collectors.toList());
//    }
}
