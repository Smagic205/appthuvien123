
package My_classes;


public class TacGia {
     private int id;
    private String tenTacGia;
    private String ngaySinh;
    private String quocTich;

    // Constructors
    public TacGia() {}
    public TacGia(int id, String tenTacGia, String ngaySinh, String quocTich) {
        this.id = id;
        this.tenTacGia = tenTacGia;
        this.ngaySinh = ngaySinh;
        this.quocTich = quocTich;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTenTacGia() { return tenTacGia; }
    public String getNgaySinh() { return ngaySinh; }
    public String getQuocTich() { return quocTich; }

    public void setId(int id) { this.id = id; }
    public void setTenTacGia(String tenTacGia) { this.tenTacGia = tenTacGia; }
    public void setNgaySinh(String ngaySinh) { this.ngaySinh = ngaySinh; }
    public void setQuocTich(String quocTich) { this.quocTich = quocTich; }
}
