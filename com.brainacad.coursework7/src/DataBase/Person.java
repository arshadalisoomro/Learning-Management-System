package DataBase;

/**
 * Created by Зая on 18.02.2016.
 */
public class Person {
    private String firstName,lastName, login, password,ID;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return getFirstName()+" "+ getLastName();
    }

    public void setLogPass(String login,String password){
        this.login = login;
        this.password = password;
    }
}
