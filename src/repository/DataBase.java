package repository;

import java.io.*;
import java.util.ArrayList;

public class DataBase {
    private ObjectOutputStream oos;

    public DataBase(String file) throws IOException {
        oos = new ObjectOutputStream(new FileOutputStream(file, false));
    }

    public void addData(Object obj) {
        try {
            oos.writeObject(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> ArrayList<T> readData(String file) {
        ArrayList<T> list = new ArrayList<>();
        Object obj;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));) {
            while (true) {
                list.add((T) ois.readObject());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void oosClose() {
        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
