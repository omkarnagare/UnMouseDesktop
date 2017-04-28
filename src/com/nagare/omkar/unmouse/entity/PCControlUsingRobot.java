package com.nagare.omkar.unmouse.entity;

import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import static java.awt.event.KeyEvent.*;

import com.nagare.omkar.unmouse.robot.PerformPCFunctions;

public class PCControlUsingRobot {
	
	public static int SCROLL_VALUE = 5;

	public static void performAction(TCPPayload tcpPayload) throws AWTException{

		switch (tcpPayload.getClickEvent()) {

		case SINGLE_CLICK:
			moveMousePointer(tcpPayload);
			performSingleClick();
			break;
		case DOUBLE_CLICK:
			moveMousePointer(tcpPayload);
			performSingleClick();
			performSingleClick();
			break;
		case MIDDLE_CLICK:
			PerformPCFunctions.getInstance();
			PerformPCFunctions.getRobotInstance().mouseWheel(SCROLL_VALUE);
			break;
		case MIDDLE_CLICK_LONG:
			SCROLL_VALUE *= -1;
			break;
		case NONE:break;
		case RIGHT_CLICK:
			PerformPCFunctions.getInstance();
			PerformPCFunctions.getRobotInstance().mousePress(InputEvent.BUTTON3_MASK);
			PerformPCFunctions.getRobotInstance().mouseRelease(InputEvent.BUTTON3_MASK);
			break;
		default: break;

		}
		
		switch (tcpPayload.getKeyEvent()) {
		
		case ALT: pressKey(VK_ALT);break;
		case ALT_LONG: longPressKey(VK_ALT); break;
		case ARROW_DOWN: pressKey(VK_DOWN); break;
		case ARROW_LEFT:pressKey(VK_LEFT); break;
		case ARROW_RIGHT:pressKey(VK_RIGHT); break;
		case ARROW_UP:pressKey(VK_UP); break;
		case BACKSPACE: pressKey(VK_BACK_SPACE); break;
		case CAPS_LOCK: pressKey(VK_CAPS_LOCK); break;
		case CTRL: pressKey(VK_CONTROL); break;
		case CTRL_LONG: longPressKey(VK_CONTROL); break;
		case DELETE: pressKey(VK_DELETE); break;
		case END: pressKey(VK_END); break;
		case ENTER: pressKey(VK_ENTER); break;
		case ESC: pressKey(VK_ESCAPE); break;
		case HOME: pressKey(VK_HOME); break;
		case INSERT: pressKey(VK_INSERT); break;
		case SHIFT: pressKey(VK_SHIFT); break;
		case SHIFT_LONG: longPressKey(VK_SHIFT); break;
		case SPACE: pressKey(VK_SPACE); break;
		case TAB: pressKey(VK_TAB); break;
		case WINDOW: pressKey(VK_WINDOWS); break;
		case COPY: copy(); break; 
		case CUT: cut(); break;
		case NONE:break;
		case PASTE: paste(); break;
		case START_PPT: startPPT(); break;
		default: break;
		}
		
		String msg = tcpPayload.getMessage();
		if( msg != null ){
			performKeyEvents(msg);
		}

	}

	private static void paste() throws AWTException {
		
		PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().keyPress(VK_CONTROL);
		PerformPCFunctions.getRobotInstance().keyPress(VK_V);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_V);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_CONTROL);
		
	}

	private static void cut() throws AWTException {
		
		PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().keyPress(VK_CONTROL);
		PerformPCFunctions.getRobotInstance().keyPress(VK_X);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_X);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_CONTROL);
		
	}

	private static void copy() throws AWTException {
		
		PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().keyPress(VK_CONTROL);
		PerformPCFunctions.getRobotInstance().keyPress(VK_C);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_C);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_CONTROL);
		
	}

	private static void startPPT() throws AWTException {
		PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().keyPress(VK_SHIFT);
		PerformPCFunctions.getRobotInstance().keyPress(VK_F5);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_F5);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_SHIFT);
	}

	private static void longPressKey(int keycode) throws AWTException {
		PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().keyPress(keycode);		
	}

	private static void pressKey(int keycode) throws AWTException {
		PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().keyPress(keycode);
		PerformPCFunctions.getRobotInstance().keyRelease(keycode);	
	}

	private static void performKeyEvents(String message) throws AWTException {

		
//		pasteContentUsingClipboard(message);
		
		
		int noOfChars =  message.length();
		for (int i = 0 ; i < noOfChars; i++){
			
			char character = message.charAt(i);
			type(character);
		}
		
	}
	
	private static void pasteContentUsingClipboard(String message) throws AWTException {
		
		StringSelection stringSelection = new StringSelection(message);
		PerformPCFunctions.getInstance();
		PerformPCFunctions.getClipboardInstance().setContents(stringSelection, null);	
		
		//to paste clipboard data
		PerformPCFunctions.getRobotInstance().keyPress(VK_CONTROL);
		PerformPCFunctions.getRobotInstance().keyPress(VK_V);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_V);
		PerformPCFunctions.getRobotInstance().keyRelease(VK_CONTROL);
		
	}

	public static void type(char character) throws AWTException {
        switch (character) {
        case 'a': doType(VK_A); break;
        case 'b': doType(VK_B); break;
        case 'c': doType(VK_C); break;
        case 'd': doType(VK_D); break;
        case 'e': doType(VK_E); break;
        case 'f': doType(VK_F); break;
        case 'g': doType(VK_G); break;
        case 'h': doType(VK_H); break;
        case 'i': doType(VK_I); break;
        case 'j': doType(VK_J); break;
        case 'k': doType(VK_K); break;
        case 'l': doType(VK_L); break;
        case 'm': doType(VK_M); break;
        case 'n': doType(VK_N); break;
        case 'o': doType(VK_O); break;
        case 'p': doType(VK_P); break;
        case 'q': doType(VK_Q); break;
        case 'r': doType(VK_R); break;
        case 's': doType(VK_S); break;
        case 't': doType(VK_T); break;
        case 'u': doType(VK_U); break;
        case 'v': doType(VK_V); break;
        case 'w': doType(VK_W); break;
        case 'x': doType(VK_X); break;
        case 'y': doType(VK_Y); break;
        case 'z': doType(VK_Z); break;
        case 'A': doType(VK_SHIFT, VK_A); break;
        case 'B': doType(VK_SHIFT, VK_B); break;
        case 'C': doType(VK_SHIFT, VK_C); break;
        case 'D': doType(VK_SHIFT, VK_D); break;
        case 'E': doType(VK_SHIFT, VK_E); break;
        case 'F': doType(VK_SHIFT, VK_F); break;
        case 'G': doType(VK_SHIFT, VK_G); break;
        case 'H': doType(VK_SHIFT, VK_H); break;
        case 'I': doType(VK_SHIFT, VK_I); break;
        case 'J': doType(VK_SHIFT, VK_J); break;
        case 'K': doType(VK_SHIFT, VK_K); break;
        case 'L': doType(VK_SHIFT, VK_L); break;
        case 'M': doType(VK_SHIFT, VK_M); break;
        case 'N': doType(VK_SHIFT, VK_N); break;
        case 'O': doType(VK_SHIFT, VK_O); break;
        case 'P': doType(VK_SHIFT, VK_P); break;
        case 'Q': doType(VK_SHIFT, VK_Q); break;
        case 'R': doType(VK_SHIFT, VK_R); break;
        case 'S': doType(VK_SHIFT, VK_S); break;
        case 'T': doType(VK_SHIFT, VK_T); break;
        case 'U': doType(VK_SHIFT, VK_U); break;
        case 'V': doType(VK_SHIFT, VK_V); break;
        case 'W': doType(VK_SHIFT, VK_W); break;
        case 'X': doType(VK_SHIFT, VK_X); break;
        case 'Y': doType(VK_SHIFT, VK_Y); break;
        case 'Z': doType(VK_SHIFT, VK_Z); break;
        case '`': doType(VK_BACK_QUOTE); break;
        case '0': doType(VK_0); break;
        case '1': doType(VK_1); break;
        case '2': doType(VK_2); break;
        case '3': doType(VK_3); break;
        case '4': doType(VK_4); break;
        case '5': doType(VK_5); break;
        case '6': doType(VK_6); break;
        case '7': doType(VK_7); break;
        case '8': doType(VK_8); break;
        case '9': doType(VK_9); break;
        case '-': doType(VK_MINUS); break;
        case '=': doType(VK_EQUALS); break;
        case '~': doType(VK_SHIFT, VK_BACK_QUOTE); break;
        case '!': doType(VK_EXCLAMATION_MARK); break;
        case '@': doType(VK_AT); break;
        case '#': doType(VK_NUMBER_SIGN); break;
        case '$': doType(VK_DOLLAR); break;
        case '%': doType(VK_SHIFT, VK_5); break;
        case '^': doType(VK_CIRCUMFLEX); break;
        case '&': doType(VK_AMPERSAND); break;
        case '*': doType(VK_ASTERISK); break;
        case '(': doType(VK_LEFT_PARENTHESIS); break;
        case ')': doType(VK_RIGHT_PARENTHESIS); break;
        case '_': doType(VK_UNDERSCORE); break;
        case '+': doType(VK_PLUS); break;
        case '\t': doType(VK_TAB); break;
        case '\n': doType(VK_ENTER); break;
        case '[': doType(VK_OPEN_BRACKET); break;
        case ']': doType(VK_CLOSE_BRACKET); break;
        case '\\': doType(VK_BACK_SLASH); break;
        case '{': doType(VK_SHIFT, VK_OPEN_BRACKET); break;
        case '}': doType(VK_SHIFT, VK_CLOSE_BRACKET); break;
        case '|': doType(VK_SHIFT, VK_BACK_SLASH); break;
        case ';': doType(VK_SEMICOLON); break;
        case ':': doType(VK_COLON); break;
        case '\'': doType(VK_QUOTE); break;
        case '"': doType(VK_QUOTEDBL); break;
        case ',': doType(VK_COMMA); break;
        case '<': doType(VK_SHIFT, VK_COMMA); break;
        case '.': doType(VK_PERIOD); break;
        case '>': doType(VK_SHIFT, VK_PERIOD); break;
        case '/': doType(VK_SLASH); break;
        case '?': doType(VK_SHIFT, VK_SLASH); break;
        case ' ': doType(VK_SPACE); break;
        default:
        	System.out.println("Cannot type character " + character);
        }
    }

    private static void doType(int... keyCodes) throws AWTException {
        doType(keyCodes, 0, keyCodes.length);
    }

    private static void doType(int[] keyCodes, int offset, int length) throws AWTException {
        if (length == 0) {
            return;
        }

        PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().keyPress(keyCodes[offset]);
        doType(keyCodes, offset + 1, length - 1);
		PerformPCFunctions.getRobotInstance().keyRelease(keyCodes[offset]);
    }

	private static void performSingleClick() throws AWTException {

		PerformPCFunctions.getInstance();
		PerformPCFunctions.getRobotInstance().mousePress(InputEvent.BUTTON1_MASK);
		PerformPCFunctions.getRobotInstance().mouseRelease(InputEvent.BUTTON1_MASK);

	}

	private static void moveMousePointer(TCPPayload tcpPayload) throws AWTException {

		int mobileScreenWidth = tcpPayload.getScreenWidth();
		int mobileScreenHeight = tcpPayload.getScreenHeight();
		PerformPCFunctions.getInstance();
		float scaleX = (float) (PerformPCFunctions.getScreenWidth()/(mobileScreenWidth*1.0));
		float scaleY = (float) (PerformPCFunctions.getScreenHeight()/(mobileScreenHeight*1.0));
		PerformPCFunctions.getRobotInstance().mouseMove((int)(tcpPayload.getCoordinateX()*scaleX), (int)(tcpPayload.getCoordinateY()*scaleY));

	}

}
