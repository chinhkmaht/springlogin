package org.LTT.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@ComponentScan(basePackages = { "org.LTT.security" })
// @ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    public SecSecurityConfig() {
        super();
    }


    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/fonts/**")
            .antMatchers("/images/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/index*","/index*","/searchuser*","/login*","/fragments*", "/logout*", "/signup/**", "/customLogin",
                        "/registrationConfirm*",
                        "/badUser*", "/user/resendRegistrationToken*" ,"/forgetPassword*","/searchusertop*", "/user/resetPassword*",
                        "/user/changePassword*", "/emailError*","/resources/**","/old/user/registration*","/successRegister*","/editeviews*","/qrcode*","/deletelogicuser*").permitAll()
                .antMatchers("/invalidSession*").anonymous()
                .antMatchers("/user/updatePassword*","/user/savePassword*","/updatePassword*","/new-period-timesheet*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .antMatchers("/university*","/userlist*","/saveuniversity*","/addcompanycard*","/saveassign*","/admin-edit-save-registration*","/listperiodtimesheet*","/addlocal*","/deleteassign*","/companycard*","/savecompanycard*","/registration*", "/user/registration*", "/deletelogiccompanycard*","/adduniversity*","/deletelogicuniversity*", "/signin/**").permitAll()
//                .antMatchers("/university*","/userlist*","/saveuniversity*","/addcompanycard*","/companycard*","/savecompanycard*","/registration*", "/user/registration*", "/deletelogiccompanycard*","/adduniversity*","/deletelogicuniversity*", "/signin/**").hasAuthority("WRITE_PRIVILEGE")
                .antMatchers( "/signin/**").hasAuthority("READ_PRIVILEGE")
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/homepage.html")
                .failureUrl("/login?error=true")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)

            .permitAll()
                .and()
            .sessionManagement()

                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
                .sessionFixation().none()
            .and()
            .logout()
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .invalidateHttpSession(false)
                .logoutSuccessUrl("/index.html")
                .deleteCookies("JSESSIONID")
                .permitAll();
    // @formatter:on
    }

    // beans


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}