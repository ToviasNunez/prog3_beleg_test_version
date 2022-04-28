package logc;

import mediaDB.Uploader;

/**
 * User that create the item
 */
public class UploaderImpl implements Uploader {
    private String name;

    /**
     * create a user with a name identification
     * @param name from user
     */
    public UploaderImpl(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * default user
     */
    public UploaderImpl() {
        name = "MUSTERMAN";
    }


    /**
     * @return the name of the user
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return all the information form the user
     */
    @Override
    public String toString() {
        return "UploaderImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
