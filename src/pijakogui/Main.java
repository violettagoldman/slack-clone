package pijakogui;

import pijakogui.Helpers.RandomString;

public class Main {
    public static final String instanceID = (new RandomString()).nextString();
    public static void main( String[] args ) throws Exception {
        // Start the demo
        new PijakoWindow().setVisible( true );

    }
}
