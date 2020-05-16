package rbs.wg.WorkoutGenerator.model;

public enum ExerciseType {
    STRENGTH(1),
    CONDITIONING(2),
    COMBO(3);

    private int id;

    ExerciseType(int id) {
        this.id = id;
    }

    public static ExerciseType getType(int id) {

        for(ExerciseType type: ExerciseType.values()) {
            if(type.getId() == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("No matching exercise type for id: " + id);

    }

    public int getId() {
        return id;
    }

}
