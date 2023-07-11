package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.util.UtilModels;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class FileRepositoryTest {
    private static final SessionFactory SF = UtilRepository.getSessionFactory();
    private final HibernateFileRepository fileRepository = new HibernateFileRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = SF.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM File").executeUpdate();
            session.createQuery("DELETE FROM Post").executeUpdate();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.createQuery("DELETE FROM Owner").executeUpdate();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.createQuery("DELETE FROM Engine").executeUpdate();
            session.createQuery("DELETE FROM Body").executeUpdate();
            session.createQuery("DELETE FROM Brand").executeUpdate();
            session.createQuery("DELETE FROM Fuel").executeUpdate();
            session.createQuery("DELETE FROM Transmission").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @AfterAll
    public static void wipeTableAfterAll() {
        Session session = SF.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM File").executeUpdate();
            session.createQuery("DELETE FROM Post").executeUpdate();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.createQuery("DELETE FROM Owner").executeUpdate();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.createQuery("DELETE FROM Engine").executeUpdate();
            session.createQuery("DELETE FROM Body").executeUpdate();
            session.createQuery("DELETE FROM Brand").executeUpdate();
            session.createQuery("DELETE FROM Fuel").executeUpdate();
            session.createQuery("DELETE FROM Transmission").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenSaveNewFileThenGetSameFile() {
        var engineTest = UtilModels.getEngineTest("engine");
        var bodyTest = UtilModels.getBodyTest("body");
        var brandTest = UtilModels.getBrandTest("brand");
        var fuelTest = UtilModels.getFuelTest("fuel");
        var transmissionTest = UtilModels.getTransmissionTest("transmission");
        var user = UtilModels.createUser("user", "user@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        var ownerTest = UtilModels.getOwnerTest(owner);
        var car = UtilModels.createCar("car", 2023, engineTest, bodyTest, brandTest, fuelTest, transmissionTest, ownerTest);
        var carTest = UtilModels.getCarTest(car);
        var post = UtilModels.createPost("post", 1000, true, "Dubai", 100, carTest, userTest);
        var postTest = UtilModels.getPostTest(post);
        var file = UtilModels.createFile("path", "name", postTest);
        UtilModels.saveFile(file);
        int id  = file.getId();
        assertThat(fileRepository.findById(id).get()).isEqualTo(file);
    }

    @Test
    public void whenDeleteFileThenGetTrue() {
        var engineTest = UtilModels.getEngineTest("engine");
        var bodyTest = UtilModels.getBodyTest("body");
        var brandTest = UtilModels.getBrandTest("brand");
        var fuelTest = UtilModels.getFuelTest("fuel");
        var transmissionTest = UtilModels.getTransmissionTest("transmission");
        var user = UtilModels.createUser("user", "user@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        var ownerTest = UtilModels.getOwnerTest(owner);
        var car = UtilModels.createCar("car", 2023, engineTest, bodyTest, brandTest, fuelTest, transmissionTest, ownerTest);
        var carTest = UtilModels.getCarTest(car);
        var post = UtilModels.createPost("post", 1000, true, "Dubai", 100, carTest, userTest);
        var postTest = UtilModels.getPostTest(post);
        var file = UtilModels.createFile("path", "name", postTest);
        UtilModels.saveFile(file);
        int id  = file.getId();
        assertThat(fileRepository.delete(id)).isEqualTo(true);
    }

    @Test
    public void whenFindAllFilesThenGetSameList() {
        var engineTest = UtilModels.getEngineTest("engine");
        var bodyTest = UtilModels.getBodyTest("body");
        var brandTest = UtilModels.getBrandTest("brand");
        var fuelTest = UtilModels.getFuelTest("fuel");
        var transmissionTest = UtilModels.getTransmissionTest("transmission");
        var user = UtilModels.createUser("user", "user@test.com", "password");
        var userTest = UtilModels.getUserTest(user);
        var owner = UtilModels.createOwner("owner", "123456", userTest);
        var ownerTest = UtilModels.getOwnerTest(owner);
        var car = UtilModels.createCar("car", 2023, engineTest, bodyTest, brandTest, fuelTest, transmissionTest, ownerTest);
        var carTest = UtilModels.getCarTest(car);
        var post = UtilModels.createPost("post", 1000, true, "Dubai", 100, carTest, userTest);
        var postTest = UtilModels.getPostTest(post);
        var file = UtilModels.createFile("path", "name", postTest);
        UtilModels.saveFile(file);
        var engineTest2 = UtilModels.getEngineTest("engine2");
        var bodyTest2 = UtilModels.getBodyTest("body2");
        var brandTest2 = UtilModels.getBrandTest("brand2");
        var fuelTest2 = UtilModels.getFuelTest("fuel2");
        var transmissionTest2 = UtilModels.getTransmissionTest("transmission2");
        var user2 = UtilModels.createUser("user2", "user2@test.com", "password2");
        var userTest2 = UtilModels.getUserTest(user2);
        var owner2 = UtilModels.createOwner("owner2", "1234562", userTest);
        var ownerTest2 = UtilModels.getOwnerTest(owner2);
        var car2 = UtilModels.createCar("car2", 2023, engineTest2, bodyTest2, brandTest2, fuelTest2, transmissionTest2, ownerTest2);
        var carTest2 = UtilModels.getCarTest(car2);
        var post2 = UtilModels.createPost("post2", 1000, true, "Dubai", 100, carTest2, userTest2);
        var postTest2 = UtilModels.getPostTest(post2);
        var file2 = UtilModels.createFile("path2", "name2", postTest2);
        UtilModels.saveFile(file2);
        assertThat(fileRepository.findAll()).isEqualTo(List.of(file, file2));
    }

}
