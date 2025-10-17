public class App {
    public static void main(String[] args) {
        // --- 팩토리 생성 ---
        // 1. 열대 식물 공장 생성
        IPlantFactory tropical = new TropicalPlantFactory();
        // 2. 사막 식물 공장 생성
        IPlantFactory desert = new DesertPlantFactory();
        // 3. 온대 식물 공장 생성
        IPlantFactory temperate = new TemperatePlantFactory();

        // --- 식물 객체 생성 ---
        // 열대 식물들 생성
        Plant tropTree = tropical.newInstance(PlantType.TREE, "Rainforest Mahogany");
        Plant tropFlower = tropical.newInstance(PlantType.FLOWER, "Heliconia");

        // 사막 식물들 생성
        Plant desSucc = desert.newInstance(PlantType.SUCCULENT, "Saguaro Cactus");
        Plant desFlower = desert.newInstance(PlantType.FLOWER, "Desert Marigold");

        // 온대 식물들 생성
        Plant tempTree = temperate.newInstance(PlantType.TREE, "Blue Oak");
        Plant tempFern = temperate.newInstance(PlantType.FERN, "Bracken Fern");

        // --- 결과 출력 ---
        System.out.println("== Tropical ==");
        System.out.println(tropTree);
        System.out.println(tropFlower);
        System.out.println("\n== Desert ==");
        System.out.println(desSucc);
        System.out.println(desFlower);
        System.out.println("\n== Temperate ==");
        System.out.println(tempTree);
        System.out.println(tempFern);

        // --- 유효성 검증 테스트 ---
        try {
            System.out.println("\n== Validation Test (Failure expected) ==");
            // 1. 일부러 규칙에 어긋나는 객체 생성을 시도
            // 규칙: SUCCULENT의 waterNeed는 LOW여야 함
            Plant invalidSucculent = new Plant.PlantBuilder(PlantType.SUCCULENT, Biome.DESERT, "Invalid Plant")
                    .waterNeed(WaterNeed.HIGH) // 규칙 위반! waterNeed를 HIGH로 설정
                    .build();
            System.out.println(invalidSucculent);
        } catch (IllegalStateException e) {
            // 2. 예상대로 예외가 발생했는지 확인하고 메시지 출력
            System.err.println("Caught expected exception: " + e.getMessage());
        }

        // yourcode
        // 새로 추가한 유효성 검증 테스트
        try {
            System.out.println("\n== Your Validation Test (Failure expected) ==");
            // 규칙: 독성이 있는 식물은 열매를 맺을 수 없음
            // 열대 나무(TROPICAL TREE)는 원래 독성이 없고(toxic=false) 열매를 맺지만,
            // 일부러 독성(toxic=true)을 주입하여 규칙 위반을 테스트
            Plant baseTree = tropical.newInstance(PlantType.TREE, "Rainforest Mahogany"); // 기본 열대 나무

            new Plant.PlantBuilder(baseTree.getType(), baseTree.getBiome(), "Toxic Fruit Tree")
                    .phenology(baseTree.getPhenology()) // 기본 생장주기 (FRUIT 포함)
                    .toxic(true) // 규칙 위반 독성을 true로 설정
                    .build();
        } catch (IllegalStateException e) {
            System.err.println("Caught your expected exception: " + e.getMessage());
        }
    }
}