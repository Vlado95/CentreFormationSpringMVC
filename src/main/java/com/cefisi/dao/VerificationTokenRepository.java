package com.cefisi.dao;

import com.cefisi.modeles.Personne;
import com.cefisi.modeles.VerificationToken;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;

public interface VerificationTokenRepository /*extends JpaRepository<VerificationToken, Long>*/ {

    VerificationToken findByToken(String token);
//
//    VerificationToken findByUser(Personne user);
//
//    Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);
//
//    void deleteByExpiryDateLessThan(Date now);

//    @Modifying
//    @Query("delete from VerificationToken t where t.expiryDate <= ?1")
//    void deleteAllExpiredSince(Date now);

    VerificationToken save(VerificationToken myToken);
}