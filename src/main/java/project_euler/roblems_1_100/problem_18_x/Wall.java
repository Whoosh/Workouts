package project_euler.roblems_1_100.problem_18_x;

public class Wall {

    private int left;
    private int right;

    public Wall(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
