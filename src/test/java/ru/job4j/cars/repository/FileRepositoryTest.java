package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;
import ru.job4j.cars.util.UtilRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final FileRepository fileRepository = new FileRepository(UtilRepository.getCrudRepository());
    private final EngineRepository engineRepository = new EngineRepository(UtilRepository.getCrudRepository());
    private final BodyRepository bodyRepository = new BodyRepository(UtilRepository.getCrudRepository());
    private final BrandRepository brandRepository = new BrandRepository(UtilRepository.getCrudRepository());
    private final FuelRepository fuelRepository = new FuelRepository(UtilRepository.getCrudRepository());
    private final TransmissionRepository transmissionRepository  = new TransmissionRepository(UtilRepository.getCrudRepository());
    private final UserRepository userRepository = new UserRepository(UtilRepository.getCrudRepository());
    private final OwnerRepository ownerRepository = new OwnerRepository(UtilRepository.getCrudRepository());
    private final CarRepository carRepository = new CarRepository(UtilRepository.getCrudRepository());
    private final PostRepository postRepository = new PostRepository(UtilRepository.getCrudRepository());

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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setPrice(10000);
        post.setCarNew(true);
        post.setLocation("Dubai");
        post.setMileage(100);
        post.setCar(carTest);
        post.setUser(userTest);
        var testPost = postRepository.save(post).get();
        var file = new File();
        file.setPath("path");
        file.setName("file");
        file.setPost(testPost);
        fileRepository.save(file);
        int id  = file.getId();
        assertThat(fileRepository.findById(id).get(), is(file));
    }

    @Test
    public void whenDeleteFileThenGetTrue() {
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setPrice(10000);
        post.setCarNew(true);
        post.setLocation("Dubai");
        post.setMileage(100);
        post.setCar(carTest);
        post.setUser(userTest);
        var testPost = postRepository.save(post).get();
        var file = new File();
        file.setPath("path");
        file.setName("file");
        file.setPost(testPost);
        fileRepository.save(file);
        int id  = file.getId();
        assertThat(fileRepository.delete(id), is(true));
    }

    @Test
    public void whenFindAllFilesThenGetSameList() {
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setPrice(10000);
        post.setCarNew(true);
        post.setLocation("Dubai");
        post.setMileage(100);
        post.setCar(carTest);
        post.setUser(userTest);
        var testPost = postRepository.save(post).get();
        var file = new File();
        file.setPath("path");
        file.setName("file");
        file.setPost(testPost);
        fileRepository.save(file);
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
        var carTest2 = carRepository.save(car2).get();
        var post2 = new Post();
        post2.setDescription("test2");
        post2.setCreated(LocalDateTime.now().minusDays(1));
        post2.setPrice(10000);
        post2.setCarNew(true);
        post2.setLocation("Dubai");
        post2.setMileage(100);
        post2.setCar(carTest2);
        post2.setUser(userTest2);
        var testPost2 = postRepository.save(post2).get();
        var file2 = new File();
        file2.setPath("path2");
        file2.setName("file2");
        file2.setPost(testPost2);
        fileRepository.save(file2);
        assertThat(fileRepository.findAll(), is(List.of(file, file2)));
    }

}
