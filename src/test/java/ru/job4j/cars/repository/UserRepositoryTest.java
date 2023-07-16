package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.util.UtilModels;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


public class UserRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final HibernateUserRepository userRepository = new HibernateUserRepository(UtilRepository.getCrudRepository());

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
        var user = UtilModels.createUser("userTest", "userTest@test.com", "password");
        userRepository.save(user);
        int id = user.getId();
        assertThat(userRepository.findById(id).get()).isEqualTo(user);
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var user = UtilModels.createUser("userTest", "userTest@test.com", "password");
        userRepository.save(user);
        int id = user.getId();
        userRepository.delete(id);
        assertThat(userRepository.findById(id)).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindByIdThenGetSameUser() {
        var user = UtilModels.createUser("userTest", "userTest@test.com", "password");
        userRepository.save(user);
        int id = user.getId();
        assertThat(userRepository.findById(id).get()).isEqualTo(user);
    }

    @Test
    public void whenFindByLikeNameThenGetList() {
        var user = UtilModels.createUser("userTest", "userTest@test.com", "password");
        userRepository.save(user);
        var user2 = UtilModels.createUser("userTest2", "userTest2@test.com", "password2");
        userRepository.save(user2);
        assertThat(userRepository.findByLikeName("Test")).isEqualTo(List.of(user, user2));
    }

    @Test
    public void whenFindByEmailThenGetSameUser() {
        var user = UtilModels.createUser("userTest", "userTest@test.com", "password");
        userRepository.save(user);
        assertThat(userRepository.findByEmail("userTest@test.com").get()).isEqualTo(user);
    }

    @Test
    public void whenFindByEmailAndPasswordThenGetSameUser() {
        var user = UtilModels.createUser("userTest", "userTest@test.com", "password");
        userRepository.save(user);
        assertThat(userRepository.findByEmailAndPassword("userTest@test.com", "password").get()).isEqualTo(user);
    }

}