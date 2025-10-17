import java.util.Arrays;

// 열대 식물을 만드는 공장
public class TropicalPlantFactory implements IPlantFactory {

    @Override
    public Plant newInstance(PlantType type, String commonName) {
        // 1. 열대(TROPICAL) 서식지에 맞는 필수값으로 빌더 생성
        Plant.PlantBuilder builder = new Plant.PlantBuilder(type, Biome.TROPICAL, commonName);

        // 2. 식물 종류(type)에 따라 json 데이터 기반으로 기본 속성 설정
        switch (type) {
            case TREE:
                builder.sunlight(Sunlight.FULL_SUN)
                        .waterNeed(WaterNeed.HIGH)
                        .evergreen(true)
                        .heightM(15.0)
                        .spreadM(8.0)
                        .toxic(false)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE, Phenophase.FRUIT));
                break;
            case FLOWER:
                builder.sunlight(Sunlight.PART_SHADE)
                        .waterNeed(WaterNeed.MEDIUM)
                        .evergreen(false)
                        .heightM(0.6)
                        .spreadM(0.4)
                        .bloom(new Bloom("scarlet", Season.SUMMER))
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE, Phenophase.BUD,
                                Phenophase.FLOWER, Phenophase.FRUIT));
                break;
            case SUCCULENT:
                builder.sunlight(Sunlight.FULL_SUN)
                        .waterNeed(WaterNeed.LOW)
                        .evergreen(true)
                        .heightM(0.4)
                        .spreadM(0.5)
                        .toxic(false)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE));
                break;
            case FERN:
                builder.sunlight(Sunlight.PART_SHADE)
                        .waterNeed(WaterNeed.HIGH)
                        .evergreen(true)
                        .heightM(1.0)
                        .spreadM(1.2)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE));
                break;
        }

        // 3. 설정이 완료된 빌더로 최종 객체 생성 및 반환
        return builder.build();
    }
}