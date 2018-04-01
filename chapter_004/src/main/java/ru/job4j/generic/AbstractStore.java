package ru.job4j.generic;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$.
 * @since 0.1.
 */
public abstract class AbstractStore implements Store {
    private SimpleArray<Base> array = new SimpleArray<>();
    private boolean result;

    /**
     * Method add model.
     *
     * @param model - Base extended.
     */
    @Override
    public void add(Base model) {
        array.add(model);
    }

    /**
     * Method replace model.
     *
     * @param id - id model.
     * @param model - Base extended.
     * @return - result condition.
     */
    @Override
    public boolean replace(String id, Base model) {
        array.set(Integer.parseInt(id), model);
        result = array.get(Integer.parseInt(id)) == model;
        return result;
    }

    /**
     * Method delete model.
     *
     * @param id - id model.
     * @return - result condition.
     */
    @Override
    public boolean delete(String id) {
        while (array.hasNext()) {
            Base test = array.next();
            if (test.getId().equals(id)) {
                array.delete(Integer.parseInt(id));
                result = true;
                break;
            } else {
                result = false;
            }
        }

        return result;
    }

    /**
     * Method find model for id.
     *
     * @param id - id model
     * @return - model.
     */
    @Override
    public Base findById(String id) {
        Base result = null;
        while (array.hasNext()) {
            result = (Base) array.next();
            if (result.getId().equals(id)) {
                break;
            } else {
                result = null;
            }
        }
        return result;
    }
}
