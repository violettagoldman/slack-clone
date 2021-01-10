package pijakogui.panel;

import pijakogui.compoment.MyTextField;

public class BaseChannel extends MyPanel{
    public BaseChannel(){
        super();
        this.panel.add(MyTextField.borderEmpty("Welcome !!\n\n\n"));
        this.panel.add(MyTextField.borderEmpty("You can create a new channel or go to your profile !!\n\n\n"));
    }

}
