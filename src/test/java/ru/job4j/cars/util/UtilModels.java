package ru.job4j.cars.util;

import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

/**
 * Utility class for creating test models.
 */
public final class UtilModels {
    private static final HibernateEngineRepository ENGINE_REPOSITORY = new HibernateEngineRepository(UtilRepository.getCrudRepository());
    private static final HibernateBodyRepository BODY_REPOSITORY = new HibernateBodyRepository(UtilRepository.getCrudRepository());
    private static final HibernateBrandRepository BRAND_REPOSITORY = new HibernateBrandRepository(UtilRepository.getCrudRepository());
    private static final HibernateFuelRepository FUEL_REPOSITORY = new HibernateFuelRepository(UtilRepository.getCrudRepository());
    private static final HibernateUserRepository USER_REPOSITORY = new HibernateUserRepository(UtilRepository.getCrudRepository());
    private static final HibernateOwnerRepository OWNER_REPOSITORY = new HibernateOwnerRepository(UtilRepository.getCrudRepository());
    private static final HibernateCarRepository CAR_REPOSITORY = new HibernateCarRepository(UtilRepository.getCrudRepository());
    private static final HibernateFileRepository FILE_REPOSITORY = new HibernateFileRepository(UtilRepository.getCrudRepository());
    private static final HibernatePostRepository POST_REPOSITORY = new HibernatePostRepository(UtilRepository.getCrudRepository());

    private UtilModels() {

    }

    /**
     * Creates and saves a test Engine model.
     * @param name the name of the Engine
     * @return the created Engine
     */
    public static Engine getEngineTest(String name) {
        var engine = new Engine();
        engine.setName("engine");
        return ENGINE_REPOSITORY.save(engine).get();
    }

    /**
     * Creates and saves a test Body model.
     * @param name the name of the Body
     * @return the created Body
     */
    public static Body getBodyTest(String name) {
        var body = new Body();
        body.setName("body");
        return BODY_REPOSITORY.save(body).get();
    }

    /**
     * Creates and saves a test Brand model.
     * @param name the name of the Brand
     * @return the created Brand
     */
    public static Brand getBrandTest(String name) {
        var brand = new Brand();
        brand.setName("brand");
        return BRAND_REPOSITORY.save(brand).get();
    }

    /**
     * Creates and saves a test Fuel model.
     * @param name the name of the Fuel
     * @return the created Fuel
     */
    public static Fuel getFuelTest(String name) {
        var fuel = new Fuel();
        fuel.setName("fuel");
        return FUEL_REPOSITORY.save(fuel).get();
    }

//    /**
//     * Creates and saves a test Transmission model.
//     * @param name the name of the Transmission
//     * @return the created Transmission
//     */
//    public static Transmission getTransmissionTest(String name) {
//        var transmission = new Transmission();
//        transmission.setName("transmission");
//        return TRANSMISSION_REPOSITORY.save(transmission).get();
//    }

    /**
     * Creates a User model.
     * @param name     the name of the User
     * @param email    the email of the User
     * @param password the password of the User
     * @return the created User
     */
    public static User createUser(String name, String email, String password) {
        var user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    /**
     * Saves a User model to the repository.
     * @param user the User to save
     * @return the saved User
     */
    public static User getUserTest(User user) {
        return USER_REPOSITORY.save(user).get();
    }

    /**
     * Creates an Owner model.
     * @param name      the name of the Owner
     * @param phone     the phone number of the Owner
     * @param userTest  the User associated with the Owner
     * @return the created Owner
     */
    public static Owner createOwner(String name, String phone, User userTest) {
        var owner = new Owner();
        owner.setName(name);
        owner.setPhone(phone);
        owner.setUser(userTest);
        return owner;
    }

    /**
     * Saves an Owner model to the repository.
     * @param owner the Owner to save
     * @return the saved Owner
     */
    public static Owner getOwnerTest(Owner owner) {
        return OWNER_REPOSITORY.save(owner).get();
    }

    /**
     * Creates a Car model.
     * @param name             the name of the Car
     * @param produced         the year of production of the Car
     * @param engineTest       the Engine associated with the Car
     * @param bodyTest         the Body associated with the Car
     * @param brandTest        the Brand associated with the Car
     * @param fuelTest         the Fuel associated with the Car
     * @param transmissionTest the Transmission associated with the Car
     * @param ownerTest        the Owner associated with the Car
     * @return the created Car
     */
    public static Car createCar(String name, int produced, Engine engineTest, Body bodyTest, Brand brandTest, Fuel fuelTest, Transmission transmissionTest, Owner ownerTest) {
        var car = new Car();
        car.setName(name);
        car.setProduced(produced);
        car.setEngine(engineTest);
        car.setBody(bodyTest);
        car.setBrand(brandTest);
        car.setFuel(fuelTest);
        car.setTransmission(transmissionTest);
        car.setOwner(ownerTest);
        return car;
    }

    /**
     * Saves a Car model to the repository.
     * @param car the Car to save
     * @return the saved Car
     */
    public static Car getCarTest(Car car) {
        return CAR_REPOSITORY.save(car).get();
    }

    /**
     * Creates a Post model.
     * @param description the description of the Post
     * @param price       the price of the Post
     * @param carNew      indicates whether the car in the Post is new
     * @param location    the location of the Post
     * @param mileage     the mileage of the car in the Post
     * @param carTest     the Car associated with the Post
     * @param userTest    the User associated with the Post
     * @return the created Post
     */
    public static Post createPost(
            String description, int price, boolean carNew, String location, int mileage, Car carTest, User userTest
    ) {
        var post = new Post();
        post.setDescription(description);
        post.setPrice(price);
        post.setCarNew(carNew);
        post.setLocation(location);
        post.setMileage(mileage);
        post.setCar(carTest);
        post.setUser(userTest);
        return post;
    }

    /**
     * Saves a Post model to the repository.
     * @param post the Post to save
     * @return the saved Post
     */
    public static Post getPostTest(Post post) {
        return POST_REPOSITORY.save(post).get();
    }

    /**
     * Creates a File model.
     * @param path     the path of the File
     * @param name     the name of the File
     * @param postTest the Post associated with the File
     * @return the created File
     */
    public static File createFile(String path, String name, Post postTest) {
        var file = new File();
        file.setPath(path);
        file.setName(name);
        file.setPost(postTest);
        return file;
    }

    /**
     * Saves a File model to the repository.
     * @param file the File to save
     */
    public static void saveFile(File file) {
        FILE_REPOSITORY.save(file);
    }

}
