import java.util.Arrays;

public class TemperatePlantFactory implements IPlantFactory {
    @Override
    public Plant newInstance(PlantType type, String commonName) {
        Plant.PlantBuilder builder = new Plant.PlantBuilder(type, Biome.TEMPERATE, commonName);

        switch (type) {
            case TREE:
                builder.sunlight(Sunlight.PART_SHADE).waterNeed(WaterNeed.MEDIUM).evergreen(false)
                        .heightM(12.0).spreadM(10.0)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE, Phenophase.FRUIT,
                                Phenophase.DORMANT));
                break;
            case FLOWER:
                builder.sunlight(Sunlight.FULL_SUN).waterNeed(WaterNeed.MEDIUM).evergreen(false)
                        .heightM(0.5).spreadM(0.4)
                        .bloom(new Bloom("blue", Season.SPRING))
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.BUD, Phenophase.FLOWER,
                                Phenophase.FRUIT, Phenophase.DORMANT));
                break;
            case SUCCULENT:
                builder.sunlight(Sunlight.FULL_SUN).waterNeed(WaterNeed.LOW).evergreen(true)
                        .heightM(0.3).spreadM(0.4)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE, Phenophase.DORMANT));
                break;
            case FERN:
                builder.sunlight(Sunlight.SHADE).waterNeed(WaterNeed.HIGH).evergreen(false)
                        .heightM(0.8).spreadM(1.0)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE, Phenophase.DORMANT));
                break;
        }
        return builder.build();
    }
}