package project_euler.roblems_1_100.problem_54;

public class Card implements Comparable {

    int rank;
    int type;

    @Override
    public int compareTo(Object o) {
        return rank - ((Card) o).rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", type=" + type +
                '}';
    }
}
