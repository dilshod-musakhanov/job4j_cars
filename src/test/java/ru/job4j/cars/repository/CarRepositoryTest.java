package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CarRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final CarRepository carRepository = new CarRepository(UtilRepository.getCrudRepository());
    private final EngineRepository engineRepository = new EngineRepository(UtilRepository.getCrudRepository());
    private final OwnerRepository ownerRepository = new OwnerRepository(UtilRepository.getCrudRepository());
    private final UserRepository userRepository = new UserRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.createQuery("DELETE FROM Engine").executeUpdate();
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
        var engine = new Engine();
        engine.setName("engine");
        var engineTest = engineRepository.save(engine).get();
        var user = new User();
        user.setLogin("user");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("owner");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setEngine(engineTest);
        car.setOwner(ownerTest);
        assertThat(carRepository.save(car).get(), is(car));
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var engine = new Engine();
        engine.setName("engine");
        var engineTest = engineRepository.save(engine).get();
        var user = new User();
        user.setLogin("user");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("owner");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setEngine(engineTest);
        car.setOwner(ownerTest);
        carRepository.save(car);
        int id = car.getId();
        carRepository.delete(id);
        assertThat(carRepository.findById(id), is(Optional.empty()));
    }

    @Test
    public void whenFindByIdThenGetCar() {
        var engine = new Engine();
        engine.setName("engine");
        var engineTest = engineRepository.save(engine).get();
        var user = new User();
        user.setLogin("user");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("owner");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setEngine(engineTest);
        car.setOwner(ownerTest);
        carRepository.save(car);
        int id = car.getId();
        assertThat(carRepository.findById(id).get(), is(car));
    }

    @Test
    public void whenUpdateCarThenGetSameCar() {
        var engine = new Engine();
        engine.setName("engine");
        var engineTest = engineRepository.save(engine).get();
        var user = new User();
        user.setLogin("user");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("owner");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setEngine(engineTest);
        car.setOwner(ownerTest);
        carRepository.save(car);
        int id = car.getId();
        car.setName("test2");
        carRepository.update(car);
        assertThat(carRepository.findById(id).get().getName(), is("test2"));
    }

    @Test
    public void whenFindAllThenGetList() {
        var engine = new Engine();
        engine.setName("engine");
        var engineTest = engineRepository.save(engine).get();
        var user = new User();
        user.setLogin("user");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("owner");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setEngine(engineTest);
        car.setOwner(ownerTest);
        carRepository.save(car);
        var engine2 = new Engine();
        engine2.setName("engine2");
        var engineTest2 = engineRepository.save(engine2).get();
        var user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("password2");
        var userTest2 = userRepository.save(user2).get();
        var owner2 = new Owner();
        owner2.setName("owner2");
        owner2.setUser(userTest2);
        var ownerTest2 = ownerRepository.save(owner2).get();
        var car2 = new Car();
        car2.setName("test2");
        car2.setEngine(engineTest2);
        car2.setOwner(ownerTest2);
        carRepository.save(car2);
        assertThat(carRepository.findAll(), is(List.of(car, car2)));
    }
}