package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final HibUserRepository userRepository = new HibUserRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM File").executeUpdate();
            session.createQuery("DELETE FROM Post").executeUpdate();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.createQuery("DELETE FROM Owner").executeUpdate();
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
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        userRepository.save(user);
        int id = user.getId();
        assertThat(userRepository.findById(id).get(), is(user));
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var user =  new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        userRepository.save(user);
        int id = user.getId();
        userRepository.delete(id);
        assertThat(userRepository.findById(id), is(Optional.empty()));
    }

    @Test
    public void whenFindByIdThenGetSameUser() {
        var user =  new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        userRepository.save(user);
        int id = user.getId();
        assertThat(userRepository.findById(id).get(), is(user));
    }

    @Test
    public void whenFindByLikeNameThenGetList() {
        var user =  new User();
        user.setName("test1");
        user.setEmail("userTest@test.com");
        user.setPassword("password1");
        var user2 =  new User();
        user2.setName("test2");
        user2.setEmail("userTest2@test.com");
        user2.setPassword("password2");
        userRepository.save(user);
        userRepository.save(user2);
        assertThat(userRepository.findByLikeName("test"), is(List.of(user, user2)));
    }

    @Test
    public void whenFindByEmailThenGetSameUser() {
        var user =  new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        userRepository.save(user);
        assertThat(userRepository.findByEmail("userTest@test.com").get(), is(user));
    }

    @Test
    public void whenFindByEmailAndPasswordThenGetSameUser() {
        var user =  new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        userRepository.save(user);
        assertThat(userRepository.findByEmailAndPassword("userTest@test.com", "password").get(), is(user));
    }
}