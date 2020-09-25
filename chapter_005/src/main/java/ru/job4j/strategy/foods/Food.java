package ru.job4j.strategy.foods;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food {

    private String name;
    private LocalDate createDate;
    private LocalDate expireDate;
    private Double price;
    private Double discount;

    public Food(String name, LocalDate createDate, LocalDate expireDate, Double price) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = 1.0;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }

        Food food = (Food) o;

        if (!Objects.equals(name, food.name)) {
            return false;
        }
        if (!Objects.equals(createDate, food.createDate)) {
            return false;
        }
        if (!Objects.equals(expireDate, food.expireDate)) {
            return false;
        }
        if (!Objects.equals(price, food.price)) {
            return false;
        }
        return Objects.equals(discount, food.discount);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expireDate=" + expireDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
