import java.util.List;
import java.util.Objects;

public class Plant {
    // --- 속성 ---
    private final String commonName;
    private final PlantType type;
    private final Biome biome;
    private final Sunlight sunlight;
    private final WaterNeed waterNeed;
    private final boolean evergreen;
    private final double heightM;
    private final double spreadM;
    private final boolean toxic;
    private final Bloom bloom;
    private final List<Phenophase> phenology;

    // [핵심 1] 생성자는 private! 오직 PlantBuilder를 통해서만 호출 가능
    private Plant(PlantBuilder builder) {
        this.commonName = builder.commonName;
        this.type = builder.type;
        this.biome = builder.biome;
        this.sunlight = builder.sunlight;
        this.waterNeed = builder.waterNeed;
        this.evergreen = builder.evergreen;
        this.heightM = builder.heightM;
        this.spreadM = builder.spreadM;
        this.toxic = builder.toxic;
        this.bloom = builder.bloom;
        this.phenology = builder.phenology;
    }

    // --- Getter 메소드들 ---
    // (setter는 없어서 한번 만들어지면 값을 바꿀 수 없음)
    public String getCommonName() {
        return commonName;
    }

    public PlantType getType() {
        return type;
    }
    // ... 나머지 모든 필드에 대한 Getter ...

    @Override
    public String toString() {
        return "Plant [commonName=" + commonName + ", type=" + type + ", biome=" + biome +
                ", sunlight=" + sunlight + ", waterNeed=" + waterNeed + ", evergreen=" + evergreen +
                ", heightM=" + heightM + ", spreadM=" + spreadM + ", toxic=" + toxic +
                ", bloom=" + bloom + ", phenology=" + phenology + "]";
    }

    // --- 내부 Builder 클래스 ---
    public static class PlantBuilder {
        // [핵심 2] Plant의 속성과 동일한 필드를 가짐
        // 필수 속성
        private final PlantType type;
        private final Biome biome;
        private final String commonName;

        // 선택 속성 (기본값으로 초기화)
        private Sunlight sunlight = null;
        private WaterNeed waterNeed = null;
        private boolean evergreen = false;
        private double heightM = 0.0;
        private double spreadM = 0.0;
        private boolean toxic = false;
        private Bloom bloom = null;
        private List<Phenophase> phenology = null;

        // [핵심 3] Builder 생성자는 '필수' 속성만 받음
        public PlantBuilder(PlantType type, Biome biome, String commonName) {
            this.type = Objects.requireNonNull(type);
            this.biome = Objects.requireNonNull(biome);
            this.commonName = Objects.requireNonNull(commonName);
        }

        // [핵심 4] 선택 속성은 메소드 체이닝 방식으로 설정
        public PlantBuilder sunlight(Sunlight sunlight) {
            this.sunlight = sunlight;
            return this;
        }

        public PlantBuilder waterNeed(WaterNeed waterNeed) {
            this.waterNeed = waterNeed;
            return this;
        }

        public PlantBuilder evergreen(boolean evergreen) {
            this.evergreen = evergreen;
            return this;
        }

        public PlantBuilder heightM(double heightM) {
            this.heightM = heightM;
            return this;
        }

        public PlantBuilder spreadM(double spreadM) {
            this.spreadM = spreadM;
            return this;
        }

        public PlantBuilder toxic(boolean toxic) {
            this.toxic = toxic;
            return this;
        }

        public PlantBuilder bloom(Bloom bloom) {
            this.bloom = bloom;
            return this;
        }

        public PlantBuilder phenology(List<Phenophase> phenology) {
            this.phenology = phenology;
            return this;
        }

        // [핵심 5] build() 메소드에서 최종 객체를 생성하며 유효성 검증 수행
        public Plant build() {
            // --- 유효성 검증 로직 (PDF 9페이지) ---
            validate();

            return new Plant(this);
        }

        private void validate() {
            // 1) SUCCULENT는 물 필요량이 반드시 LOW 여야 함
            if (type == PlantType.SUCCULENT && waterNeed != WaterNeed.LOW) {
                throw new IllegalStateException("Validation Error: SUCCULENT must have a LOW water need.");
            }

            // 2) FERN(양치류)은 개화 정보가 있으면 안 됨
            if (type == PlantType.FERN && bloom != null) {
                throw new IllegalStateException("Validation Error: FERN cannot have bloom information.");
            }

            // 3) FLOWER는 개화 정보가 필수
            if (type == PlantType.FLOWER && bloom == null) {
                throw new IllegalStateException("Validation Error: FLOWER must have bloom information.");
            }

            // 4) 온대(temperate) + 낙엽(deciduous, evergreen=false)인데 phenology에 DORMANT가 없으면
            // 위반
            if (biome == Biome.TEMPERATE && !evergreen
                    && (phenology == null || !phenology.contains(Phenophase.DORMANT))) {
                throw new IllegalStateException(
                        "Validation Error: Temperate deciduous plants must have a DORMANT phenophase.");
            }
        }
    }
}