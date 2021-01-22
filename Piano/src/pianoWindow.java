import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class pianoWindow implements ActionListener, KeyListener{
	
	//Action C5Keyboard;
	private static final int CIRCLE = 0;
	private static final int SHARPCIRCLE = 1;
	private static final int SHARPKEY = 2;
	
	private static final int C5SOUND = 0;
	private static final int D5SOUND = 1;
	private static final int E5SOUND = 2;
	private static final int F5SOUND = 3;
	private static final int G5SOUND = 4;
	private static final int A5SOUND = 5;
	private static final int B5SOUND = 6;
	private static final int C6SOUND = 7;
	private static final int D6SOUND = 8;
	private static final int E6SOUND = 9;
	private static final int F6SOUND = 10;
	private static final int G6SOUND = 11;
	private static final int A6SOUND = 12;
	private static final int B6SOUND = 13;
	private static final int CSHARP5SOUND = 14;
	private static final int DSHARP5SOUND = 15;
	private static final int FSHARP5SOUND = 16;
	private static final int GSHARP5SOUND = 17;
	private static final int ASHARP5SOUND = 18;
	private static final int CSHARP6SOUND = 19;
	private static final int DSHARP6SOUND = 20;
	private static final int FSHARP6SOUND = 21;
	private static final int GSHARP6SOUND = 22;
	private static final int ASHARP6SOUND = 23;
	private static final int C7SOUND = 24;

	public JFrame frame;
	public JButton btnC5, btnD5, btnE5, btnF5, btnG5, btnA5, btnB5, btnC6, btnD6, btnE6, btnF6, btnG6, btnA6, btnB6, btnC7 ;
	public JButton btnCSharp5, btnDSharp5, btnFSharp5, btnGSharp5, btnASharp5, btnCSharp6, btnDSharp6, btnFSharp6, btnGSharp6, btnASharp6;
	public JRadioButton onNote, offNote, onKeyboard, offKeyboard;
	public ButtonGroup keyboardGroup;
	public ButtonGroup noteGroup;
	SoundEffect se = new SoundEffect();
	public JLabel lblC5Key;
	private boolean keysDown[] = new boolean[0x10000];
	public JLabel c5circle, d5circle, e5circle, f5circle, g5circle, a5circle, b5circle, c6circle, d6circle, e6circle, f6circle, g6circle, a6circle, b6circle, c7circle;
	public JLabel csharp5circle, dsharp5circle, fsharp5circle, gsharp5circle, asharp5circle, csharp6circle, dsharp6circle, fsharp6circle, gsharp6circle, asharp6circle;
	             
	public static String imagesArray [] = new String[] {
		"res/images/Circle.png",
		"res/images/sharpcircle1.png",
		"res/images/SharpKey.png"
	};
	
	public static String soundsArray [] = new String [] {
			
			"res/wavFiles/C5New.wav", //0
			"res/wavFiles/D5.wav",
			"res/wavFiles/E5.wav",
			"res/wavFiles/F5.wav",
			"res/wavFiles/G5.wav",
			"res/wavFiles/A5.wav",
			"res/wavFiles/B5.wav",
			"res/wavFiles/C6.wav",
			"res/wavFiles/D6.wav",
			"res/wavFiles/E6.wav",
			"res/wavFiles/F6.wav",
			"res/wavFiles/G6.wav",
			"res/wavFiles/A6.wav",
			"res/wavFiles/B6.wav",
			"res/wavFiles/C#5.wav",
			"res/wavFiles/D#5.wav",
			"res/wavFiles/F#5.wav",
			"res/wavFiles/G#5.wav",
			"res/wavFiles/A#5.wav",
			"res/wavFiles/C#6.wav",
			"res/wavFiles/D#6.wav",
			"res/wavFiles/F#6.wav",
			"res/wavFiles/G#6.wav",
			"res/wavFiles/A#6.wav",
			"res/wavFiles/C7.wav"
	};

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pianoWindow window = new pianoWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
			
			
	}

	/**
	 * Create the application.
	 */
	public pianoWindow() {
		initialize();
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.gray);;
		frame.getContentPane().setBackground(UIManager.getColor("Button.darkShadow"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);
		frame.addKeyListener(this);

	
		//C5Keyboard = new C5Keyboard();
		//btnC5.getInputMap().put(KeyStroke.getKeyStroke("Z"), "C5");
	//	btnC5.getActionMap().put("C5", C5Keyboard);
	
		
	
	//labels
		JLabel lblC5board = new JLabel("Z");
		lblC5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblC5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblC5board.setBackground(Color.WHITE);
		lblC5board.setBounds(60, 465, 40, 20);
		frame.getContentPane().add(lblC5board);
		
		JLabel lblD5Board = new JLabel("X");
		lblD5Board.setHorizontalAlignment(SwingConstants.CENTER);
		lblD5Board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblD5Board.setBounds(120, 465, 40, 20);
		frame.getContentPane().add(lblD5Board);
		
		JLabel lblE5board = new JLabel("C");
		lblE5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblE5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblE5board.setBounds(180, 465, 40, 20);
		frame.getContentPane().add(lblE5board);
		
		JLabel lblF5board = new JLabel("V");
		lblF5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblF5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblF5board.setBounds(240, 465, 40, 20);
		frame.getContentPane().add(lblF5board);
		
		JLabel lblG5board = new JLabel("B");
		lblG5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblG5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblG5board.setBounds(300, 465, 40, 20);
		frame.getContentPane().add(lblG5board);
		

		JLabel lblA5Board = new JLabel("N");
		lblA5Board.setHorizontalAlignment(SwingConstants.CENTER);
		lblA5Board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblA5Board.setBounds(360, 465, 40, 20);
		frame.getContentPane().add(lblA5Board);
		
		
			
		JLabel lblB5board = new JLabel("M");
		lblB5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblB5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblB5board.setBounds(415, 465, 50, 20);
		frame.getContentPane().add(lblB5board);
		
		JLabel lblC6board = new JLabel("Q");
		lblC6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblC6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblC6board.setBounds(480, 465, 40, 20);
		frame.getContentPane().add(lblC6board);
		
		JLabel lblD6board = new JLabel("W");
		lblD6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblD6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblD6board.setBounds(540, 465, 40, 20);
		frame.getContentPane().add(lblD6board);
		
		JLabel lblE6board = new JLabel("E");
		lblE6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblE6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblE6board.setBounds(600, 465, 40, 20);
		frame.getContentPane().add(lblE6board);
		
		JLabel lblF6board = new JLabel("R");
		lblF6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblF6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblF6board.setBounds(660, 465, 40, 20);
		frame.getContentPane().add(lblF6board);
		
		JLabel lblG6board = new JLabel("T");
		lblG6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblG6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblG6board.setBounds(720, 465, 40, 20);
		frame.getContentPane().add(lblG6board);
		
		JLabel lblA6board = new JLabel("Y");
		lblA6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblA6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblA6board.setBounds(780, 465, 40, 20);
		frame.getContentPane().add(lblA6board);
		
		JLabel lblB6board = new JLabel("U");
		lblB6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblB6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblB6board.setBounds(840, 465, 40, 16);
		frame.getContentPane().add(lblB6board);
		
		JLabel lblC7board = new JLabel("I");
		lblC7board.setHorizontalAlignment(SwingConstants.CENTER);
		lblC7board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblC7board.setBounds(900, 465, 40, 20);
		frame.getContentPane().add(lblC7board);
		
		JLabel lblCSharp5board = new JLabel("S");
		lblCSharp5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblCSharp5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblCSharp5board.setForeground(Color.WHITE);
		lblCSharp5board.setBounds(90, 365, 40, 20);
		frame.getContentPane().add(lblCSharp5board);
		
		JLabel lblDSharp5board = new JLabel("D");
		lblDSharp5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblDSharp5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSharp5board.setForeground(Color.WHITE);
		lblDSharp5board.setBounds(150, 365, 40, 20);
		frame.getContentPane().add(lblDSharp5board);
		
		JLabel lblFSharp5board = new JLabel("G");
		lblFSharp5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblFSharp5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblFSharp5board.setForeground(Color.WHITE);
		lblFSharp5board.setBounds(270, 365, 40, 20);
		frame.getContentPane().add(lblFSharp5board);
		
		JLabel lblGSharp5board = new JLabel("H");
		lblGSharp5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblGSharp5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblGSharp5board.setForeground(Color.WHITE);
		lblGSharp5board.setBounds(330, 365, 40, 20);
		frame.getContentPane().add(lblGSharp5board);
		
		JLabel lblASharp5board = new JLabel("J");
		lblASharp5board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblASharp5board.setHorizontalAlignment(SwingConstants.CENTER);
		lblASharp5board.setForeground(Color.WHITE);
		lblASharp5board.setBounds(390, 365, 40, 20);
		frame.getContentPane().add(lblASharp5board);
		
		JLabel lblCSharp6board = new JLabel("2");
		lblCSharp6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblCSharp6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblCSharp6board.setForeground(Color.WHITE);
		lblCSharp6board.setBounds(510, 365, 40, 20);
		frame.getContentPane().add(lblCSharp6board);
		
		JLabel lblDSharp6board = new JLabel("3");
		lblDSharp6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblDSharp6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSharp6board.setForeground(Color.WHITE);
		lblDSharp6board.setBounds(570, 365, 40, 20);
		frame.getContentPane().add(lblDSharp6board);
		
		JLabel lblFSharp6board = new JLabel("5");
		lblFSharp6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblFSharp6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblFSharp6board.setForeground(Color.WHITE);
		lblFSharp6board.setBounds(690, 365, 40, 20);
		frame.getContentPane().add(lblFSharp6board);
		
		JLabel lblGSharp6board = new JLabel("6");
		lblGSharp6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblGSharp6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblGSharp6board.setForeground(Color.WHITE);
		lblGSharp6board.setBounds(750, 365, 40, 20);
		frame.getContentPane().add(lblGSharp6board);
		
		JLabel lblASharp6board = new JLabel("7");
		lblASharp6board.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblASharp6board.setHorizontalAlignment(SwingConstants.CENTER);
		lblASharp6board.setForeground(Color.WHITE);
		lblASharp6board.setBounds(810, 365, 40, 20);
		frame.getContentPane().add(lblASharp6board);
		
	//labels for white keys	
		JLabel lblC5Key = new JLabel("C5");
		lblC5Key.setForeground(new Color(0, 102, 255));
		lblC5Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblC5Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblC5Key.setBounds(50, 420, 60, 20);
		frame.getContentPane().add(lblC5Key);
		
		JLabel lblD5Key = new JLabel("D5");
		lblD5Key.setForeground(new Color(0, 102, 255));
		lblD5Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblD5Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblD5Key.setBounds(110, 420, 60, 20);
		frame.getContentPane().add(lblD5Key);
		
		JLabel lblE5Key = new JLabel("E5");
		lblE5Key.setForeground(new Color(0, 102, 255));
		lblE5Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblE5Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblE5Key.setBounds(170, 420, 60, 20);
		frame.getContentPane().add(lblE5Key);
		
		JLabel lblF5Key = new JLabel("F5");
		lblF5Key.setForeground(new Color(0, 102, 255));
		lblF5Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblF5Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblF5Key.setBounds(230, 420, 60, 20);
		frame.getContentPane().add(lblF5Key);
		
		JLabel lblG5Key = new JLabel("G5");
		lblG5Key.setForeground(new Color(0, 102, 255));
		lblG5Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblG5Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblG5Key.setBounds(290, 420, 60, 20);
		frame.getContentPane().add(lblG5Key);
		
		JLabel lblA5Key = new JLabel("A5");
		lblA5Key.setForeground(new Color(0, 102, 255));
		lblA5Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblA5Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblA5Key.setBounds(350, 420, 60, 20);
		frame.getContentPane().add(lblA5Key);
		
		JLabel lblB5Key = new JLabel("B5");
		lblB5Key.setForeground(new Color(0, 102, 255));
		lblB5Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblB5Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblB5Key.setBounds(410, 420, 60, 20);
		frame.getContentPane().add(lblB5Key);
		
		JLabel lblC6Key = new JLabel("C6");
		lblC6Key.setForeground(new Color(0, 102, 255));
		lblC6Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblC6Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblC6Key.setBounds(470, 420, 60, 20);
		frame.getContentPane().add(lblC6Key);
		
		JLabel lblD6Key = new JLabel("D6");
		lblD6Key.setForeground(new Color(0, 102, 255));
		lblD6Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblD6Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblD6Key.setBounds(530, 420, 60, 20);
		frame.getContentPane().add(lblD6Key);
		
		JLabel lblE6Key = new JLabel("E6");
		lblE6Key.setForeground(new Color(0, 102, 255));
		lblE6Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblE6Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblE6Key.setBounds(590, 420, 60, 20);
		frame.getContentPane().add(lblE6Key);
		
		JLabel lblF6Key = new JLabel("F6");
		lblF6Key.setForeground(new Color(0, 102, 255));
		lblF6Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblF6Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblF6Key.setBounds(650, 420, 60, 20);
		frame.getContentPane().add(lblF6Key);
		
		JLabel lblG6Key = new JLabel("G6");
		lblG6Key.setForeground(new Color(0, 102, 255));
		lblG6Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblG6Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblG6Key.setBounds(710, 420, 60, 20);
		frame.getContentPane().add(lblG6Key);
		
		JLabel lblA6Key = new JLabel("A6");
		lblA6Key.setForeground(new Color(0, 102, 255));
		lblA6Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblA6Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblA6Key.setBounds(770, 420, 60, 20);
		frame.getContentPane().add(lblA6Key);
		
		JLabel lblB6Key = new JLabel("B6");
		lblB6Key.setForeground(new Color(0, 102, 255));
		lblB6Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblB6Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblB6Key.setBounds(830, 420, 60, 20);
		frame.getContentPane().add(lblB6Key);
		
		JLabel lblC7Key = new JLabel("C7");
		lblC7Key.setForeground(new Color(0, 102, 255));
		lblC7Key.setFont(new Font("Avenir", Font.BOLD, 16));
		lblC7Key.setHorizontalAlignment(SwingConstants.CENTER);
		lblC7Key.setBounds(890, 420, 60, 20);
		frame.getContentPane().add(lblC7Key);
		
		
		JLabel lblCSharp5key = new JLabel("C#5");
		lblCSharp5key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblCSharp5key.setHorizontalAlignment(SwingConstants.CENTER);
		lblCSharp5key.setForeground(new Color(102, 170, 237));
		lblCSharp5key.setBounds(90, 320, 40, 20);
		frame.getContentPane().add(lblCSharp5key);
		
		JLabel lblDSharp5key = new JLabel("D#5");
		lblDSharp5key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblDSharp5key.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSharp5key.setForeground(new Color(102, 170, 237));
		lblDSharp5key.setBounds(150, 320, 40, 20);
		frame.getContentPane().add(lblDSharp5key);
		
		JLabel lblFSharp5key = new JLabel("F#5");
		lblFSharp5key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblFSharp5key.setHorizontalAlignment(SwingConstants.CENTER);
		lblFSharp5key.setForeground(new Color(102, 170, 237));
		lblFSharp5key.setBounds(270, 320, 40, 20);
		frame.getContentPane().add(lblFSharp5key);
		
		JLabel lblGSharp5key = new JLabel("G#5");
		lblGSharp5key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblGSharp5key.setHorizontalAlignment(SwingConstants.CENTER);
		lblGSharp5key.setForeground(new Color(102, 170, 237));
		lblGSharp5key.setBounds(330, 320, 40, 20);
		frame.getContentPane().add(lblGSharp5key);
		
		JLabel lblASharp5key = new JLabel("A#5");
		lblASharp5key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblASharp5key.setHorizontalAlignment(SwingConstants.CENTER);
		lblASharp5key.setForeground(new Color(102, 170, 237));
		lblASharp5key.setBounds(390, 320, 40, 20);
		frame.getContentPane().add(lblASharp5key);
		
		JLabel lblCSharp6key = new JLabel("C#6");
		lblCSharp6key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblCSharp6key.setHorizontalAlignment(SwingConstants.CENTER);
		lblCSharp6key.setForeground(new Color(102, 170, 237));
		lblCSharp6key.setBounds(510, 320, 40, 20);
		frame.getContentPane().add(lblCSharp6key);
		
		JLabel lblDSharp6key = new JLabel("D#6");
		lblDSharp6key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblDSharp6key.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSharp6key.setForeground(new Color(102, 170, 237));
		lblDSharp6key.setBounds(570, 320, 40, 20);
		frame.getContentPane().add(lblDSharp6key);
		
		JLabel lblFSharp6key = new JLabel("F#6");
		lblFSharp6key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblFSharp6key.setHorizontalAlignment(SwingConstants.CENTER);
		lblFSharp6key.setForeground(new Color(102, 170, 237));
		lblFSharp6key.setBounds(690, 320, 40, 20);
		frame.getContentPane().add(lblFSharp6key);
		
		JLabel lblGSharp6key = new JLabel("G#6");
		lblGSharp6key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblGSharp6key.setHorizontalAlignment(SwingConstants.CENTER);
		lblGSharp6key.setForeground(new Color(102, 170, 237));
		lblGSharp6key.setBounds(750, 320, 40, 20);
		frame.getContentPane().add(lblGSharp6key);
		
		JLabel lblASharp6key = new JLabel("A#6");
		lblASharp6key.setFont(new Font("Avenir", Font.PLAIN, 16));
		lblASharp6key.setHorizontalAlignment(SwingConstants.CENTER);
		lblASharp6key.setForeground(new Color(102, 170, 237));
		lblASharp6key.setBounds(810, 320, 40, 20);
		frame.getContentPane().add(lblASharp6key);	
		
		ImageIcon CircImage = new ImageIcon(imagesArray[CIRCLE]);
		ImageIcon sharpcircImage = new ImageIcon(imagesArray[SHARPCIRCLE]);
	//circle labels that show up when you press button	
		c5circle = new JLabel(CircImage);
		c5circle.setBackground(new Color(255, 255, 255));
		c5circle.setOpaque(true);
		c5circle.setForeground(new Color(255, 255, 255));
		c5circle.setBounds(60, 428, 40, 47);
		c5circle.setVisible(false);
		frame.getContentPane().add(c5circle);
		
		
		d5circle = new JLabel(CircImage);
		d5circle.setBackground(new Color(255, 255, 255));
		d5circle.setOpaque(true);
		d5circle.setForeground(new Color(255, 255, 255));
		d5circle.setBounds(120, 428, 40, 47);
		d5circle.setVisible(false);
		frame.getContentPane().add(d5circle);

		e5circle = new JLabel(CircImage);
		e5circle.setBackground(new Color(255, 255, 255));
		e5circle.setOpaque(true);
		e5circle.setForeground(new Color(255, 255, 255));
		e5circle.setBounds(180, 428, 40, 47);
		e5circle.setVisible(false);
		frame.getContentPane().add(e5circle);
		
		f5circle = new JLabel(CircImage);
		f5circle.setBackground(new Color(255, 255, 255));
		f5circle.setOpaque(true);
		f5circle.setForeground(new Color(255, 255, 255));
		f5circle.setBounds(240, 428, 40, 47);
		f5circle.setVisible(false);
		frame.getContentPane().add(f5circle);
		
		g5circle = new JLabel(CircImage);
		g5circle.setBackground(new Color(255, 255, 255));
		g5circle.setOpaque(true);
		g5circle.setForeground(new Color(255, 255, 255));
		g5circle.setBounds(300, 428, 40, 47);
		g5circle.setVisible(false);
		frame.getContentPane().add(g5circle);
		
		a5circle = new JLabel(CircImage);
		a5circle.setBackground(new Color(255, 255, 255));
		a5circle.setOpaque(true);
		a5circle.setForeground(new Color(255, 255, 255));
		a5circle.setBounds(360, 428, 40, 47);
		a5circle.setVisible(false);
		frame.getContentPane().add(a5circle);
		
		b5circle = new JLabel(CircImage);
		b5circle.setBackground(new Color(255, 255, 255));
		b5circle.setOpaque(true);
		b5circle.setForeground(new Color(255, 255, 255));
		b5circle.setBounds(420, 428, 40, 47);
		b5circle.setVisible(false);
		frame.getContentPane().add(b5circle);
		
		c6circle = new JLabel(CircImage);
		c6circle.setBackground(new Color(255, 255, 255));
		c5circle.setOpaque(true);
		c6circle.setForeground(new Color(255, 255, 255));
		c6circle.setBounds(480, 428, 40, 47);
		c6circle.setVisible(false);
		frame.getContentPane().add(c6circle);
		
		d6circle = new JLabel(CircImage);
		d6circle.setBackground(new Color(255, 255, 255));
		d6circle.setOpaque(true);
		d6circle.setForeground(new Color(255, 255, 255));
		d6circle.setBounds(540, 428, 40, 47);
		d6circle.setVisible(false);
		frame.getContentPane().add(d6circle);

		e6circle = new JLabel(CircImage);
		e6circle.setBackground(new Color(255, 255, 255));
		e6circle.setOpaque(true);
		e6circle.setForeground(new Color(255, 255, 255));
		e6circle.setBounds(600, 428, 40, 47);
		e6circle.setVisible(false);
		frame.getContentPane().add(e6circle);
		
		f6circle = new JLabel(CircImage);
		f6circle.setBackground(new Color(255, 255, 255));
		f6circle.setOpaque(true);
		f6circle.setForeground(new Color(255, 255, 255));
		f6circle.setBounds(660, 428, 40, 47);
		f6circle.setVisible(false);
		frame.getContentPane().add(f6circle);
		
		g6circle = new JLabel(CircImage);
		g6circle.setBackground(new Color(255, 255, 255));
		g6circle.setOpaque(true);
		g6circle.setForeground(new Color(255, 255, 255));
		g6circle.setBounds(720, 428, 40, 47);
		g6circle.setVisible(false);
		frame.getContentPane().add(g6circle);
		
		a6circle = new JLabel(CircImage);
		a6circle.setBackground(new Color(255, 255, 255));
		a6circle.setOpaque(true);
		a6circle.setForeground(new Color(255, 255, 255));
		a6circle.setBounds(780, 428, 40, 47);
		a6circle.setVisible(false);
		frame.getContentPane().add(a6circle);
		
		b6circle = new JLabel(CircImage);
		b6circle.setBackground(new Color(255, 255, 255));
		b6circle.setOpaque(true);
		b6circle.setForeground(new Color(255, 255, 255));
		b6circle.setBounds(840, 428, 40, 47);
		b6circle.setVisible(false);
		frame.getContentPane().add(b6circle);
		
		c7circle = new JLabel(CircImage);
		c7circle.setBackground(new Color(255, 255, 255));
		c7circle.setOpaque(true);
		c7circle.setForeground(new Color(255, 255, 255));
		c7circle.setBounds(900, 428, 40, 47);
		c7circle.setVisible(false);
		frame.getContentPane().add(c7circle);
		
		csharp5circle = new JLabel(sharpcircImage);
		csharp5circle.setBackground(new Color(0, 0, 0));
		csharp5circle.setOpaque(true);
		csharp5circle.setForeground(new Color(255, 255, 255));
		csharp5circle.setBounds(98, 325, 25, 55);
		csharp5circle.setVisible(false);
		frame.getContentPane().add(csharp5circle);
		
		dsharp5circle = new JLabel(sharpcircImage);
		dsharp5circle.setBackground(new Color(0, 0, 0));
		dsharp5circle.setOpaque(true);
		dsharp5circle.setForeground(new Color(255, 255, 255));
		dsharp5circle.setBounds(158, 325, 25, 55);
		dsharp5circle.setVisible(false);
		frame.getContentPane().add(dsharp5circle);
		
		fsharp5circle = new JLabel(sharpcircImage);
		fsharp5circle.setBackground(new Color(0, 0, 0));
		fsharp5circle.setOpaque(true);
		fsharp5circle.setForeground(new Color(255, 255, 255));
		fsharp5circle.setBounds(278, 325, 25, 55);
		fsharp5circle.setVisible(false);
		frame.getContentPane().add(fsharp5circle);
		
		gsharp5circle = new JLabel(sharpcircImage);
		gsharp5circle.setBackground(new Color(0, 0, 0));
		gsharp5circle.setOpaque(true);
		gsharp5circle.setForeground(new Color(255, 255, 255));
		gsharp5circle.setBounds(338, 325, 25, 55);
		gsharp5circle.setVisible(false);
		frame.getContentPane().add(gsharp5circle);
		
		asharp5circle = new JLabel(sharpcircImage);
		asharp5circle.setBackground(new Color(0, 0, 0));
		asharp5circle.setOpaque(true);
		asharp5circle.setForeground(new Color(255, 255, 255));
		asharp5circle.setBounds(398, 325, 25, 55);
		asharp5circle.setVisible(false);
		frame.getContentPane().add(asharp5circle);
		
		csharp6circle = new JLabel(sharpcircImage);
		csharp6circle.setBackground(new Color(0, 0, 0));
		csharp6circle.setOpaque(true);
		csharp6circle.setForeground(new Color(255, 255, 255));
		csharp6circle.setBounds(518, 325, 25, 55);
		csharp6circle.setVisible(false);
		frame.getContentPane().add(csharp6circle);
		
		dsharp6circle = new JLabel(sharpcircImage);
		dsharp6circle.setBackground(new Color(0, 0, 0));
		dsharp6circle.setOpaque(true);
		dsharp6circle.setForeground(new Color(255, 255, 255));
		dsharp6circle.setBounds(578, 325, 25, 55);
		dsharp6circle.setVisible(false);
		frame.getContentPane().add(dsharp6circle);
		
		fsharp6circle = new JLabel(sharpcircImage);
		fsharp6circle.setBackground(new Color(0, 0, 0));
		fsharp6circle.setOpaque(true);
		fsharp6circle.setForeground(new Color(255, 255, 255));
		fsharp6circle.setBounds(698, 325, 25, 55);
		fsharp6circle.setVisible(false);
		frame.getContentPane().add(fsharp6circle);
		
		gsharp6circle = new JLabel(sharpcircImage);
		gsharp6circle.setBackground(new Color(0, 0, 0));
		gsharp6circle.setOpaque(true);
		gsharp6circle.setForeground(new Color(255, 255, 255));
		gsharp6circle.setBounds(758, 325, 25, 55);
		gsharp6circle.setVisible(false);
		frame.getContentPane().add(gsharp6circle);
		
		asharp6circle = new JLabel(sharpcircImage);
		asharp6circle.setBackground(new Color(0, 0, 0));
		asharp6circle.setOpaque(true);
		asharp6circle.setForeground(new Color(255, 255, 255));
		asharp6circle.setBounds(818, 325, 25, 55);
		asharp6circle.setVisible(false);
		frame.getContentPane().add(asharp6circle);
		
		
		
		
		
	//sharp buttons	
		ImageIcon SharpImage = new ImageIcon(imagesArray[SHARPKEY]);
		
		btnCSharp5 = new JButton("c#5", SharpImage) {};
		btnCSharp5.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnCSharp5.setRequestFocusEnabled(false);
		btnCSharp5.setBounds(92, 270, 40, 135);
		frame.getContentPane().add(btnCSharp5);
		btnCSharp5.addActionListener(this);
		
		btnDSharp5 = new JButton("d#5",SharpImage) {};
		btnDSharp5.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnDSharp5.setRequestFocusEnabled(false);
		btnDSharp5.setBounds(152, 270, 40, 135);
		frame.getContentPane().add(btnDSharp5);
		btnDSharp5.addActionListener(this);
		
		btnFSharp5 = new JButton("f#5",SharpImage) {};
		btnFSharp5.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnFSharp5.setRequestFocusEnabled(false);
		btnFSharp5.setBounds(272, 270, 40, 135);
		frame.getContentPane().add(btnFSharp5);
		btnFSharp5.addActionListener(this);
			
		btnGSharp5 = new JButton("g#5",SharpImage) {};
		btnGSharp5.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnGSharp5.setRequestFocusEnabled(false);
		btnGSharp5.setBounds(332, 270, 40, 135);
		frame.getContentPane().add(btnGSharp5);
		btnGSharp5.addActionListener(this);
	
		btnASharp5 = new JButton("a#5",SharpImage) {};
		btnASharp5.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnASharp5.setRequestFocusEnabled(false);
		btnASharp5.setBounds(392, 270, 40, 135);
		frame.getContentPane().add(btnASharp5);
		btnASharp5.addActionListener(this);
		
		btnCSharp6 = new JButton("c#6",SharpImage) {};
		btnCSharp6.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnCSharp6.setRequestFocusEnabled(false);
		btnCSharp6.setBounds(512, 270, 40, 135);
		frame.getContentPane().add(btnCSharp6);
		btnCSharp6.addActionListener(this);
	
		btnDSharp6 = new JButton("d#6",SharpImage) {};
		btnDSharp6.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnDSharp6.setRequestFocusEnabled(false);
		btnDSharp6.setBounds(572, 270, 40, 135);
		frame.getContentPane().add(btnDSharp6);
		btnDSharp6.addActionListener(this);
	
		btnFSharp6 = new JButton("f#6",SharpImage) {};
		btnFSharp6.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnFSharp6.setRequestFocusEnabled(false);
		btnFSharp6.setBounds(692, 270, 40, 135);
		frame.getContentPane().add(btnFSharp6);
		btnFSharp6.addActionListener(this);
		
		btnGSharp6 = new JButton("g#6",SharpImage) {};
		btnGSharp6.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnGSharp6.setRequestFocusEnabled(false);
		btnGSharp6.setBounds(752, 270, 40, 135);
		frame.getContentPane().add(btnGSharp6);
		btnGSharp6.addActionListener(this);
		
		btnASharp6 = new JButton("a#6",SharpImage) {};
		btnASharp6.setFont(new Font("Lucida Grande", Font.PLAIN, 0));
		btnASharp6.setRequestFocusEnabled(false);
		btnASharp6.setBounds(812, 270, 40, 135);
		frame.getContentPane().add(btnASharp6);
		btnASharp6.addActionListener(this);;

		
		btnC5 = new JButton("c5");
		btnC5.setRequestFocusEnabled(false);
		btnC5.setForeground(Color.WHITE);
		btnC5.setBounds(50, 270, 60, 240);
		frame.getContentPane().add(btnC5);
		btnC5.addActionListener(this);


		btnD5 = new JButton("d5");
		btnD5.setForeground(Color.WHITE);
		btnD5.setRequestFocusEnabled(false);
		btnD5.setBounds(110, 270, 60, 240);
		frame.getContentPane().add(btnD5);
		btnD5.addActionListener(this);
		
		btnE5 = new JButton("e5");
		btnE5.setForeground(Color.WHITE);
		btnE5.setRequestFocusEnabled(false);
		btnE5.setBounds(170, 270, 60, 240);
		frame.getContentPane().add(btnE5);
		btnE5.addActionListener(this);
		
		btnF5 = new JButton("f5");
		btnF5.setForeground(Color.WHITE);
		btnF5.setRequestFocusEnabled(false);
		btnF5.setBounds(230, 270, 60, 240);
		frame.getContentPane().add(btnF5);
		btnF5.addActionListener(this);
		
		btnG5 = new JButton("g5");
		btnG5.setForeground(Color.WHITE);
		btnG5.setRequestFocusEnabled(false);
		btnG5.setBounds(290, 270, 60, 240);
		frame.getContentPane().add(btnG5);
		btnG5.addActionListener(this);
		
		btnA5 = new JButton("a5");
		btnA5.setForeground(Color.WHITE);
		btnA5.setRequestFocusEnabled(false);
		btnA5.setBounds(350, 270, 60, 240);
		frame.getContentPane().add(btnA5);
		btnA5.addActionListener(this);
		
		btnB5 = new JButton("b5");
		btnB5.setForeground(Color.WHITE);
		btnB5.setRequestFocusEnabled(false);
		btnB5.setBounds(410, 270, 60, 240);
		frame.getContentPane().add(btnB5);
		btnB5.addActionListener(this);
		
		btnC6 = new JButton("c6");
		btnC6.setForeground(Color.WHITE);
		btnC6.setRequestFocusEnabled(false);
		btnC6.setBounds(470, 270, 60, 240);
		frame.getContentPane().add(btnC6);
		btnC6.addActionListener(this);
		
		btnD6 = new JButton("d6");
		btnD6.setForeground(Color.WHITE);
		btnD6.setRequestFocusEnabled(false);
		btnD6.setBounds(530, 270, 60, 240);
		frame.getContentPane().add(btnD6);
		btnD6.addActionListener(this);	
		
		btnE6 = new JButton("e6");
		btnE6.setForeground(Color.WHITE);
		btnE6.setRequestFocusEnabled(false);
		btnE6.setBounds(590, 270, 60, 240);
		frame.getContentPane().add(btnE6);
		btnE6.addActionListener(this);
		
		btnF6 = new JButton("f6");
		btnF6.setForeground(Color.WHITE);
		btnF6.setRequestFocusEnabled(false);
		btnF6.setBounds(650, 270, 60, 240);
		frame.getContentPane().add(btnF6);
		btnF6.addActionListener(this);
		
		btnG6 = new JButton("g6");
		btnG6.setForeground(Color.WHITE);
		btnG6.setRequestFocusEnabled(false);
		btnG6.setBounds(710, 270, 60, 240);
		frame.getContentPane().add(btnG6);
		btnG6.addActionListener(this);
		
		btnA6 = new JButton("a6");
		btnA6.setForeground(Color.WHITE);
		btnA6.setRequestFocusEnabled(false);
		btnA6.setBounds(770, 270, 60, 240);
		frame.getContentPane().add(btnA6);
		btnA6.addActionListener(this);
		
		btnB6 = new JButton("b6");
		btnB6.setForeground(Color.WHITE);
		btnB6.setRequestFocusEnabled(false);
		btnB6.setBounds(830, 270, 60, 240);
		frame.getContentPane().add(btnB6);
		btnB6.addActionListener(this);
		
		btnC7 = new JButton("c7");
		btnC7.setForeground(Color.WHITE);
		btnC7.setRequestFocusEnabled(false);
		btnC7.setBounds(890, 270, 60, 240);
		frame.getContentPane().add(btnC7);
		btnC7.addActionListener(this);
		

		
		
	//Labels at the top
		
		
		JLabel titleLbl = new JLabel("Virtual Piano");
		titleLbl.setFont(new Font("Kokonor", Font.PLAIN, 29));
		titleLbl.setForeground(UIManager.getColor("Button.highlight"));
		titleLbl.setBounds(50, 6, 220, 47);
		frame.getContentPane().add(titleLbl);
		
		JLabel sampleLbl = new JLabel("Sampled from Yamaha C7 Grand Piano");
		sampleLbl.setFont(new Font("Kokonor", Font.PLAIN, 18));
		sampleLbl.setForeground(Color.WHITE);
		sampleLbl.setBounds(50, 46, 415, 30);
		frame.getContentPane().add(sampleLbl);
		
	// radio button commands	
		onNote = new JRadioButton("Show Note Labels", true);
		onNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblC5Key.setVisible(true);
				lblD5Key.setVisible(true);
				lblE5Key.setVisible(true);
				lblF5Key.setVisible(true);
				lblG5Key.setVisible(true);
				lblA5Key.setVisible(true);
				lblB5Key.setVisible(true);
				lblC6Key.setVisible(true);
				lblD6Key.setVisible(true);
				lblE6Key.setVisible(true);
				lblF6Key.setVisible(true);
				lblG6Key.setVisible(true);
				lblA6Key.setVisible(true);
				lblB6Key.setVisible(true);
				lblC7Key.setVisible(true);	
				lblCSharp5key.setVisible(true);
				lblDSharp5key.setVisible(true);
				lblFSharp5key.setVisible(true);
				lblGSharp5key.setVisible(true);
				lblASharp5key.setVisible(true);
				lblCSharp6key.setVisible(true);
				lblDSharp6key.setVisible(true);
				lblFSharp6key.setVisible(true);
				lblGSharp6key.setVisible(true);
				lblASharp6key.setVisible(true);
			}
			
		});
		onNote.setForeground(Color.WHITE);
		onNote.setSize(170, 25);
		onNote.setLocation(50, 210);
		onNote.setRequestFocusEnabled(false);
		
		
		offNote = new JRadioButton("Hide Note Labels", false);
		offNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblC5Key.setVisible(false);
				lblD5Key.setVisible(false);
				lblE5Key.setVisible(false);
				lblF5Key.setVisible(false);
				lblG5Key.setVisible(false);
				lblA5Key.setVisible(false);
				lblB5Key.setVisible(false);
				lblC6Key.setVisible(false);
				lblD6Key.setVisible(false);
				lblE6Key.setVisible(false);
				lblF6Key.setVisible(false);
				lblG6Key.setVisible(false);
				lblA6Key.setVisible(false);
				lblB6Key.setVisible(false);
				lblC7Key.setVisible(false);	
				lblCSharp5key.setVisible(false);
				lblDSharp5key.setVisible(false);
				lblFSharp5key.setVisible(false);
				lblGSharp5key.setVisible(false);
				lblASharp5key.setVisible(false);
				lblCSharp6key.setVisible(false);
				lblDSharp6key.setVisible(false);
				lblFSharp6key.setVisible(false);
				lblGSharp6key.setVisible(false);
				lblASharp6key.setVisible(false);
			}
		});
		offNote.setSize(170, 25);
		offNote.setForeground(Color.WHITE);
		offNote.setLocation(50, 230);
		offNote.setRequestFocusEnabled(false);
		
		onKeyboard= new JRadioButton("Show Keyboard Labels", true);
		onKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblC5board.setVisible(true);
				lblD5Board.setVisible(true);
				lblE5board.setVisible(true);
				lblF5board.setVisible(true);
				lblG5board.setVisible(true);
				lblA5Board.setVisible(true);
				lblB5board.setVisible(true);
				lblC6board.setVisible(true);
				lblD6board.setVisible(true);
				lblE6board.setVisible(true);
				lblF6board.setVisible(true);
				lblG6board.setVisible(true);
				lblA6board.setVisible(true);
				lblB6board.setVisible(true);
				lblC7board.setVisible(true);
				lblCSharp5board.setVisible(true);
				lblDSharp5board.setVisible(true);
				lblFSharp5board.setVisible(true);
				lblGSharp5board.setVisible(true);
				lblASharp5board.setVisible(true);
				lblCSharp6board.setVisible(true);
				lblDSharp6board.setVisible(true);
				lblFSharp6board.setVisible(true);
				lblGSharp6board.setVisible(true);
				lblASharp6board.setVisible(true);
				
			}
		});
		onKeyboard.setForeground(Color.WHITE);
		onKeyboard.setSize(200, 25);
		onKeyboard.setLocation(210, 210);
		onKeyboard.setRequestFocusEnabled(false);
		
		
		offKeyboard = new JRadioButton("Hide Keyboard Labels", false);
		offKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblC5board.setVisible(false);
				lblD5Board.setVisible(false);
				lblE5board.setVisible(false);
				lblF5board.setVisible(false);
				lblG5board.setVisible(false);
				lblA5Board.setVisible(false);
				lblB5board.setVisible(false);
				lblC6board.setVisible(false);
				lblD6board.setVisible(false);
				lblE6board.setVisible(false);
				lblF6board.setVisible(false);
				lblG6board.setVisible(false);
				lblA6board.setVisible(false);
				lblB6board.setVisible(false);
				lblC7board.setVisible(false);
				lblCSharp5board.setVisible(false);
				lblDSharp5board.setVisible(false);
				lblFSharp5board.setVisible(false);
				lblGSharp5board.setVisible(false);
				lblASharp5board.setVisible(false);
				lblCSharp6board.setVisible(false);
				lblDSharp6board.setVisible(false);
				lblFSharp6board.setVisible(false);
				lblGSharp6board.setVisible(false);
				lblASharp6board.setVisible(false);
				
				
			}
		});
		offKeyboard.setForeground(Color.WHITE);
		offKeyboard.setSize(170, 25);
		offKeyboard.setLocation(210, 230);
		offKeyboard.setRequestFocusEnabled(false);
		
		frame.getContentPane().add(onNote);
		frame.getContentPane().add(offNote);
		frame.getContentPane().add(onKeyboard);
		frame.getContentPane().add(offKeyboard);
		
	// create group for the two radiobuttons	
		noteGroup = new ButtonGroup();
		noteGroup.add(onNote);
		noteGroup.add(offNote);
		
		keyboardGroup = new ButtonGroup();
		keyboardGroup.add(onKeyboard);
		keyboardGroup.add(offKeyboard);
		
		JLabel craftedLbl = new JLabel("Crafted By: Anmol Tyagi");
		craftedLbl.setForeground(Color.WHITE);
		craftedLbl.setFont(new Font("Kokonor", Font.PLAIN, 15));
		craftedLbl.setBounds(50, 77, 231, 30);
		frame.getContentPane().add(craftedLbl);
		
	}
	// fetching the sound 
	public class SoundEffect{
		
		Clip clip;
		
		public void setFile(String soundFileName) {
			
			try
			{
				File file = new File(soundFileName);
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(sound);
				
			}
			catch (Exception e)
			{
				System.out.println("not working");
				
			}
			
			
		}
		public void play() {
			
			clip.setFramePosition(0);
			clip.start();
			
			
		}
		 
		public void stop() {
			clip.stop();
			
		}
		
	}
	
	

		

// events for mouse clicking on the buttons	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("c5")) {
			se.setFile(soundsArray[C5SOUND]);
			se.play();

		}
		if (e.getActionCommand().equals("d5")) {
			se.setFile(soundsArray[D5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("e5")) {
			se.setFile(soundsArray[E5SOUND]);
			se.play();
			
		}
		if (e.getActionCommand().equals("f5")) {
			se.setFile(soundsArray[F5SOUND]);
			se.play();
			
		}	
		if (e.getActionCommand().equals("g5")) {
			se.setFile(soundsArray[G5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("a5")) {
			se.setFile(soundsArray[A5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("b5")) {
			se.setFile(soundsArray[B5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("c6")) {
			se.setFile(soundsArray[C6SOUND]);
			se.play();
			
		}
		if (e.getActionCommand().equals("d6")) {
			se.setFile(soundsArray[D6SOUND]);
			se.play();
			
		}
		if (e.getActionCommand().equals("e6")) {
			se.setFile(soundsArray[E6SOUND]);
			se.play();
			
		}
		if (e.getActionCommand().equals("f6")) {
			se.setFile(soundsArray[F6SOUND]);
			se.play();
			
		}
		if (e.getActionCommand().equals("g6")) {
			se.setFile(soundsArray[G6SOUND]);
			se.play();
			
		}
		if (e.getActionCommand().equals("a6")) {
			se.setFile(soundsArray[A6SOUND]);
			se.play();
			
		}
		if (e.getActionCommand().equals("b6")) {
			se.setFile(soundsArray[B6SOUND]);
			se.play();		
		}
		if (e.getActionCommand().equals("c7")) {
			se.setFile(soundsArray[C7SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("c#5")) {
			se.setFile(soundsArray[CSHARP5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("d#5")) {
			se.setFile(soundsArray[DSHARP5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("f#5")) {
			se.setFile(soundsArray[FSHARP5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("g#5")) {
			se.setFile(soundsArray[GSHARP5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("a#5")) {
			se.setFile(soundsArray[ASHARP5SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("c#6")) {
			se.setFile(soundsArray[CSHARP6SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("d#6")) {
			se.setFile(soundsArray[DSHARP6SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("f#6")) {
			se.setFile(soundsArray[FSHARP6SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("g#6")) {
			se.setFile(soundsArray[GSHARP6SOUND]);
			se.play();
		}
		if (e.getActionCommand().equals("a#6")) {
			se.setFile(soundsArray[ASHARP6SOUND]);
			se.play();
		}
		
	}
	
	
	// when the key is pressed on keyboard, play the notes that are pressed
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			 if (0 <= key && key <= 100000) {
			        if (keysDown[key]) return;
			        keysDown[key] = true;
			    }
			
			
			if (e.getKeyCode() == KeyEvent.VK_Z) {
				se.setFile(soundsArray[C5SOUND]);
				se.play();
				c5circle.setVisible(true);
				System.out.println("C5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_X) {
				se.setFile(soundsArray[D5SOUND]);
				se.play();
				d5circle.setVisible(true);
				System.out.println("D5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_C) {
				se.setFile(soundsArray[E5SOUND]);
				se.play();
				e5circle.setVisible(true);
				System.out.println("E5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_V) {
				se.setFile(soundsArray[F5SOUND]);
				se.play();
				f5circle.setVisible(true);
				System.out.println("F5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_B) {
				se.setFile(soundsArray[G5SOUND]);
				se.play();
				g5circle.setVisible(true);
				System.out.println("G5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_N) {
				se.setFile(soundsArray[A5SOUND]);
				se.play();
				a5circle.setVisible(true);
				System.out.println("A5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_M) {
				se.setFile(soundsArray[B5SOUND]);
				se.play();
				b5circle.setVisible(true);
				System.out.println("B5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_S) {
				se.setFile(soundsArray[CSHARP5SOUND]);
				se.play();
				csharp5circle.setVisible(true);
				System.out.println("C#5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_D) {
				se.setFile(soundsArray[DSHARP5SOUND]);
				se.play();
				dsharp5circle.setVisible(true);
				System.out.println("D#5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_G) {
				se.setFile(soundsArray[FSHARP5SOUND]);
				se.play();
				fsharp5circle.setVisible(true);
				System.out.println("F#5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_H) {
				se.setFile(soundsArray[GSHARP5SOUND]);
				se.play();
				gsharp5circle.setVisible(true);
				System.out.println("G#5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_J) {
				se.setFile(soundsArray[ASHARP5SOUND]);
				se.play();
				asharp5circle.setVisible(true);
				System.out.println("A#5 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_Q) {
				se.setFile(soundsArray[C6SOUND]);
				se.play();
				c6circle.setVisible(true);
				System.out.println("C6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_W) {
				se.setFile(soundsArray[D6SOUND]);
				se.play();
				d6circle.setVisible(true);
				System.out.println("D6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_E) {
				se.setFile(soundsArray[E6SOUND]);
				se.play();
				e6circle.setVisible(true);
				System.out.println("E6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_R) {
				se.setFile(soundsArray[F6SOUND]);
				se.play();
				f6circle.setVisible(true);
				System.out.println("F6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_T) {
				se.setFile(soundsArray[G6SOUND]);
				se.play();
				g6circle.setVisible(true);
				System.out.println("G6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_Y) {
				se.setFile(soundsArray[A6SOUND]);
				se.play();
				a6circle.setVisible(true);
				System.out.println("A6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_U) {
				se.setFile(soundsArray[B6SOUND]);
				se.play();
				b6circle.setVisible(true);
				System.out.println("B6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_I) {
				se.setFile(soundsArray[C7SOUND]);
				se.play();
				c7circle.setVisible(true);
				System.out.println("C7 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_2) {
				se.setFile(soundsArray[CSHARP6SOUND]);
				se.play();
				csharp6circle.setVisible(true);
				System.out.println("C#6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_3) {
				se.setFile(soundsArray[DSHARP6SOUND]);
				se.play();
				dsharp6circle.setVisible(true);
				System.out.println("D#6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_5) {
				se.setFile(soundsArray[FSHARP6SOUND]);
				se.play();
				fsharp6circle.setVisible(true);
				System.out.println("F#6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_6) {
				se.setFile(soundsArray[GSHARP6SOUND]);
				se.play();
				gsharp6circle.setVisible(true);
				System.out.println("G#6 played");
			}
			if (e.getKeyCode()== KeyEvent.VK_7) {
				se.setFile(soundsArray[ASHARP6SOUND]);
				se.play();
				asharp6circle.setVisible(true);
				System.out.println("A#6 played");
			}
	
			
		}
		// when the key is released, stop showing the circles that indicate that the key is being pressed
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
		    if (0 <= key && key <= 100000) {
		        if (!keysDown[key]) return;
		        keysDown[key] = false;
		    }
		    
		    if (e.getKeyCode() == KeyEvent.VK_Z) {
		    	c5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_X) {
		    	d5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_C) {
		    	e5circle.setVisible(false);
		    }
		    if (e.getKeyCode()== KeyEvent.VK_V) {
		    	f5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_B) {
		    	g5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_N) {
		    	a5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_M) {
		    	b5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_Q) {
		    	c6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_W) {
		    	d6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_E) {
		    	e6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_R) {
		    	f6circle.setVisible(false);	
		    }
		    if (e.getKeyCode() == KeyEvent.VK_T) {
		    	g6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_Y) {
		    	a6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_U) {
		    	b6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_I) {
		    	c7circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_S) {
		    	csharp5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_D) {
		    	dsharp5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_G) {
		    	fsharp5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_H) {
		    	gsharp5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_J) {
		    	asharp5circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_2) {
		    	csharp6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_3) {
		    	dsharp6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_5) {
		    	fsharp6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_6) {
		    	gsharp6circle.setVisible(false);
		    }
		    if (e.getKeyCode() == KeyEvent.VK_7) {
		    	asharp6circle.setVisible(false);
		    }
		}
		@Override
		public void keyTyped(KeyEvent e) {

			
			
		}
		

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
