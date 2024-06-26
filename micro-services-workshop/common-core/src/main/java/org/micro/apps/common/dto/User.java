package org.micro.apps.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tibinatomy
 */

@Entity
@Table(name = "UserRegistry",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "password"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "token")
    private String token;

    @CreationTimestamp
    private Date created;

    @CreationTimestamp
    private Date updated;
}
