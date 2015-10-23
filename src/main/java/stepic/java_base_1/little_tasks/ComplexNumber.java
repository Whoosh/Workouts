package stepic.java_base_1.little_tasks;

/**
 * Created by DX on 09.10.2015.
 */
public final class ComplexNumber {
    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object n){
        if(this==n) return true;
        if(n instanceof ComplexNumber){
            ComplexNumber o = (ComplexNumber) n;
            if(o.getRe() != this.getRe()) return false;
            if(o.getIm() != this.getIm()) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(re);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(im);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}