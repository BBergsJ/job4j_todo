package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.Users;

import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SF = new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();
    private static final Logger LOG = LoggerFactory.getLogger(HbmStore.class.getName());

    private HbmStore() {
    }

    private static final class Lazy {
        private static final Store INST = new HbmStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Item createItem(Item item, List<String> categoriesId) {
        return this.tx(session -> {
            categoriesId.forEach(id -> item.addCategory(session.find(Category.class, Integer.parseInt(id))));
            session.save(item);
            return item;
        });
    }

    @Override
    public Users createUser(Users user) {
        this.tx(session -> session.save(user));
        return user;
    }

    @Override
    public void update(int id) {
        this.tx(session -> session.createQuery("update Item i "
                + "set i.done = case i.done when true then false else true end"
                + " where i.id = :iId").setParameter("iId", id).executeUpdate()
        );
    }

    @Override
    public void delete(Integer id) {
        this.tx(session -> {
                Item item = session.load(Item.class, id);
                session.delete(item);
                return item;
            }
        );
    }

    @Override
    public List<Item> findAll() {
        return this.tx(session -> session.createQuery(
                "select distinct c from Item c join fetch c.categories").list());
    }

    @Override
    public List<Item> findByFlagFalse() {
        return this.tx(session -> session.createQuery(
                "select distinct c from Item c join fetch c.categories where c.done = :false")
                    .setParameter("false", false).list()
        );
    }

    @Override
    public Item findById(int id) {
        return (Item) this.tx(session -> session.createQuery(
                "select distinct i from Item i join fetch i.categories where i.id = :id")
                .setParameter("id", id).uniqueResult());
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(REGISTRY);
    }

    @Override
    public Users findUserByEmail(String email) {
        return (Users) this.tx(session -> session.createQuery("from ru.job4j.todo.model.Users where email = :email")
                .setParameter("email", email).uniqueResult());
    }

    @Override
    public List<Category> findAllCategories() {
        return this.tx(session -> session.createQuery("select c from Category c", Category.class).list());
    }

    @Override
    public Category findCatById(int id) {
        return this.tx(session -> session.get(Category.class, id));
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = SF.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
