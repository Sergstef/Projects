public class User {
    private int id;
    private String name;
    private float averageMark;
    private boolean isFree;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageMark=" + averageMark +
                ", isFree=" + isFree +
                '}';
    }

    public User(int id, String name, float averageMark, boolean isFree) {
        this.id = id;
        this.name = name;
        this.averageMark = averageMark;
        this.isFree = isFree;
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

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
