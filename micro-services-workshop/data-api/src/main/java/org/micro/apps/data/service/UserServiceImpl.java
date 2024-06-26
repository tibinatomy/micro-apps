package org.micro.apps.data.service;

import lombok.RequiredArgsConstructor;
import org.micro.apps.common.dto.User;
import org.micro.apps.common.exception.ResourceNotFoundException;
import org.micro.apps.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author tibinatomy
 */

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User object) {
        return userRepository.save(object);
    }

    @Override
    public User update(User object) {

        Optional<User> user = userRepository.findById(object.getId());
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setId(object.getId());
            updateUser.setEmail(object.getEmail());
            updateUser.setPassword(object.getPassword());
            updateUser.setName(object.getName());
            updateUser.setRole(object.getRole());
            updateUser.setToken(object.getToken());
            updateUser.setUpdated(new Date());
            userRepository.save(updateUser);
            return updateUser;
        }
        throw new ResourceNotFoundException("User not found with Id " + object.getId());
    }

    @Override
    public User get(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("User not found with Id " + id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(long id) {
        Optional<User> userByEmail = userRepository.findById(id);

         if (userByEmail.isPresent()){
             userRepository.delete(userByEmail.get());
         }
         throw new RuntimeException("User not found with Id " + id);
    }

    @Override
    public User getByEmail(String email){
        List<User> userByEmail = userRepository.findByEmail(email);
        if(CollectionUtils.isEmpty(userByEmail)){
            throw new ResourceNotFoundException("User not found with email " + email);
        }
        return userByEmail.get(0);
    }

}
