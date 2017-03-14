package com.cefisi.service;

import com.cefisi.modeles.Personne;
import com.cefisi.modeles.VerificationToken;
//import com.cefisi.web.dto.UserDto;
import java.io.UnsupportedEncodingException;
import java.util.List;

//import org.baeldung.persistence.model.PasswordResetToken;
//import org.baeldung.persistence.model.User;
//import org.baeldung.persistence.model.VerificationToken;
//import org.baeldung.web.dto.UserDto;
//import org.baeldung.web.error.UserAlreadyExistException;

public interface IUserService {

    Personne registerNewUserAccount(Personne accountDto) ;//throws UserAlreadyExistException;

    Personne getUser(String verificationToken);

    void saveRegisteredUser(Personne user);

    void deleteUser(Personne user);

    void createVerificationTokenForUser(Personne user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

   // void createPasswordResetTokenForUser(Personne user, String token);

    Personne findUserByEmail(String email);

   // PasswordResetToken getPasswordResetToken(String token);

    //Personne getUserByPasswordResetToken(String token);

    Personne getUserByID(long id);

    void changeUserPassword(Personne user, String password);

    boolean checkIfValidOldPassword(Personne user, String password);

    String validateVerificationToken(String token);

  //  String generateQRUrl(Personne user) throws UnsupportedEncodingException;

    //Personne updateUser2FA(boolean use2FA);

   // List<String> getUsersFromSessionRegistry();

}
