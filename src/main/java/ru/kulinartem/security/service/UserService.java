package ru.kulinartem.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kulinartem.security.dao.UserDao;
import ru.kulinartem.security.model.User;

import java.util.List;


public interface UserService extends DaoService<User> {

    @Override
    public User getItemById(long id);

    @Override
    public List<User> getAllItems();

    @Override
    public void saveItem(User item);

    @Override
    public void deleteItem(User item);

    @Override
    public void updateItem(User item, long id);
}
