import com.google.gson.Gson;

public class Main {

    //Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код, использующий эти зависимости.
    //Пример решения:
    //1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
    //2. Добавьте зависимости org.apache.commons:commons-lang3:3.14.0 и com.google.code.gson:gson:2.10.1.
    //3. Создайте класс Person с полями firstName, lastName и age.
    //4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
    //5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.

    public static void main(String[] args) {
        System.out.println("hw from seminar one");

        Person personForSerialization = new Person("Ivan", "Petrov", 24);
        System.out.println(personForSerialization);

        Gson gson = new Gson();

        String gsonPerson = gson.toJson(personForSerialization);
        System.out.println(gsonPerson);

        Person personForDeserialization = gson.fromJson(gsonPerson, Person.class);
        System.out.println(personForDeserialization);

        System.out.println(personForSerialization.equals(personForDeserialization));

    }
}
