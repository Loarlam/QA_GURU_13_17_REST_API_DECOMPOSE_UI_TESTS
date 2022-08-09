package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/cookies.properties")
public interface CookiesConfig extends Config {
    String cookieForHeaderRegistration();
    String cookieForBodyRegistration();
}
