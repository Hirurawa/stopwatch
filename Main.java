package stopwatch;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Main {

static int secs = 10;
static int ido;

static boolean beallas = true;

static int sv;
static int sorvege = 1;

static Timer timer;

static int screenSize_x = 650;
static int screenSize_y = 250;
static int setupSize_x = 400;
static int setupSize_y = 250;

public static void main(String[] args) {
	
	final JFrame setup = new JFrame("Setup");
	setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setup.setLayout(null);
	setup.setVisible(true);
	setup.setResizable(false);
	setup.setLocation(500, 500);
	Insets insets = setup.getInsets();
	Dimension size;
	setup.setSize(setupSize_x + insets.left + insets.right, setupSize_y + insets.top + insets.bottom);
	
	final JFrame watch = new JFrame("Stopwatch");
	watch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	watch.getContentPane().setBackground(Color.WHITE);
	watch.setLayout(null);
	watch.setVisible(true);
	watch.setResizable(false);
	insets = watch.getInsets();
	watch.setSize(screenSize_x + insets.left + insets.right, screenSize_y + insets.top + insets.bottom);
	
	final JLabel time = new JLabel("" + secs);
	time.setFont(new Font("Times New Roman", Font.PLAIN, 120));
	size = time.getPreferredSize();
	time.setBounds(40 + insets.left, 70 + insets.top, size.width + 75, size.height);

	final JRadioButton hosszu = new JRadioButton("6 vessző");
	final JRadioButton rovid = new JRadioButton("3 vessző");
	ButtonGroup vesszok = new ButtonGroup();
	vesszok.add(rovid);
	vesszok.add(hosszu);
	size = rovid.getPreferredSize();
	rovid.setBounds(250 + insets.left, 100 + insets.top, size.width, size.height);
	size = hosszu.getPreferredSize();
	hosszu.setBounds(250 + insets.left, 125 + insets.top, size.width, size.height);
	
	rovid.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			ido = 20;
			time.setText("" + secs);
		}
	});
	
	hosszu.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			ido = 240;
			time.setText("" + secs);
		}	
	});

	final JRadioButton terem = new JRadioButton("Terem");
	final JRadioButton kint = new JRadioButton("Kint");
	ButtonGroup hely = new ButtonGroup();
	hely.add(terem);
	hely.add(kint);
	size = terem.getPreferredSize();
	terem.setBounds(150 + insets.left, 100 + insets.top, size.width, size.height);
	size = kint.getPreferredSize();
	kint.setBounds(150 + insets.left, 125 + insets.top, size.width, size.height);
	
	final JRadioButton AB = new JRadioButton("AB");
	final JRadioButton ABCD = new JRadioButton("AB/CD");
	final JRadioButton ABCDEF = new JRadioButton("AB/CD/EF");
	ButtonGroup sor = new ButtonGroup();
	sor.add(AB);
	sor.add(ABCD);
	sor.add(ABCDEF);
	size = AB.getPreferredSize();
	AB.setBounds(50 + insets.left, 100 + insets.top, size.width, size.height);
	size = ABCD.getPreferredSize();
	ABCD.setBounds(50 + insets.left, 125 + insets.top, size.width, size.height);
	size = ABCDEF.getPreferredSize();
	ABCDEF.setBounds(50 + insets.left, 150 + insets.top, size.width, size.height);
	
	AB.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			sv = 1;
			sorvege = sv;
		}
	});
	ABCD.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			sv = 2;
			sorvege = sv;
		}
	});
	ABCDEF.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			sv = 3;
			sorvege = sv;
		}
	});
	
	
	JButton start = new JButton("Start");
	size = start.getPreferredSize();
	start.setBounds(250 + insets.left, 100 + insets.top, size.width, size.height);
	start.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.start();
		}
	});
	
	JButton stop = new JButton("Stop");
	size = stop.getPreferredSize();
	stop.setBounds(250 + insets.left, 150 + insets.top, size.width, size.height);
	stop.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
		}
	});
	
	final JButton done = new JButton("KÉSZ!");
	size = done.getPreferredSize();
	done.setBounds(25 + insets.left,  insets.top, size.width, size.height);
	done.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			rovid.setEnabled(false);
			hosszu.setEnabled(false);
			terem.setEnabled(false);
			kint.setEnabled(false);
			AB.setEnabled(false);
			ABCD.setEnabled(false);
			ABCDEF.setEnabled(false);
			done.setEnabled(false);
		}
	});
	
	timer = new Timer(250, new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			if(secs <= 1 && beallas){
				secs = ido + 1;
				beallas = false;
				sorvege --;
			}
			if(secs <= 1 && !beallas){
				secs = 10 + 1;
				beallas = true;
				if(sorvege == 0){
					timer.stop();
					sorvege = sv;
				}
			}
			secs--;
			time.setText("" + secs);
		}
	});
	
	setup.add(done);
	setup.add(AB);
	setup.add(ABCD);
	setup.add(ABCDEF);
	setup.add(kint);
	setup.add(terem);
	watch.add(start);
	watch.add(stop);
	watch.add(time);
	setup.add(rovid);
	setup.add(hosszu);
	watch.repaint();
	setup.repaint();
	}

}
