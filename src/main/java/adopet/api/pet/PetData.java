package adopet.api.pet;

public record PetData(
    Long id,
    String name,
    int age,
    String size,
    String temperament,
    boolean adopted,
    String imageURL,
    Long shelterId) {

    public PetData(Pet pet) {
        this(
            pet.getId(),
            pet.getName(),
            pet.getAge(),
            pet.getSize(),
            pet.getTemperament(),
            pet.isAdopted(),
            pet.getImageURL(),
            pet.getShelter().getId()
        );
    }

}
