package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/credentials.properties")
public interface CredentialsConfig extends Config {
    String login();
    String password();
    String cookieForHeaderRegistration();
    String cookieForBodyRegistration();
//    String remoteLink();
}