package model;

public enum Animal {

    Elephant(1, "elephant.png"), Lion(2, "lion.png"), Tigre(3, "tigre.png"), Panthere(4, "panther.png"), Chien(5,
            "chien.png"), Loup(6, "loup.png"), Chat(7, "chat.png"), Rat(8, "rat.png");

    private int rank;
    private String img;

    Animal(int r) {
        rank = r;
    }

    Animal(int r, String pimg) {
        rank = r;
        img = pimg;
    }

    public int getRank() {
        return rank;
    }

    public String getImg() {
        return img;
    }

}
