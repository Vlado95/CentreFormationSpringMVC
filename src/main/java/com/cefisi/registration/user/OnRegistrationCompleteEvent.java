package com.cefisi.registration.user;

import com.cefisi.modeles.Personne;
import java.util.Locale;

//import org.baeldung.persistence.model.User;
import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final Personne user;

    public OnRegistrationCompleteEvent(final Personne user, final Locale locale, final String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }

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

}
