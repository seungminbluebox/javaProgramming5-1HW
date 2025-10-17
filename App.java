public class App {
    public static void main(String[] args) {
        // Tropical (열대) 샘플
        IPlantFactory tropical = new TropicalPlantFactory();
        Plant tropTree = tropical.newInstance(PlantType.TREE, "Rainforest Mahogany");
        Plant tropFlower = tropical.newInstance(PlantType.FLOWER, "Heliconia");

        // Desert (사막) 샘플
        IPlantFactory desert = new DesertPlantFactory();
        Plant desSucc = desert.newInstance(PlantType.SUCCULENT, "Saguaro Cactus");
        Plant desFlower = desert.newInstance(PlantType.FLOWER, "Desert Marigold");

        // Temperate (온대) 샘플
        IPlantFactory temperate = new TemperatePlantFactory();
        Plant tempTree = temperate.newInstance(PlantType.TREE, "Blue Oak");
        Plant tempFern = temperate.newInstance(PlantType.FERN, "Bracken Fern");

        System.out.println("== Tropical ==");
        System.out.println(tropTree);
        System.out.println(tropFlower);
        System.out.println("\n== Desert ==");
        System.out.println(desSucc);
        System.out.println(desFlower);
        System.out.println("\n== Temperate ==");
        System.out.println(tempTree);
        System.out.println(tempFern);

        // 유효성 검증 테스트 (일부러 오류 발생시키기)
        try {
            System.out.println("\n== Validation Test (Failure expected) ==");
            // 규칙: SUCCULENT의 waterNeed는 LOW여야 함
            // 하지만 Tropical SUCCULENT는 기본값이 LOW이므로 규칙 통과.
            // 다른 팩토리를 통해 의도적으로 규칙 위반 객체를 만들어 테스트 필요.
            Plant invalidSucculent = new Plant.PlantBuilder(PlantType.SUCCULENT, Biome.DESERT, "Invalid Plant")
                    .waterNeed(WaterNeed.HIGH) // 규칙 위반
                    .build();
            System.out.println(invalidSucculent);
        } catch (IllegalStateException e) {
            System.err.println("Caught expected exception: " + e.getMessage());
        }
    }
}