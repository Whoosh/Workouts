package stepic.java_base_1.serialization;

import java.io.*;

/**
 * Created by DX on 18.10.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int count = 2;
        Animal[] animals = new Animal[count];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeInt(count);
        for (int i = 0; i < count; i++) {
            animals[i] = new Animal("Dat - " + i);
            out.writeObject(animals[i]);
        }
        Animal[] result = deserializeAnimalArray(byteArrayOutputStream.toByteArray());
        for (Animal animal : result) {
            System.out.println(animal);
        }
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] animals;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            animals = new Animal[objectInputStream.readInt()];
            for (int i = 0; i < animals.length; animals[i++] = (Animal) objectInputStream.readObject());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return animals;
    }
}
