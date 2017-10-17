package org.LTT.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
