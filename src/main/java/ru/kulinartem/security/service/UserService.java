package ru.kulinartem.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kulinartem.security.dao.UserDao;
import ru.kulinartem.security.model.User;

import java.util.List;

@Service
public class UserService implements DaoService<User> {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDAO) {
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
}
