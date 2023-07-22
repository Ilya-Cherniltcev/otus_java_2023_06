package homework;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final Map<Customer, String> map;

    public CustomerService() {
        map = new HashMap<>();
    }

    protected Map<Customer, String> cloneMap(Map<Customer, String> map1) {
        Map<Customer, String> cloneMap = new HashMap<>();
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
        long minScore = 0L;
        Map.Entry<Customer, String> smallestMap = null;
        Map<Customer, String> mapTemp = cloneMap(map);
        for (Map.Entry<Customer, String> entry : mapTemp.entrySet()) {
            if (smallestMap == null) {
                smallestMap = entry;
                minScore = entry.getKey().getScores();
            }
            if (entry.getKey().getScores() < minScore) {
                minScore = entry.getKey().getScores();
                smallestMap = entry;
            }
        }
        return smallestMap;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        long middleScore = customer.getScores();
        Map<Customer, String> mapCopy = cloneMap(map);
        Map<Customer, String> tempMap = new HashMap<>();
        if (mapCopy != null) {
            for (Map.Entry<Customer, String> entry : mapCopy.entrySet()) {
                if (entry.getKey().getScores() > middleScore) {
                    tempMap.put(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
        if (tempMap.isEmpty()) return null;
        return
                //  create a copy of the new entry in the map
                Map.Entry.copyOf(tempMap.entrySet().stream().toList().get(0));
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}