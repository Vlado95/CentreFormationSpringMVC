package com.cefisi.service;

import com.cefisi.modeles.Adresse;
import com.cefisi.modeles.Personne;
import com.cefisi.modeles.VerificationToken;
import java.io.UnsupportedEncodingException;
import java.util.List;

//import org.baeldung.persistence.model.PasswordResetToken;
//import org.baeldung.persistence.model.User;
//import org.baeldung.persistence.model.VerificationToken;
//import org.baeldung.web.dto.UserDto;
//import com.cefisi.web.error.UserAlreadyExistException;

public interface IUserService {

//    Personne registerNewUserAccount(Personne accountDto) ; //throws UserAlreadyExistException;
    
    Adresse registerNewUserAccount(Personne accountDto) ;

   // Personne getUser(String verificationToken);

    void saveRegisteredUser(Personne user);
    
    void saveRegisteredAdressUser(Adresse adresse);

   // void deleteUser(Personne user);

    void createVerificationTokenForUser(Personne user, String token);

   // VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

   // void createPasswordResetTokenForUser(User user, String token);

    Personne findUserByEmail(String email);

  //  PasswordResetToken getPasswordResetToken(String token);

   // User getUserByPasswordResetToken(String token);

    // Personne getUserByID(long id);

//    void changeUserPassword(User user, String password);
//
//    boolean checkIfValidOldPassword(User user, String password);

    
    // pour plus tard
    //String validateVerificationToken(String token);

//    String generateQRUrl(User user) throws UnsupportedEncodingException;
//
//    User updateUser2FA(boolean use2FA);

  //  List<String> getUsersFromSessionRegistry();

}
