package ru.kulinartem.security.dao;

import ru.kulinartem.security.model.User;

import java.util.List;

public interface UserDao extends Dao<User>{

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
