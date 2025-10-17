public interface IPlantFactory {
    // 기본 newInstance 메소드
    Plant newInstance(PlantType type, String commonName);
}