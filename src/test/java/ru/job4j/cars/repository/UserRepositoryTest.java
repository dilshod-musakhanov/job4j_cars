package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import ru.job4j.cars.util.UtilRepository;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final UserRepository userRepository = new UserRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenSaveNewUserThenGetSameUser() {
        var user =  new User();
        user.setEmail("test@test.com");
        user.setPassword("password");
        userRepository.save(user);
        int id = user.getId();
        assertThat(userRepository.findById(id).get(), is(user));
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var user =  new User();
        user.setEmail("test@test.com");
        user.setPassword("password");
        userRepository.save(user);
        int id = user.getId();
        userRepository.delete(id);
        assertThat(userRepository.findById(id), is(Optional.empty()));
    }

}
