public class Bloom {// 식물의 개화 정보를 담는 클래스
    private String color;
    private Season peakSeason;

    public Bloom(String color, Season peakSeason) {
        this.color = color;
        this.peakSeason = peakSeason;
    }

    public String getColor() {
        return color;
    }

    public Season getPeakSeason() {
        return peakSeason;
    }

    @Override
    public String toString() {
        return "Bloom [color=" + color + ", peakSeason=" + peakSeason + "]";
    }
}