
package My_classes;


public class TacGia {
    
    private int id;
    private String name;
    private String birthday;
    private String nationality;

    public TacGia() {
    }

    public TacGia(int id, String name, String birthday, String nationality) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    
}
