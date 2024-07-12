package ru.stepup.demohikari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserDaoJdbc userDao;
    @Autowired
    public UserService(UserDaoJdbc userDao) {
        this.userDao = userDao;
    }

    public int save (User user) {
        return userDao.save(user);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }

    public void updateUser(int id, String name) {
        userDao.updateUser(id, name);
    }

    public void deleteAll() {
        userDao.deleteAll();
    }
}


