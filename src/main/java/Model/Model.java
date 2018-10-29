package Model;

public class Model {

    private static Model singleton = null;
    public Database database;
    public User user;

    private Model() {

        createDataBase();
        //before_hagasha();
        createTables();

    }

    public static Model getInstance() {
        if (singleton == null)
            singleton = new Model();
        return singleton;

    }

    private void createDataBase() {

        database = new Database("jdbc:sqlite:Vaction4U.sqlite");
    }

    private void createTables() {
        database.createTables();//TODO PARAMETER TABLE NAME
    }

    //functions:

    public boolean insert(String table_name, Object[] data) {
        return database.insert(table_name, data);
    }

    public boolean delete(String table_name, String id) {
        return database.delete(table_name, id);
    }

    public String[] read(String table_name, String id) {
        User showedUser = database.read(table_name, id);

        if (showedUser == null)
            return null;

        String[] details = new String[6];
        details[0] = showedUser.getUsername();
        details[1] = showedUser.getPassword();
        details[2] = showedUser.getBDay();
        details[3] = showedUser.getFName();
        details[4] = showedUser.getLName();
        details[5] = showedUser.getCity();

        return details;
    }

    public boolean update(String table_name, Object[] data, String id) {
        return database.update(table_name, data, id);
    }


    public boolean confirm(String table_name, String user, String password) {
        this.user = database.read(table_name, user);
        if (user == null)
            return false;
        else return this.user.getPassword().equals(password);
    }

    public String[] readConnectedUser() {
        String [] details = read("Users", this.user.getUsername());
        return details;
    }


    public void before_hagasha (){
        database.dropTable("Users");
    }


}

