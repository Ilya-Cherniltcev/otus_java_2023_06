package homework;


import java.util.ArrayList;
import java.util.List;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    List<Customer> customers;
    int counter;

    public CustomerReverseOrder() {
        customers = new ArrayList<>();
        counter = 0;
    }

    public void add(Customer customer) {
        customers.add(customer);
        counter += 1;
    }

    public Customer take() {
        if (counter > 0) {
            counter -= 1;
        }
        return customers.get(counter);
    }
}
