package random_tasks;


/**
 * Created by whoosh on 2/16/16.
 */
// simply example for friend
public class CVSortLogicExample {

    private static String[][] matrix = new String[][]{
            {"F", "B", "C", "D", "A", "T"},
            {"B", "B", "B", "B", "B", "B"},
            {"C", "C", "C", "C", "C", "C",},
            {"D", "D", "D", "D", "D", "D",},
    };

    public static void main(String[] args) {
        String nameOfColumnWhatWeShouldToSort = new User().getNameOfColumn();
        System.out.println("До");
        printMatrix(matrix);
        for (int i = 0; i < matrix[0].length; i++) {
            if (nameOfColumnWhatWeShouldToSort.equals(matrix[0][i])) {
                sortMatrix(matrix, i);
                break;
            }
        }
        System.out.println("После");
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            System.out.print(matrix[0][i] + " ");
        }
        for (int i = 1; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }

    private static void sortMatrix(String[][] matrix, int index) {
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][index].compareTo(matrix[j][index]) < 0) {
                    swap(matrix, index, i, j);
                }
            }
        }
    }

    private static void swap(String[][] matrix, int index, int i, int j) {
        String b = matrix[i][index];
        matrix[i][index] = matrix[j][index];
        matrix[j][index] = b;
    }


    private static class User {
        public String getNameOfColumn() {
            return "A";
        }
    }
}
