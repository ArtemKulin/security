package ru.kulinartem.security.dao;

import org.springframework.stereotype.Repository;
import ru.kulinartem.security.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User getItemById(long id) {
        return manager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllItems() {
        return  manager.createQuery("FROM User")
                .getResultList();
    }

    @Override
    public void saveItem(User item) {
        manager.merge(item);
    }

    @Override
    public void deleteItem(User item) {
        User deletedUser = manager.find(User.class, item.getId());
        manager.remove(deletedUser);
    }

    @Override
    public void updateItem(User item, long id) {
        User updatedUser = getItemById(id);
        updatedUser.setName(item.getName());
        updatedUser.setLastName(item.getLastName());
        updatedUser.setAge(item.getAge());
        updatedUser.setEmail(item.getEmail());
        manager.merge(updatedUser);
    }

    @Override
    public User getItemByEmail(String email) {
        return (User) manager
                .createQuery("FROM User u WHERE u.email =:parEmail")
                .setParameter("parEmail", email)
                .getSingleResult();
    }
}
