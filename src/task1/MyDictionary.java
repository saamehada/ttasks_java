package task1;

import java.util.*;

public class MyDictionary<K, V> {
    private Map<K, List<V>> dictionary;

    public MyDictionary() {
        dictionary = new HashMap<>();
    }

    // добавить пару "ключ-значение" в словарь
    public void put(K key, V value) {
        if (!dictionary.containsKey(key)) {
            dictionary.put(key, new ArrayList<>());
        }
        dictionary.get(key).add(value);
    }

    // получить список значений по ключу
    public List<V> get(K key) {
        return dictionary.getOrDefault(key, Collections.emptyList());
    }

    // получить список ключей для заданного объекта
    public List<K> getKeysByValue(V value) {
        List<K> keys = new ArrayList<>();
        for (Map.Entry<K, List<V>> entry : dictionary.entrySet()) {
            if (entry.getValue().contains(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    // удалить заданный объект для любых значений ключа
    public void removeValues(V value) {
        for (Map.Entry<K, List<V>> entry : dictionary.entrySet()) {
            entry.getValue().removeIf(v -> v.equals(value));
            if (entry.getValue().isEmpty()) {
                dictionary.remove(entry.getKey());
            }
        }
    }

    // удалить заданный объект для заданного ключа
    public void removeValue(K key, V value) {
        if (dictionary.containsKey(key)) {
            dictionary.get(key).removeIf(v -> v.equals(value));
            if (dictionary.get(key).isEmpty()) {
                dictionary.remove(key);
            }
        }
    }

    // вывести содержимое словаря на консоль
    public void printDictionary() {
        for (Map.Entry<K, List<V>> entry : dictionary.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Values: " + entry.getValue());
        }
    }

}
