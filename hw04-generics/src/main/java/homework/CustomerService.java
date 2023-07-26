package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final TreeMap<Customer, String> map;

    public CustomerService() {
        map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }

    protected TreeMap<Customer, String> cloneMap(TreeMap<Customer, String> map1) {
        TreeMap<Customer, String> cloneMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
        try {
            for (Map.Entry<Customer, String> entry : map1.entrySet()) {
                cloneMap.put(entry.getKey().clone(), entry.getValue());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cloneMap;
    }

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        TreeMap<Customer, String> mapTemp = cloneMap(map);
        return mapTemp.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        TreeMap<Customer, String> mapTemp = cloneMap(map);
        return mapTemp.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}