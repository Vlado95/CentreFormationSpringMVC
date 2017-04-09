package com.cefisi.registration;

import com.cefisi.modeles.Adresse;
import com.cefisi.modeles.Personne;
import java.util.Locale;

//import org.baeldung.persistence.model.User;
import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final Adresse adresse;

    private final Personne user;

//    public OnRegistrationCompleteEvent(final Personne user, final Locale locale, final String appUrl) {
//        super(user);
//        this.user = user;
//        this.locale = locale;
//        this.appUrl = appUrl;
//    }
    public OnRegistrationCompleteEvent(final Adresse adresse, final Locale locale, final String appUrl, final Personne user) {
        super(adresse);
        System.out.println("com.cefisi.registration.OnRegistrationCompleteEvent.<init>()1");
        this.adresse = adresse;
        System.out.println("com.cefisi.registration.OnRegistrationCompleteEvent.<init>()2");
        this.user = user;
        System.out.println("com.cefisi.registration.OnRegistrationCompleteEvent.<init>()");
        this.locale = locale;
        this.appUrl = appUrl;
    }

//    public OnRegistrationCompleteEvent(Adresse registered, Locale locale, String appUrl) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    //
    public String getAppUrl() {
        return appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public Personne getUser() {
        return user;
    }

    public Adresse getAdresse() {
        return adresse;
    }

}
