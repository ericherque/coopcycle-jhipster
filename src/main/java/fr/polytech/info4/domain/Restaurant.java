package fr.polytech.info4.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Restaurant.
 */
@Entity
@Table(name = "restaurant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "i_d_restaurant", nullable = false, unique = true)
    private Integer iDRestaurant;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "position", nullable = false)
    private String position;

    @Min(value = 0)
    @Max(value = 5)
    @Column(name = "rating")
    private Integer rating;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "restaurant_delivery",
               joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "delivery_id", referencedColumnName = "id"))
    private Set<Delivery> deliveries = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "restaurant_order",
               joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private Set<Order> orders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getiDRestaurant() {
        return iDRestaurant;
    }

    public Restaurant iDRestaurant(Integer iDRestaurant) {
        this.iDRestaurant = iDRestaurant;
        return this;
    }

    public void setiDRestaurant(Integer iDRestaurant) {
        this.iDRestaurant = iDRestaurant;
    }

    public String getName() {
        return name;
    }

    public Restaurant name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public Restaurant position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getRating() {
        return rating;
    }

    public Restaurant rating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }

    public Restaurant deliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
        return this;
    }

    public Restaurant addDelivery(Delivery delivery) {
        this.deliveries.add(delivery);
        delivery.getRestaurants().add(this);
        return this;
    }

    public Restaurant removeDelivery(Delivery delivery) {
        this.deliveries.remove(delivery);
        delivery.getRestaurants().remove(this);
        return this;
    }

    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Restaurant orders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Restaurant addOrder(Order order) {
        this.orders.add(order);
        order.getRestaurants().add(this);
        return this;
    }

    public Restaurant removeOrder(Order order) {
        this.orders.remove(order);
        order.getRestaurants().remove(this);
        return this;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Restaurant)) {
            return false;
        }
        return id != null && id.equals(((Restaurant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Restaurant{" +
            "id=" + getId() +
            ", iDRestaurant=" + getiDRestaurant() +
            ", name='" + getName() + "'" +
            ", position='" + getPosition() + "'" +
            ", rating=" + getRating() +
            "}";
    }
}
