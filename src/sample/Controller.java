package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Controller {
    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12;
    private Button[] buttonArray;

    private boolean firstPressedButton = true;

    private int firstSelectedButtonID;
    private int firstImageID;

    @FXML
    void initialize(){

        initializeButtonArray();

        List<Integer> imageList = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            int sizeArrayButtons = (int)(buttonArray.length * 0.5 );//f);
            for (int j = 0; j < sizeArrayButtons; j++) {
                imageList.add(j);
            }
        }
        Collections.shuffle(imageList);

        for (int i = 0; i < buttonArray.length; i++) {
            int j = i;

            buttonArray[i].setOnAction(e -> {
                buttonSelect(j, imageList.get(j));
            });
        }
    }

    private void buttonSelect(int idSelectedButton,int idImage){

        if(firstPressedButton){
            firstPressedButton = false;

            firstSelectedButtonID = idSelectedButton;
            firstImageID = idImage;

            setImageButton(idSelectedButton,idImage);
        }

        else {
            setImageButton(firstSelectedButtonID, firstImageID);
            firstPressedButton = true;

            if (idImage == firstImageID && idSelectedButton != firstSelectedButtonID){
                setImageButton(idSelectedButton, firstImageID);
                buttonArray[idSelectedButton].setDisable(true);
                buttonArray[firstSelectedButtonID].setDisable(true);

            }else {

                buttonArray[firstSelectedButtonID].setGraphic(null);
                buttonArray[idSelectedButton].setGraphic(null);

                buttonSelect(idSelectedButton, idImage);
            }
        }
    }

    // creating a collection of buttons
    private void initializeButtonArray() {
        buttonArray = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12};
    }

    // setting buttons' image
    private void setImageButton(int idButton, int idImage){
        Image imageDecline = new Image(getClass().getResourceAsStream(idImage + ".png"));
        buttonArray[idButton].setGraphic(new ImageView(imageDecline));
    }
}