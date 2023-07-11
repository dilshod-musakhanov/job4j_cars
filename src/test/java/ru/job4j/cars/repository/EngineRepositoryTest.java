package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.util.UtilRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class EngineRepositoryTest {
    private static final SessionFactory SF = UtilRepository.getSessionFactory();
    private final HibernateEngineRepository engineRepository = new HibernateEngineRepository(UtilRepository.getCrudRepository());

    @BeforeEach
    public void wipeTable() {
        Session session = SF.openSession();
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

    @AfterAll
    public static void wipeTableAfterAll() {
        Session session = SF.openSession();
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
        assertThat(result.get().getName()).isEqualTo(engine.getName());
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
        assertThat(result.get().getName()).isEqualTo(engine.getName());
    }

    @Test
    public void whenDeleteEngineByIdThenGetEmptyOptional() {
        var engine = new Engine();
        engine.setName("test1");
        engineRepository.save(engine);
        int id = engine.getId();
        engineRepository.delete(id);
        assertThat(engineRepository.findById(id)).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindByIdThenGetSameEngine() {
        var engine = new Engine();
        engine.setName("test1");
        engineRepository.save(engine);
        int id = engine.getId();
        var result = engineRepository.findById(id);
        assertThat(engine).isEqualTo(result.get());
    }

    @Test
    public void whenFindAllThenGetListOfSameEngines() {
        var engine1 = new Engine();
        engine1.setName("test1");
        var engine2 = new Engine();
        engine2.setName("test2");
        engineRepository.save(engine1);
        engineRepository.save(engine2);
        List<Engine> result = engineRepository.findAll();
        List<Engine> expected = List.of(engine1, engine2);
        assertThat(result).isEqualTo(expected);
    }

}