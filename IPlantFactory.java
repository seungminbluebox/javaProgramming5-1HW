// 모든 식물 공장이 따라야 할 인터페이스 
public interface IPlantFactory {
    Plant newInstance(PlantType type, String commonName);
}