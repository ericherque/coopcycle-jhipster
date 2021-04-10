package fr.polytech.info4.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Delivery.
 */
@Entity
@Table(name = "delivery")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "i_ddelivery", nullable = false, unique = true)
    private Integer iDdelivery;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "position", nullable = false)
    private String position;

    @OneToMany(mappedBy = "delivery")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "deliveries", allowSetters = true)
    private Client client;

    @ManyToMany(mappedBy = "deliveries")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Restaurant> restaurants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDdelivery() {
        return iDdelivery;
    }

    public Delivery iDdelivery(Integer iDdelivery) {
        this.iDdelivery = iDdelivery;
        return this;
    }

    public void setiDdelivery(Integer iDdelivery) {
        this.iDdelivery = iDdelivery;
    }

    public String getName() {
        return name;
    }

    public Delivery name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public Delivery lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public Delivery position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Delivery orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Delivery addOrder(Order order) {
        this.orders.add(order);
        order.setDelivery(this);
        return this;
    }

    public Delivery removeOrder(Order order) {
        this.orders.remove(order);
        order.setDelivery(null);
        return this;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Client getClient() {
        return client;
    }

    public Delivery client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Delivery restaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
        return this;
    }

    public Delivery addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
        restaurant.getDeliveries().add(this);
        return this;
    }

    public Delivery removeRestaurant(Restaurant restaurant) {
        this.restaurants.remove(restaurant);
        restaurant.getDeliveries().remove(this);
        return this;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Delivery)) {
            return false;
        }
        return id != null && id.equals(((Delivery) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Delivery{" +
            "id=" + getId() +
            ", iDdelivery=" + getiDdelivery() +
            ", name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", position='" + getPosition() + "'" +
            "}";
    }
}
