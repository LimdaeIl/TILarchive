class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name); // 부모 클래스의 생성자 호출
        this.breed = breed;
    }
}

class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("망고", "말티즈");
        System.out.println("dog.breed = " + dog.breed);
        System.out.println("dog.name = " + dog.name);

        Animal animal = new Animal("보리");
        System.out.println("animal = " + animal.name);
    }
}