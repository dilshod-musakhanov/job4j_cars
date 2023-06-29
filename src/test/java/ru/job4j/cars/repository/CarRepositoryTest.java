package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CarRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final HibCarRepository carRepository = new HibCarRepository(UtilRepository.getCrudRepository());
    private final HibEngineRepository engineRepository = new HibEngineRepository(UtilRepository.getCrudRepository());
    private final HibBodyRepository bodyRepository = new HibBodyRepository(UtilRepository.getCrudRepository());
    private final HibBrandRepository brandRepository = new HibBrandRepository(UtilRepository.getCrudRepository());
    private final HibFuelRepository fuelRepository = new HibFuelRepository(UtilRepository.getCrudRepository());
    private final HibTransmissionRepository transmissionRepository  = new HibTransmissionRepository(UtilRepository.getCrudRepository());
    private final HibOwnerRepository ownerRepository = new HibOwnerRepository(UtilRepository.getCrudRepository());
    private final HibUserRepository userRepository = new HibUserRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = sf.openSession();
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
        var engine = new Engine();
        engine.setName("engine");
        var engineTest = engineRepository.save(engine).get();
        var body = new Body();
        body.setName("body");
        var bodyTest = bodyRepository.save(body).get();
        var brand = new Brand();
        brand.setName("brand");
        var brandTest = brandRepository.save(brand).get();
        var fuel = new Fuel();
        fuel.setName("fuel");
        var fuelTest = fuelRepository.save(fuel).get();
        var transmission = new Transmission();
        transmission.setName("transmission");
        var transmissionTest = transmissionRepository.save(transmission).get();
        var user = new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("test");
        owner.setPhone("123456");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setProduced(2023);
        car.setEngine(engineTest);
        car.setBody(bodyTest);
        car.setBrand(brandTest);
        car.setFuel(fuelTest);
        car.setTransmission(transmissionTest);
        car.setOwner(ownerTest);
        assertThat(carRepository.save(car).get(), is(car));
    }

    @Test
    public void whenDeleteThenGetEmptyOptional() {
        var engine = new Engine();
        engine.setName("engine");
        var engineTest = engineRepository.save(engine).get();
        var body = new Body();
        body.setName("body");
        var bodyTest = bodyRepository.save(body).get();
        var brand = new Brand();
        brand.setName("brand");
        var brandTest = brandRepository.save(brand).get();
        var fuel = new Fuel();
        fuel.setName("fuel");
        var fuelTest = fuelRepository.save(fuel).get();
        var transmission = new Transmission();
        transmission.setName("transmission");
        var transmissionTest = transmissionRepository.save(transmission).get();
        var user = new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("test");
        owner.setPhone("123456");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setProduced(2023);
        car.setEngine(engineTest);
        car.setBody(bodyTest);
        car.setBrand(brandTest);
        car.setFuel(fuelTest);
        car.setTransmission(transmissionTest);
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
        var body = new Body();
        body.setName("body");
        var bodyTest = bodyRepository.save(body).get();
        var brand = new Brand();
        brand.setName("brand");
        var brandTest = brandRepository.save(brand).get();
        var fuel = new Fuel();
        fuel.setName("fuel");
        var fuelTest = fuelRepository.save(fuel).get();
        var transmission = new Transmission();
        transmission.setName("transmission");
        var transmissionTest = transmissionRepository.save(transmission).get();
        var user = new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("test");
        owner.setPhone("123456");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setProduced(2023);
        car.setEngine(engineTest);
        car.setBody(bodyTest);
        car.setBrand(brandTest);
        car.setFuel(fuelTest);
        car.setTransmission(transmissionTest);
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
        var body = new Body();
        body.setName("body");
        var bodyTest = bodyRepository.save(body).get();
        var brand = new Brand();
        brand.setName("brand");
        var brandTest = brandRepository.save(brand).get();
        var fuel = new Fuel();
        fuel.setName("fuel");
        var fuelTest = fuelRepository.save(fuel).get();
        var transmission = new Transmission();
        transmission.setName("transmission");
        var transmissionTest = transmissionRepository.save(transmission).get();
        var user = new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("test");
        owner.setPhone("123456");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setProduced(2023);
        car.setEngine(engineTest);
        car.setBody(bodyTest);
        car.setBrand(brandTest);
        car.setFuel(fuelTest);
        car.setTransmission(transmissionTest);
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
        var body = new Body();
        body.setName("body");
        var bodyTest = bodyRepository.save(body).get();
        var brand = new Brand();
        brand.setName("brand");
        var brandTest = brandRepository.save(brand).get();
        var fuel = new Fuel();
        fuel.setName("fuel");
        var fuelTest = fuelRepository.save(fuel).get();
        var transmission = new Transmission();
        transmission.setName("transmission");
        var transmissionTest = transmissionRepository.save(transmission).get();
        var user = new User();
        user.setName("userTest");
        user.setEmail("userTest@test.com");
        user.setPassword("password");
        var userTest = userRepository.save(user).get();
        var owner = new Owner();
        owner.setName("test");
        owner.setPhone("123456");
        owner.setUser(userTest);
        var ownerTest = ownerRepository.save(owner).get();
        var car = new Car();
        car.setName("test");
        car.setProduced(2023);
        car.setEngine(engineTest);
        car.setBody(bodyTest);
        car.setBrand(brandTest);
        car.setFuel(fuelTest);
        car.setTransmission(transmissionTest);
        car.setOwner(ownerTest);
        carRepository.save(car);
        var engine2 = new Engine();
        engine2.setName("engine2");
        var engineTest2 = engineRepository.save(engine2).get();
        var body2 = new Body();
        body2.setName("body2");
        var bodyTest2 = bodyRepository.save(body2).get();
        var brand2 = new Brand();
        brand2.setName("brand2");
        var brandTest2 = brandRepository.save(brand2).get();
        var fuel2 = new Fuel();
        fuel2.setName("fuel2");
        var fuelTest2 = fuelRepository.save(fuel2).get();
        var transmission2 = new Transmission();
        transmission2.setName("transmission2");
        var transmissionTest2 = transmissionRepository.save(transmission2).get();
        var user2 = new User();
        user2.setName("userTest2");
        user2.setEmail("userTest2@test.com");
        user2.setPassword("password2");
        var userTest2 = userRepository.save(user2).get();
        var owner2 = new Owner();
        owner2.setName("test2");
        owner2.setPhone("123456");
        owner2.setUser(userTest2);
        var ownerTest2 = ownerRepository.save(owner2).get();
        var car2 = new Car();
        car2.setName("test2");
        car2.setProduced(2023);
        car2.setEngine(engineTest2);
        car2.setBody(bodyTest2);
        car2.setBrand(brandTest2);
        car2.setFuel(fuelTest2);
        car2.setTransmission(transmissionTest2);
        car2.setOwner(ownerTest2);
        carRepository.save(car2);
        assertThat(carRepository.findAll(), is(List.of(car, car2)));
    }
}