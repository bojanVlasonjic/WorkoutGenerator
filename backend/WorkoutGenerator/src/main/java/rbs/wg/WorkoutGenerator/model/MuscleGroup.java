package rbs.wg.WorkoutGenerator.model;

public enum MuscleGroup {
    SHOULDERS(1),
    TRICEPS(2),
    BICEPS(3),
    CHEST(4),
    BACK(5),
    CORE(6),
    QUADS(7),
    HAMSTRINGS(8),
    GLUTEUS(9),
    CALVES(10);

    private int id;

    MuscleGroup(int id) {
        this.id = id;
    }

    public static MuscleGroup getType(int id) {

        for(MuscleGroup group: MuscleGroup.values()) {
            if(group.getId() == id) {
                return group;
            }
        }

        throw new IllegalArgumentException("No matching muscle group type for id: " + id);

    }


    public int getId() {
        return id;
    }
}
