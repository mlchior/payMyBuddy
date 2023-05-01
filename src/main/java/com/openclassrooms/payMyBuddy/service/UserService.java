package com.openclassrooms.payMyBuddy.service;
import com.openclassrooms.payMyBuddy.model.User;
import com.openclassrooms.payMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public Iterable<User> getUsers();

    public User getUserById(int userId);
    public void deleteById(Integer id);
    public void deleteByUsername(String username);
    public List<User> findAll();
    public Integer save(User user);

    public User getUserByEmail(String email);

    public void updateExistingPasswords();




        }

