import java.util.Arrays;

public class DesertPlantFactory implements IPlantFactory {
    @Override
    public Plant newInstance(PlantType type, String commonName) {
        Plant.PlantBuilder builder = new Plant.PlantBuilder(type, Biome.DESERT, commonName);

        switch (type) {
            case TREE:
                builder.sunlight(Sunlight.FULL_SUN).waterNeed(WaterNeed.MEDIUM).evergreen(false)
                        .heightM(6.0).spreadM(7.0).toxic(false)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE, Phenophase.DORMANT));
                break;
            case FLOWER:
                builder.sunlight(Sunlight.FULL_SUN).waterNeed(WaterNeed.LOW).evergreen(false)
                        .heightM(0.3).spreadM(0.3)
                        .bloom(new Bloom("yellow", Season.SPRING))
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.BUD, Phenophase.FLOWER,
                                Phenophase.DORMANT));
                break;
            case SUCCULENT:
                builder.sunlight(Sunlight.FULL_SUN).waterNeed(WaterNeed.LOW).evergreen(true)
                        .heightM(0.5).spreadM(0.7).toxic(true)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE));
                break;
            case FERN:
                builder.sunlight(Sunlight.SHADE).waterNeed(WaterNeed.MEDIUM).evergreen(false)
                        .heightM(0.5).spreadM(0.6)
                        .phenology(Arrays.asList(Phenophase.SEEDLING, Phenophase.VEGETATIVE, Phenophase.DORMANT));
                break;
        }
        return builder.build();
    }
}