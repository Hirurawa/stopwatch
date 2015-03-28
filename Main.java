package stopwatch;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Main {
	
static int secs = 120;
static Timer timer;

static int screenSize_x = 500;
static int screenSize_y = 250;

public static void main(String[] args) {
	
	final JFrame frame = new JFrame("Stopwatch");
	frame.getContentPane().setBackground(Color.WHITE);
	frame.setLayout(null);
	frame.setVisible(true);
	frame.setResizable(false);
	Insets insets = frame.getInsets();
	Dimension size;
	frame.setSize(screenSize_x + insets.left + insets.right, screenSize_y + insets.top + insets.bottom);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

	final JLabel time = new JLabel(""+ secs);
	time.setFont(new Font("Times New Roman", Font.PLAIN, 120));
	frame.add(time);
	size = time.getPreferredSize();
	time.setBounds(40 + insets.left, 70 + insets.top, size.width, size.height);
	
	JRadioButton hosszu = new JRadioButton("240");
	JRadioButton rovid = new JRadioButton("120");
	ButtonGroup group = new ButtonGroup();
	group.add(rovid);
	group.add(hosszu);
	size = rovid.getPreferredSize();
	rovid.setBounds(350 + insets.left, 100 + insets.top, size.width, size.height);
	size = hosszu.getPreferredSize();
	hosszu.setBounds(350 + insets.left, 150 + insets.top, size.width, size.height);
	rovid.setSelected(true);
	
    final int delay = 1000;
    final int period = 1000;
    timer = new Timer();	
	
	final TimerTask task = new TimerTask() {
		@Override
		public void run() {
			if(secs == 1){
				timer.cancel();
			}
			secs--;
            time.setText(""+ secs);
            if(secs <= 117){
            	frame.getContentPane().setBackground(Color.BLACK);
            	time.setForeground(Color.YELLOW);
            }
		}};
		
		rovid.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				secs = 120;
				time.setText("" + secs);
			}
		});
		
		hosszu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				secs = 240;
				time.setText("" + secs);
			}	
		});
		
	
	final JButton start = new JButton("Start");
	final JButton reset = new JButton("Reset");
	size = reset.getPreferredSize();
	reset.setBounds(250 + insets.left, 150 + insets.top, size.width, size.height);
	reset.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			secs = 120;
			timer.cancel();
			time.setText("" + secs);
		}
	});
	
	size = start.getPreferredSize();
	start.setBounds(250 + insets.left, 100 + insets.top, size.width, size.height);
	start.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.schedule(task, delay, period);
		}
		
	});
	
	frame.add(rovid);
	frame.add(hosszu);
	frame.add(start);
	frame.add(reset);
	frame.repaint();
	}
}
