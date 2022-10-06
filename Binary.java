/**
 * A class representing a binary number.
 * 
 * @author  Jacob Knox
 * @version 0.0.1
 * @since   2022-10-06
 */

public class Binary {



    /* VARIABLES */



    /**
     * The bit sequence of the Binary instance.
     */
    public String bits;
    /**
     * Boolean value representing whether or no the Binary instance is signed.
     */
    public boolean signed;
    /**
     * A String containing all valid digits of a binary number.
     */
    private String validDigits = "01. ";



    /* CONSTRUCTORS */



    /**
     * Constructor that takes in the bit sequence of the binary number and assumes it is an unsigned binary number.
     * 
     * @param bitSequence The bit sequence of the Binary instance.
     * @throws Exception Exception thrown if the bit sequence is found to contain invalid digits.
     */
    public Binary(String bitSequence) throws Exception{
        // Calls the two parameter constructor, assuming it is unsigned
        this(bitSequence, false);
    }

    /**
     * Constructor that takes in the bit sequence of the binary number and whether or not it represents a signed binary number.
     * @param bitSequence The bit sequence of the Binary instance.
     * @param isSigned Whether or not the Binary instance is signed.
     * @throws Exception Exception thrown if the bit sequence is found to contain invalid digits.
     */
    public Binary(String bitSequence, boolean isSigned) throws Exception{
        // Calls the three parameter constructor, assuming the formatting should not be kept
        this(bitSequence, isSigned, false);
    }

    /**
     * Constructor that takes in the bit sequence of the binary number, whether or not it represents a signed binary number, and whether or not to keep the formatting of the inputted bit sequence.
     * @param bitSequence The bit sequence of the Binary instance.
     * @param isSigned Whether or not the Binary instance is signed.
     * @param keepFormatting Whether or not to keep the formatting/spacing in the inputted bit sequence.
     * @throws Exception Exception thrown if the bit sequence is found to contain invalid digits.
     */
    public Binary(String bitSequence, boolean isSigned, boolean keepFormatting) throws Exception{
        // If the user specifies to keep the formatting
        if(keepFormatting){
            // Set the sequence without removing spaces
            this.setBitSequence(bitSequence);
        }
        // Otherwise, set the sequence with spaces removed
        else{
            this.setBitSequence(bitSequence.replaceAll(" ", ""));
        }
        // Set the signed variable
        this.signed = isSigned;
        // Check if the sequence is valid, and if it is not, throw an exception
        if(!this.isValid()){
            throw new Exception("Attempting to create Binary object with invalid bit sequence.");
        }
    }



    /* GETTERS */



    /**
     * Getter for the bit sequence.
     * @return A String representing the bit sequence of the Binary instance.
     */
    public String getBitSequence(){
        // Returns the bit sequence of the instance
        return this.bits;
    }

    /**
     * Tells whether or not the Binary instance is signed.
     * @return A boolean representing whether or not the Binary instance is signed.
     */
    public boolean isSigned(){
        // Returns whether or not the binary number is signed
        return this.signed;
    }



    /* SETTERS */



    /**
     * Setter for the bit sequence of the Binary instance.
     * @param newBits A String representing the new bit sequence of the Binary instance.
     * @throws Exception Exception thrown if the bit sequence is found to contain invalid digits.
     */
    public void setBitSequence(String newBits) throws Exception{
        // Calls the other setBitSequence method, assuming keepFormatting is false
        setBitSequence(newBits, false);
    }

    /**
     * Setter for the bit sequence of the Binary instance.
     * @param newBits A String representing the new bit sequence of the Binary instance.
     * @param keepFormatting A boolean representing whether or not the keep the formatting/spacing of the new bit sequence.
     * @throws Exception Exception thrown if the bit sequence is found to contain invalid digits.
     */
    public void setBitSequence(String newBits, boolean keepFormatting) throws Exception{
        // Create a temporary Binary instance with the new bit sequence
        Binary tempBin = new Binary(newBits, false, keepFormatting);
        // Test if the temporary isntance is valid
        if(!tempBin.isValid()){
            // If it isn't, throw an exception
            throw new Exception("Attempting to redefine bit sequence with invalid bit sequence.");
        }
        // Otherwise, set the bit sequence to the new bit sequence
        this.bits = tempBin.getBitSequence();
    }



    /* METHODS */



    /**
     * Prints the bit sequence of the Binary instance, assuming no precision.
     */
    public void printBits(){
        // Calls the other printBits methods, assuming no precision
        this.printBits("none");
    }

    /**
     * Prints the bit sequence of the Binary instance with specified precision.
     * @param precision Desired precision of the printed sequence. Currently supported: none.
     */
    public void printBits(String precision){
        // Gets the bit sequence
        String bitSequence = this.getBitSequence();
        // Converst the precision to lowercase
        precision = precision.toLowerCase();
        // If the precision is none, then simply print the bit sequence
        if(precision.equals("none")){
            System.out.println(bitSequence);
            return;
        }

        /* ADD MORE LATER: NIBBLE, BYTE */

    }

    /**
     * Negates the bit sequence of the Binary instance and replaces it.
     */
    public void negateReplace(){
        // Loops over all of the individual digits
        for(int i = 0; i < this.numBits(); i++){
            // If the digit is a 0, replace it with a 1
            if(this.bits.charAt(i) == '0'){
                this.bits = this.getBitSequence().substring(0, i) + "1" + this.getBitSequence().substring(i + 1, this.numBits());
            }
            // If the digit is a 1, replace it with a 0
            else if(this.bits.charAt(i) == '1'){
                this.bits = this.getBitSequence().substring(0, i) + "0" + this.getBitSequence().substring(i + 1, this.numBits());
            }
        }
    }

    /**
     * Negates the bit sequence of the Binary instance and returns a new Binary instance.
     * @return A new Binary instance with a bit sequence that is the negation of the caller's bit sequence.
     */
    public Binary negatePreserve(){
        // Create a temporary Binary instance that is the same as this one
        Binary temp = this;
        // Call the negateReplace() method on the temporary instance
        temp.negateReplace();
        // Return the temporary instance
        return temp;
    }

    /**
     * Gets the number of bits in the Binary instance's bit sequence.
     * @return An integer representing the number of bits in the bit sequence.
     */
    public int numBits(){
        // Gets rid of all of the spaces (temporarily) to get the number of bits
        return this.getBitSequence().replaceAll(".", "").length();
    }

    /**
     * Gets the number of nibbles in the Binary instance's bit sequence, rounded up to the nearest nibble.
     * @return An integer representing the number of nibbles rounded up.
     */
    public int numNibblesRounded(){
        // Divides the number of bits by 4 and rounds up
        return (int) Math.ceilDiv(this.numBits(), 4);
    }

    /**
     * Gets the precise number of nibbles in the Binary instance's bit sequence.
     * @return A double representing the number of nibbles.
     */
    public double numNibblesPrecise(){
        // Divides the number of bits by 4
        return (double) (this.numBits()) / 4.0;
    }

    /**
     * Gets the number of bytes in the Binary instance's bit sequence, rounded up to the nearest byte.
     * @return An integer representing the number of bytes rounded up.
     */
    public int numBytesRounded(){
        // Divides the number of bits by 8 and rounds up
        return (int) Math.ceilDiv(this.numBits(), 8);
    }

    /**
     * Gets the precise number of bytes in the Binary instance's bit sequence.
     * @return A double representing the number of bytes.
     */
    public double numBytesPrecise(){
        // Divides the number of bits by 8
        return (double) (this.numBits()) / 8.0;
    }

    /**
     * Shifts the bit sequence left.
     * @param numShifts An integer representing the number of shifts to perform.
     */
    public void shiftLeft(int numShifts){
        // Redefines the bit sequence as a substring excluding the first numShifts digits
        this.bits = this.getBitSequence().substring(numShifts);
        // For the number of shifts
        for(int i = 0; i < numShifts; i++){
            // Add a 0 at the end
            this.bits = this.getBitSequence() + "0";
        }
    }

    /**
     * Shifts the bit sequence right.
     * @param numShifts An integer representing the number of shifts to perform.
     */
    public void shiftRight(int numShifts){
        // Redefines the bit sequence as a substring excluding the last numShifts digits
        this.bits = this.getBitSequence().substring(0, this.bits.length() - numShifts + 1);
        // For the number of shifts
        for(int i = 0; i < numShifts; i++){
            // Add a 0 at the start
            this.bits = "0" + this.getBitSequence();
        }
    }



    /* PRIVATE UTILITY METHODS */



    /**
     * Tells whether or not the bit sequence of the Binary instance is valid.
     * @return A boolean indicating whether or not the bit sequence is valid.
     */
    private boolean isValid(){
        // Loop over all of the digits of the bit sequence
        for(int i = 0; i < bits.length(); i++){
            // If the current digit is not in the validDigits, then it is not a valid bit sequence
            if(!validDigits.contains(bits.substring(i, i + 1))){
                return false;
            }
        }
        // Return true if we never return false
        return true;
    }
}
