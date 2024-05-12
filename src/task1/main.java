package task1;

public class main {
    public static void main(String[] args) {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        dictionary.put("Key1", 1);
        dictionary.put("Key1", 2);
        dictionary.put("Key1", 3);

        dictionary.put("Key2", 2);
        dictionary.put("Key2", 3);
        dictionary.put("Key2", 4);

        dictionary.put("Key3", 5);

        System.out.println("Содержимое словаря:");
        dictionary.printDictionary();

        System.out.println("\nСписок ключей для значения 2: " + dictionary.getKeysByValue(2));

        System.out.println("\nУдаление объекта для любых значений ключа: " );
        dictionary.removeValues(2);
        dictionary.printDictionary();

        System.out.println();

        System.out.println("Удаление объекта для заданного ключа: ");
        dictionary.removeValue("Key1", 3);
        dictionary.printDictionary();
    }
}
