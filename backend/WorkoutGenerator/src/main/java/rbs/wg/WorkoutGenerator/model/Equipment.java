package rbs.wg.WorkoutGenerator.model;

public enum Equipment {
    
    NONE(1),
    BARBELL_WITH_PLATES(2),
    PULL_UP_BAR(3),
    DIP_BAR(4),
    DUMBBELLS_WITH_PLATES(5),
    KETTLEBELL(6),
    JUMP_ROPE(7);

    private int id;

    Equipment(int id) {
        this.id = id;
    }

    public static Equipment getType(int id) {

        for(Equipment equipment: Equipment.values()) {
            if(equipment.getId() == id) {
                return equipment;
            }
        }

        throw new IllegalArgumentException("No matching equipment type for id: " + id);
    }


    public int getId() {
        return id;
    }
}
