package sample;

public class ReformatBuffer {

    //public String outputString;

    static int cutoffASCII = 10;
    static String cummulativeTempBuffer = "";

    public static void parseByteArray(byte[] readBuffer){
        String readBufferSnippet = new String(readBuffer);
        cummulativeTempBuffer = cummulativeTempBuffer.concat(readBufferSnippet);

        if((cummulativeTempBuffer.indexOf(cutoffASCII)+1)>0){
            String outputString = cummulativeTempBuffer.substring(0,cummulativeTempBuffer.indexOf(cutoffASCII)+1);
            cummulativeTempBuffer = cummulativeTempBuffer.substring(cummulativeTempBuffer.indexOf(cutoffASCII)+1);

            System.out.println(outputString);
        }
    }
}
