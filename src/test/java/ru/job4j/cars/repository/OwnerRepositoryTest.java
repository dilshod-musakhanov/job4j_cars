package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.util.UtilModels;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OwnerRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final HibernateOwnerRepository ownerRepository = new HibernateOwnerRepository(UtilRepository.getCrudRepository());

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
/**
    @Test
    public void whenSaveNewOwnerThenGetSameOwner() {
        var user = UtilModels.createUser("user", "userTest@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        ownerRepository.save(owner);
        int id = owner.getId();
        assertThat(ownerRepository.findById(id).get(), is(owner));
    }

    @Test
    public void whenUpdateOwnerThenGetUpdatedOwner() {
        var user = UtilModels.createUser("user", "userTest@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        ownerRepository.save(owner);
        int id = owner.getId();
        owner.setName("test2");
        ownerRepository.update(owner);
        assertThat(ownerRepository.findById(id).get().getName(), is("test2"));
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var user = UtilModels.createUser("user", "userTest@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        ownerRepository.save(owner);
        int id = owner.getId();
        ownerRepository.delete(id);
        assertThat(ownerRepository.findById(id), is(Optional.empty()));
    }

    @Test
    public void whenFindByIdThenGetOwner() {
        var user = UtilModels.createUser("user", "userTest@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        ownerRepository.save(owner);
        int id = owner.getId();
        assertThat(ownerRepository.findById(id).get(), is(owner));
    }

    @Test
    public void whenFindAllThenGetList() {
        var user = UtilModels.createUser("user", "userTest@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        ownerRepository.save(owner);
        var user2 = UtilModels.createUser("user2", "userTest2@test.com", "password2");
        var userTest2 = UtilModels.getUserTest(user2);
        var owner2 = UtilModels.createOwner("owner2", "1234562", userTest2);
        ownerRepository.save(owner2);
        assertThat(ownerRepository.findAll(), is(List.of(owner, owner2)));
    }
    **/
}