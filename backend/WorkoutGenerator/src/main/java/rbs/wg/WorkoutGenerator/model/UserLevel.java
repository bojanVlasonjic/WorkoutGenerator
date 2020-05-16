package rbs.wg.WorkoutGenerator.model;

public enum UserLevel {

    BEGINNER(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    private int id;

    UserLevel(int id) {
        this.id = id;
    }

    public static UserLevel getType(int id) {

        for(UserLevel level: UserLevel.values()) {
            if(level.getId() == id) {
                return level;
            }
        }

        throw new IllegalArgumentException("No matching user level for id: " + id);
    }

    public int getId() {
        return id;
    }
}
