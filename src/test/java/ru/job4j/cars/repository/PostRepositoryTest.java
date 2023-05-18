package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.*;
import ru.job4j.cars.util.UtilRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final EngineRepository engineRepository = new EngineRepository(UtilRepository.getCrudRepository());
    private final OwnerRepository ownerRepository = new OwnerRepository(UtilRepository.getCrudRepository());
    private final CarRepository carRepository = new CarRepository(UtilRepository.getCrudRepository());
    private final UserRepository userRepository = new UserRepository(UtilRepository.getCrudRepository());
    private final PhotoRepository photoRepository = new PhotoRepository(UtilRepository.getCrudRepository());
    private final PostRepository postRepository = new PostRepository(UtilRepository.getCrudRepository());


    @BeforeEach
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Photo").executeUpdate();
            session.createQuery("DELETE FROM Post").executeUpdate();
            session.createQuery("DELETE FROM Car").executeUpdate();
            session.createQuery("DELETE FROM Owner").executeUpdate();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.createQuery("DELETE FROM Engine").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenSaveNewPostThenGetSamePost() {
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
        int id = post.getId();
        assertThat(postRepository.findById(id).get(), is(post));
    }

    @Test
    public void whenUpdateThenGetUpdatedPost() {
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
        post.setDescription("test1");
        postRepository.update(post);
        int id = post.getId();
        assertThat(postRepository.findById(id).get().getDescription(), is("test1"));
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
        int id = post.getId();
        postRepository.delete(id);
        assertThat(postRepository.findById(id), is(Optional.empty()));
    }

    @Test
    public void whenFindByIdThenGetPost() {
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
        int id = post.getId();
        var result = postRepository.findById(id).get();
        assertThat(result, is(post));
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
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
        var carTest2 = carRepository.save(car2).get();
        var post2 = new Post();
        post2.setDescription("test2");
        post2.setCreated(LocalDateTime.now());
        post2.setCar(carTest2);
        post2.setUser(userTest2);
        postRepository.save(post2);
        var result = postRepository.findAll();
        assertThat(result, is(List.of(post, post2)));
    }

    @Test
    public void whenFindByCreatedPreviousDayThenGetPost() {
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now().minusDays(1));
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
        assertThat(postRepository.findByCreatedPreviousDay(), is(List.of(post)));
    }

    @Test
    public void whenFindByCarNameThenGetPost() {
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
        car.setName("testCar");
        car.setEngine(engineTest);
        car.setOwner(ownerTest);
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
        var result = postRepository.findByCarName("testCar");
        assertThat(result, is(List.of(post)));
    }

    @Test
    public void whenFindWithPhotosThenGetList() {
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
        var carTest = carRepository.save(car).get();
        var post = new Post();
        post.setDescription("test");
        post.setCreated(LocalDateTime.now());
        post.setCar(carTest);
        post.setUser(userTest);
        postRepository.save(post);
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
        var carTest2 = carRepository.save(car2).get();
        var photo = new Photo();
        photo.setName("testPhoto");
        photo.setPath("testPath");
        photoRepository.save(photo);
        var post2 = new Post();
        post2.setDescription("test2");
        post2.setCreated(LocalDateTime.now());
        post2.setCar(carTest2);
        post2.setUser(userTest2);
        post2.setPhotos(List.of(photo));
        postRepository.save(post2);
        var result = postRepository.findWithPhotos();
        assertThat(result, is(List.of(post2)));
    }
}