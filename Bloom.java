public class Bloom {
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