package org.micro.apps.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author tibinatomy
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
