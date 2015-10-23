package stepic.java_base_1.robot;


public class Main {

    public static void main(String[] args) {

    }


    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        RobotConnection connection = null;
        for (int i = 0; i < 3; i++) {
            try {
                connection = robotConnectionManager.getConnection();
                connection.moveRobotTo(toX, toY);
                break;
            } catch (RobotConnectionException ignore) {

            } finally {
                try {
                    connection.close();
                } catch (Throwable ignore) {

                }
            }
            if (i == 2) throw new RobotConnectionException("");
        }
    }
}
