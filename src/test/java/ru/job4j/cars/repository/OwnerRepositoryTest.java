package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OwnerRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final HibOwnerRepository ownerRepository = new HibOwnerRepository(UtilRepository.getCrudRepository());
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
    public void whenSaveNewOwnerThenGetSameOwner() {
        var userTest = new User();
        userTest.setName("userTest");
        userTest.setEmail("userTest@test.com");
        userTest.setPassword("password");
        var user = userRepository.save(userTest).get();
        var owner = new Owner();
        owner.setName("test1");
        owner.setPhone("123456");
        owner.setUser(user);
        ownerRepository.save(owner);
        int id = owner.getId();
        assertThat(ownerRepository.findById(id).get(), is(owner));
    }

    @Test
    public void whenUpdateOwnerThenGetUpdatedOwner() {
        var userTest = new User();
        userTest.setName("userTest");
        userTest.setEmail("userTest@test.com");
        userTest.setPassword("password");
        var user = userRepository.save(userTest).get();
        var owner = new Owner();
        owner.setName("test1");
        owner.setPhone("123456");
        owner.setUser(user);
        ownerRepository.save(owner);
        int id = owner.getId();
        owner.setName("test2");
        owner.setPhone("123456");
        ownerRepository.update(owner);
        assertThat(ownerRepository.findById(id).get().getName(), is("test2"));
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var userTest = new User();
        userTest.setName("userTest");
        userTest.setEmail("userTest@test.com");
        userTest.setPassword("password");
        var user = userRepository.save(userTest).get();
        var owner = new Owner();
        owner.setName("test2");
        owner.setPhone("123456");
        owner.setUser(user);
        ownerRepository.save(owner);
        int id = owner.getId();
        ownerRepository.delete(id);
        assertThat(ownerRepository.findById(id), is(Optional.empty()));
    }

    @Test
    public void whenFindByIdThenGetOwner() {
        var userTest = new User();
        userTest.setName("userTest");
        userTest.setEmail("userTest@test.com");
        userTest.setPassword("password");
        var user = userRepository.save(userTest).get();
        var owner = new Owner();
        owner.setName("test2");
        owner.setPhone("123456");
        owner.setUser(user);
        ownerRepository.save(owner);
        int id = owner.getId();
        assertThat(ownerRepository.findById(id).get(), is(owner));
    }

    @Test
    public void whenFindAllThenGetList() {
        var userTest = new User();
        userTest.setName("userTest");
        userTest.setEmail("userTest@test.com");
        userTest.setPassword("password");
        var user = userRepository.save(userTest).get();
        var owner = new Owner();
        owner.setName("test");
        owner.setPhone("123456");
        owner.setUser(user);
        ownerRepository.save(owner);
        var userTest2 = new User();
        userTest2.setName("userTest2");
        userTest2.setEmail("userTest2@test.com");
        userTest2.setPassword("password2");
        var user2 = userRepository.save(userTest2).get();
        var owner2 = new Owner();
        owner2.setName("test2");
        owner2.setPhone("123456");
        owner2.setUser(user2);
        ownerRepository.save(owner2);
        assertThat(ownerRepository.findAll(), is(List.of(owner, owner2)));
    }
}