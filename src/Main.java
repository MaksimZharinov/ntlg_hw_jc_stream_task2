import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        int young = (int) persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> army = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .filter(person -> person.getSex().toString().equals("MAN"))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        List<String> job = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getEducation().toString().equals("HIGHER"))
                .filter(person -> {
                    if (person.getSex().toString().equals("MAN")) {
                        return person.getAge() <= 65;
                    } else {
                        return person.getAge() <= 60;
                    }
                })
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

//        System.out.println(young);
//        System.out.println(army);
//        System.out.println(job);
    }
}
