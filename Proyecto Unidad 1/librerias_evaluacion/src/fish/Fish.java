package fish;

public class Fish {
    private String fishType;
    private int toBirth;

    public Fish(String fishType, int toBirth) {
        this.fishType = fishType;
        this.toBirth = toBirth;
    }

    public String getFishType() {
        return fishType;
    }
    public void setFishType(String fishType) {
        this.fishType = fishType;
    }

    public int getToBirth() {
        return toBirth;
    }
    public void setToBirth(int toBirth) {
        this.toBirth = toBirth;
    }
    public int totalFish() {
        int total = (getToBirth()*2)-10;
        return total;
    }
}