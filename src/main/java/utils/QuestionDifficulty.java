package utils;

public enum QuestionDifficulty {

    EASY(5),
    MEDIUM(10),
    HARD(20),
    UNSPECIFIED(0);

    private final int rewardPoints;

    QuestionDifficulty(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }
}
