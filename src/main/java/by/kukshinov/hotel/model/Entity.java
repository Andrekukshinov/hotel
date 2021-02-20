package by.kukshinov.hotel.model;

/**
 * Special interface-marker(contract) that must be implemented by all entity classes of application
 */
public interface Entity {
    /**
     * All entities must have id and this contract is designed to return this id
     *
     * @return id of entity
     */
    Long getId();
}
