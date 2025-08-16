import java.io.*;

public class BasicReflectionSerializationDemo {
    public static void main(String[] args) {
        BasicReflectionSerialization obj1 = BasicReflectionSerialization.getInstance();
        obj1.setValue(12);

        Util.saveToFile(obj1,"demoFile.bin");

        BasicReflectionSerialization obj2 = Util.getFromFile("demoFile.bin");

    }
}

class Util{
    public static void saveToFile(BasicReflectionSerialization basicReflectionSerialization,String fileName)
    {
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        )
        {
            out.writeObject(basicReflectionSerialization);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static BasicReflectionSerialization getFromFile(String fileName)
    {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            return (BasicReflectionSerialization) objectInputStream.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        } catch (IOException e) {
            throw new RuntimeException("IO error while reading file: " + fileName, e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found during deserialization", e);
        }


    }
}

class BasicReflectionSerialization implements Serializable{

    int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private BasicReflectionSerialization(){

    }

    public static BasicReflectionSerialization getInstance(){
        return INSTANCE;
    }

    protected Object readResolve(){
        return INSTANCE;
    }

    private static final BasicReflectionSerialization INSTANCE = new BasicReflectionSerialization();

}


