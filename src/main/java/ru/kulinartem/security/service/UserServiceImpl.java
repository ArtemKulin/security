package ru.kulinartem.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kulinartem.security.dao.UserDao;
import ru.kulinartem.security.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDAO) {
        this.userDao = userDAO;
    }

    @Transactional
    @Override
    public User getItemById(long id) {
        return userDao.getItemById(id);
    }

    @Transactional
    @Override
    public List<User> getAllItems() {
        return userDao.getAllItems();
    }

    @Transactional
    @Override
    public void saveItem(User item) {
        userDao.saveItem(item);
    }

    @Transactional
    @Override
    public void deleteItem(User item) {
        userDao.deleteItem(item);
    }

    @Transactional
    @Override
    public void updateItem(User item, long id) {
        userDao.updateItem(item, id);
    }

    @Transactional
    @Override
    public User getItemByEmail(String email) {
        return userDao.getItemByEmail(email);
    }
}
