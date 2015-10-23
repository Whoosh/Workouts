package stepic.java_base_1.little_tasks;

public class AsciiCharSequence implements CharSequence {

    private byte[] sequence;

    public AsciiCharSequence(byte[] sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        StringBuilder ok = new StringBuilder(sequence.length);
        for (byte aSequence : sequence) ok.append((char) aSequence);
        return ok.toString();
    }

    @Override
    public int length() {
        return sequence.length;
    }

    @Override
    public char charAt(int index) {
        return (char) sequence[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        byte[] newSequence = new byte[end - start];
        System.arraycopy(sequence, start, newSequence, 0, end - start);
        return new AsciiCharSequence(newSequence);
    }

    public static void main(String[] args) {
        String s = new AsciiCharSequence(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).subSequence(3, 6).toString();
        System.out.println(s);
    }

}
