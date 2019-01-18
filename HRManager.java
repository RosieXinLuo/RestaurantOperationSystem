package ResApp;

/**
 * A human resource manager that tracks all the cooks and servers. Design Pattern: Singleton.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HRManager {

    public static final String filePath = "/Users/Desktop/group_0467/phase2/RestaurantApp/src/ResApp/staffs.ser";
    private static HashMap<Integer, Staff> staffs;

    public HRManager() {
        staffs = new HashMap<Integer, Staff>();
        // Reads serializable objects from file.
        // Populates the record list using stored data, if it exists.
        File file = new File(filePath);
        if (file.exists() && file.length() != 0) {
            try {
                readFromFile(filePath);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Deserialize the map from given path.
     *
     * @param path
     *            the path of the serializable file
     * @throws ClassNotFoundException
     */
    protected static void readFromFile(String path) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input;
            input = new ObjectInputStream(buffer);
            staffs = (HashMap<Integer, Staff>) input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current data to the serializable file. This function also can
     * be used to update file content .
     *
     * @throws IOException
     */
    protected static void saveToFile(String path)  {

        OutputStream file = null;
        try {
            file = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = null;
        try {
            output = new ObjectOutputStream(buffer);
            output.writeObject(staffs);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCook (Cook c) throws IOException {
        staffs.put(c.getId(), (Staff) c);
        saveToFile(filePath);
        LoggerHelper.makeALog("Cook "+c.getId()+" is constructed");
    }

    public void addServer (Server s) throws IOException {
        staffs.put(s.getId(), (Staff) s);
        saveToFile(filePath);
        LoggerHelper.makeALog("Server "+s.getId()+" is constructed");
    }

    public void removeCook (Cook c) throws IOException {
        staffs.remove(c.getId());
        saveToFile(filePath);
        LoggerHelper.makeALog("Cook "+c.getId()+" is removed");
    }

    public void removeServer (Server s) throws IOException {
        staffs.remove(s.getId());
        saveToFile(filePath);
        LoggerHelper.makeALog("Server "+s.getId()+" is removed");
    }

    public HashMap<Integer, Server> getServers(){
        HashMap<Integer, Server> servers = new HashMap<>();
        for (Integer id: staffs.keySet()) {
            if (staffs.get(id).getServerOrCook() == 0) {
                servers.put(id, (Server) staffs.get(id));
            }
        }
        return servers;
    }

    public HashMap<Integer, Cook> getCooks(){
        HashMap<Integer, Cook> cooks = new HashMap<>();
        for (Integer id: staffs.keySet()) {
            if (staffs.get(id).getServerOrCook() == 1) {
                cooks.put(id, (Cook) staffs.get(id));
            }
        }
        return cooks;
    }

    public static HashMap<Integer, Staff> getStaffs() {
        return staffs;
    }
}
