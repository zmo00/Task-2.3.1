package task.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import task.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    //Реализация через SessionFactory
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    public UserDaoImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public void create(User user) {
//        sessionFactory.getCurrentSession().save(user);
//    }
//
//    @Override
//    public User read(long id) {
//        return (User) sessionFactory.getCurrentSession().get(User.class, id);
//    }
//
//    @Override
//    public void update(User user) {
//        sessionFactory.getCurrentSession().update(user);
//    }
//
//    @Override
//    public void delete(User user) {
//        sessionFactory.getCurrentSession().delete(user);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<User> getUsers() {
//        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
//        return query.getResultList();
//    }


    //Реализация через EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public User read(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
        User updateUser = entityManager.find(User.class, user.getId());
        entityManager.detach(updateUser);
        updateUser.setName(user.getName());
        updateUser.setLastname(user.getLastname());
        updateUser.setAge(user.getAge());
        updateUser.setEmail(user.getEmail());
        entityManager.merge(updateUser);
        entityManager.flush();
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.find(User.class, user.getId()));
        entityManager.flush();
    }

    @Override
    public List<User> getUsers() {
        List<Object[]> objects =
                entityManager.createQuery("select id, name, lastname, age, email from User").getResultList();
        List<User> userList = new ArrayList<User>();
        for (Object[] object : objects) {
            User user = new User((String) object[1], (String) object[2], (Byte) object[3], (String) object[4]);
            user.setId((Long) object[0]);
            userList.add(user);
        }
        return userList;
    }
}
