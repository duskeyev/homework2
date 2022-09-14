



import java.util.*;
import java.util.stream.Collectors;


public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };
        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                    Key:Amelia
                    Value:4
                    Key: Emily
                    Value:1
                    Key: Harry
                    Value:3
                    Key: Jack
                    Value:1
         */


        Arrays.sort(RAW_DATA, Comparator.comparing(Person::getId));


        Map<String, List<Person>> DATA = Arrays.stream(RAW_DATA).distinct().collect(
                Collectors.groupingBy(Person::getName));
        for(Map.Entry<String, List<Person>> item : DATA.entrySet()){

            System.out.println("Key: " + item.getKey());
            System.out.println("Value: " + item.getValue().toArray().length);

        }
        System.out.println("**************************************************");

        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */

        int[] arr = new int[]{3, 4, 2, 7};
            Arrays.sort(arr);
            int first = 0;
            int last = arr.length - 1;
            int[] result = new int[2];
            while (first < last) {
                int s = arr[first] + arr[last];
                if (s == 10) {
                    result[0]=arr[first];
                    result[1]=arr[last];
                    System.out.println(Arrays.toString(result));
                    break;
                } else {
                    if (s < 10) first++;
                    else last--;
                }
            }

        System.out.println("**************************************************");





        /*
        Task3
            Реализовать функцию нечеткого поиска
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */



        System.out.println(fuzzySearch("cwheeel","cartwheel"));
     }

    static boolean fuzzySearch (String string1, String string2 ) {
        if (string1==null){
            System.out.println("Value of parameter is Null");
            return false;
        }
        if (string2==null){
            System.out.println("Value of parameter is Null");
            return false;
        }
        char[] chararray1 = string1.toCharArray();
        char[] chararray2 = string2.toCharArray();
        char[] chararray3 = new char[chararray1.length];
        int index=0;
        int chararray3Index=0;
        for (int i=0; i<chararray1.length;i++){

            for (int j = 0; j < chararray2.length; j++) {

                if (chararray1[i] == chararray2[j]) {
                    if (index>j){return false;}
                    index=j;
                    //System.out.println(chararray1[i] + " -" +i +"-" + j); //вывод индексов массивов
                    chararray3[chararray3Index] = chararray2[j];
                    chararray3Index++;
                    chararray2[j]=0;
                    break;

                }

            }

        }
        for (int i=0; i< chararray1.length; i++){
            if (chararray1[i]!=chararray3[i]){
                return false;
            }

        }
        return true;
    }


}


