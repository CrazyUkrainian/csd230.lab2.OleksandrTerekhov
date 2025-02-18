web security in a spring mvc app: explanation & setup

introduction
spring boot security acts as your app's security guard. it ensures only authorized users gain access and integrates seamlessly with spring mvc. it is easy to configure and customize.

understanding the security setup
we have a file `WebSecurityConfig.java`, which handles all security-related configurations.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig

what does this do?
- `@Configuration`: tells spring this is a config file.
- `@EnableWebSecurity`: enables security features for the app.

security filter chain
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

this defines how security is applied, setting rules for authentication and access.

authorization rules
http
    .authorizeHttpRequests((requests) -> requests
        .requestMatchers("/", "/index.html").permitAll()
        .anyRequest().authenticated()
    );
- `requestMatchers("/", "/index.html").permitAll()`: allows public access to these pages.
- `anyRequest().authenticated()`: all other pages require login.

login page setup
.formLogin((form) -> form
    .loginPage("/login")
    .permitAll()
)
- sets `/login` as the custom login page.
- allows unrestricted access to it.

logout
.logout((logout) -> logout.permitAll());
- enables users to log out anytime.

user authentication setup
defining a test user
@Bean
public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("password")
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(user);
}
- creates a test user: `admin` with password `password`.
- stores user data in memory (not for production use).
- encodes passwords securely.

spring security’s default behavior
when you add `spring-boot-starter-security` to dependencies, spring automatically:
- locks everything by default.
- generates a random password if no user is set.
- provides basic authentication out of the box.

customizing security further
you can enhance security in multiple ways:
- real user accounts → replace `InMemoryUserDetailsManager` with a database-backed solution.
- admin/user roles → use `.hasRole("ADMIN")` to restrict pages to specific users.
- csrf protection → enabled by default, but can be disabled for apis.
- OAuth2 & JWT → use token-based authentication instead of passwords.

conclusion
spring boot security simplifies securing applications. customize `WebSecurityConfig.java` to control access, authentication, and session management. for production, replace in-memory users with database authentication.

