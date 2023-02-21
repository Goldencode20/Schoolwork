public class Counter {
    class CountOutOfBoundsException extends Exception {}

    private int maxCount; // the maximum count assuming bits represents an unsigned int
    private int count; // the current count
    private int numBits; // the number of bits to use in the counter, typically a power of two
    private int[] bits; // little endian binary representation of count

    /*
     *  Little vs. Big Endian
     *  =====================
     *  The notion of "Endianess" refers to how a binary number is read. Binary is always read left to right, but we
     *  refer to the right end as being the little or big end (i.e. the bits of smaller or larger end).
     *
     *  Little Endian:    5 -> 0000 0000 0000 0101
     *
     *  Big Endian:       5 -> 1010 0000 0000 0000
     *
     *  You will almost always see binary with little endian ordering; big endian is mainly used in networking
     *  protocols (https://en.wikipedia.org/wiki/Endianness).
     */

    public Counter(int numBits) {
        this.count = 0;
        this.numBits = numBits;
        this.maxCount = (int) Math.pow(2, numBits) - 1;
        this.bits = new int[numBits];
    }

    /**
     * Increments the value of this.count and its binary representation (remember this.bits represents a number in binary).
     *
     * E.x. if this.bits = [ 0, 1, 1, 0, 1 ], this.bits should become [ 0, 1, 1, 1, 0 ] after a call to this function and the
     * returned integer should be 2.
     *
     * @return The number of "checks" (iterations of a loop) performed during the operation
     */
    public int incrementEfficient() throws CountOutOfBoundsException{
        int checks = 0;
        if (this.count + 1 > this.maxCount){
            throw new CountOutOfBoundsException();
        }
        this.count++;
        for (int i = this.numBits - 1; i >= 0; i--) {
            if(this.bits[i] == 0){
                this.bits[i] = 1;
                i = -100;
            } else {
                this.bits[i] = 0;
                //this.bits[i-1] = 1; I don't know why it doesn't need this but it works?
            }
            checks++;
        }
        return checks; // TOD
    }

    /**
     * Increments the value of this.count and its binary representation (remember this.bits represents a number in binary).
     *
     * E.x. if this.bits = [ 0, 1, 1, 0, 1 ], this.bits should become [ 0, 1, 1, 1, 0 ] after a call to this function and the
     * returned integer should be 2.
     *
     * @return The number of "checks" (iterations of a loop) performed during the operation
     */
    public int incrementSlow() throws CountOutOfBoundsException {
        if (this.count+1 > this.maxCount) throw new CountOutOfBoundsException();

        this.count++;

        // Assume the next three lines cost 0
        String format = "%"+this.numBits+"s";
        String countStr = Integer.toBinaryString(this.count);
        String newCountBinary = String.format(format, countStr).replaceAll(" ", "0");

        int cost = 0;
        // For-loop reversed to stay consistent with the little endian property of this.bits
        for (int i = newCountBinary.length()-1; i >= 0; i--) {
            cost++;
            this.bits[i] = Integer.parseInt(newCountBinary.charAt(i)+"");
        }

        return cost;
    }

    /**
     * toString() methods are a great addition to any class you make (remember from C212?)! They make it super simple to
     * make a visual representation of your objects like so:
     *
     * System.out.println(*your BinaryCounter obj*);
     *
     * @return A string representation of a BinaryCounter object.
     */
    @Override
    public String toString() {
        String res = "[ ";

        for (int i = 0; i < this.bits.length; i++)
            res += this.bits[i] + ", ";

        return res.substring(0, res.length() - 2) + " ]";
    }
}
