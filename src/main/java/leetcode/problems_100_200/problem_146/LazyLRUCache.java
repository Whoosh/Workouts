package leetcode.problems_100_200.problem_146;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by whoosh on 2/14/16.
 */
public class LazyLRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LazyLRUCache(int capacity) {
        super(capacity, 1, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.containsKey(key) ? super.get(key) : -1;
    }

    public void set(int key, int value) {
       put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return this.size() > capacity;
    }
}