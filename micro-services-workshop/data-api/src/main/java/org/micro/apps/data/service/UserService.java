package org.micro.apps.data.service;

import org.micro.apps.common.dto.User;

/**
 * @author 207345
 */
public interface UserService extends iCrudService<User> {
    User getByEmail(String email);
}