package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.util.UtilModels;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


public class CarRepositoryTest {
    private static final SessionFactory SF = UtilRepository.getSessionFactory();
    private final HibernateCarRepository carRepository = new HibernateCarRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = SF.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.createQuery("DELETE FROM Engine").executeUpdate();
            session.createQuery("DELETE FROM Body").executeUpdate();
            session.createQuery("DELETE FROM Brand").executeUpdate();
            session.createQuery("DELETE FROM Fuel").executeUpdate();
            session.createQuery("DELETE FROM Transmission").executeUpdate();
            session.createQuery("DELETE FROM Owner").executeUpdate();
            session.createQuery("DELETE FROM User").executeUpdate();
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
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.createQuery("DELETE FROM Engine").executeUpdate();
            session.createQuery("DELETE FROM Body").executeUpdate();
            session.createQuery("DELETE FROM Brand").executeUpdate();
            session.createQuery("DELETE FROM Fuel").executeUpdate();
            session.createQuery("DELETE FROM Transmission").executeUpdate();
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
    public void whenSaveCarThenGetSameCar() {
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
        assertThat(carRepository.save(car).get()).isEqualTo(car);
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
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
        carRepository.save(car);
        int id = car.getId();
        carRepository.delete(id);
        assertThat(carRepository.findById(id)).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindByIdThenGetCar() {
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
        carRepository.save(car);
        int id = car.getId();
        assertThat(carRepository.findById(id).get()).isEqualTo(car);
    }

    @Test
    public void whenUpdateCarThenGetSameCar() {
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
        carRepository.save(car);
        int id = car.getId();
        car.setName("test2");
        carRepository.update(car);
        assertThat(carRepository.findById(id).get().getName()).isEqualTo("test2");
    }

    @Test
    public void whenFindAllThenGetList() {
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
        carRepository.save(car);
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
        carRepository.save(car2);
        assertThat(carRepository.findAll()).isEqualTo(List.of(car, car2));
    }
}