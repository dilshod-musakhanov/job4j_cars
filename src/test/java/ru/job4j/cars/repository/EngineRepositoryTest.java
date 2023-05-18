package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class EngineRepositoryTest {
    private final SessionFactory sf = UtilRepository.getSessionFactory();
    private final EngineRepository engineRepository = new EngineRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Engine").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenSaveNewEngineThenGetSameEngine() {
        var engine = new Engine();
        engine.setName("test1");
        engineRepository.save(engine);
        int id = engine.getId();
        var result = engineRepository.findById(id);
        assertThat(result.get().getName(), is(engine.getName()));
    }

    @Test
    public void whenUpdateEngineThenGetUpdatedEngine() {
        var engine = new Engine();
        engine.setName("test1");
        engineRepository.save(engine);
        engine.setName("test2");
        int id = engine.getId();
        engineRepository.update(engine);
        var result = engineRepository.findById(id);
        assertThat(result.get().getName(), is(engine.getName()));
    }

    @Test
    public void whenDeleteEngineByIdThenGetEmptyOptional() {
        var engine = new Engine();
        engine.setName("test1");
        engineRepository.save(engine);
        int id = engine.getId();
        engineRepository.delete(id);
        assertThat(engineRepository.findById(id), is(Optional.empty()));
    }

    @Test
    public void whenFindByIdThenGetSameEngine() {
        var engine = new Engine();
        engine.setName("test1");
        engineRepository.save(engine);
        int id = engine.getId();
        var result = engineRepository.findById(id);
        assertThat(engine, is(result.get()));
    }

    @Test
    public void whenFindAllThenGetListOfEngines() {
        var engine1 = new Engine();
        engine1.setName("test1");
        var engine2 = new Engine();
        engine2.setName("test2");
        engineRepository.save(engine1);
        engineRepository.save(engine2);
        assertThat(engineRepository.findAll(), is(List.of(engine1, engine2)));
    }
}