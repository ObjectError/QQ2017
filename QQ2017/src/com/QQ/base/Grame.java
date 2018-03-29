package com.QQ.base;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;



@SuppressWarnings("serial")
public class Grame extends JFrame implements ActionListener,ItemListener,MouseListener,WindowListener{
	JButton clbutton,hybbutton,dygbutton,sygbutton,xygbutton,zzgbutton,xgbutton,yybutton,yykgbutton;
	JLabel yylabel;
	JComboBox yybox;
	ButtonGroup all;
	JMenuBar bar;
	JMenu oping,set,help;
	JMenuItem back,reset,last,next,choice,exit;
	JRadioButtonMenuItem nor,popo,guang,qin,eyes;
	 JMenuItem about,yxbj;
	mbpanel mb;	
	
	Sound sound;
	
	static int level = 1;
	
	String[] music = {
			"卡农",
			"潮鳴り",
			"这个是僵尸吗",
			"U.N简单版",
			"the fox and the grapes"
			   
	   };
	String sMusicFile[]={
			"nor.mid",
			"qin.mid",
			"popo.mid",
			"guang.mid",
			"eyes on me.mid"
		};
	
	public Grame() {
		   Toolkit t = Toolkit.getDefaultToolkit();
		   Image iconImage = t.getImage("images2/shime44.png");
		   this.setIconImage(iconImage);
		   this.setTitle("   死亡空间");
		   
		   Container c = getContentPane();
		   c.setLayout(null);
		   
		   cjbutton(c);
		   cjcd();
		   
		   mb = new mbpanel();  
		   mb.setBounds(0,60,600,600);
		   
		   JLabel titleLabel = new JLabel("逃出死亡空间",JLabel.CENTER);
		   titleLabel.setBounds(240,20,200,30);
		   titleLabel.setFont(new Font("微软雅黑",Font.ITALIC,16));
		   
		   c.add(mb);
		   c.add(titleLabel);
		   c.setBackground(Color.lightGray);
		   
		   sound = new Sound();
		   sound.loadSound();

		   	   
		setVisible(true);
		setSize(720,720);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setDefaultLookAndFeelDecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		c.addMouseListener(this);
		mb.requestFocus();
		
	}
	public void cjbutton(Container c){
		clbutton = new JButton("重来");
		hybbutton = new JButton("悔一步");
		dygbutton = new JButton("第一关");
		sygbutton = new JButton("上一关");
		xygbutton = new JButton("下一关");
		xgbutton = new JButton("选关");
		zzgbutton = new JButton("最后关");
		yybutton = new JButton("音乐");
		yykgbutton = new JButton("音乐关");
		
		
		yylabel = new JLabel("选择音乐"); 
		yybox = new JComboBox(music);
		
		
		 clbutton.setBounds(610,80, 80, 30);
		 hybbutton.setBounds(610,130, 80, 30);
		 dygbutton.setBounds(610,180, 80, 30);
		 sygbutton.setBounds(610,230, 80, 30);
		 xygbutton.setBounds(610,280, 80, 30);
		 xgbutton.setBounds(610,330, 80, 30);
		 zzgbutton.setBounds(610,380, 80, 30);
		 yybutton.setBounds(610,430, 80, 30);
		 yykgbutton.setBounds(610,445,80,30);
		 yylabel.setBounds(610,480, 80, 20);
		 yybox.setBounds(610,500, 100, 30);
		 
		 
		 clbutton.setBackground(Color.pink);
		 hybbutton.setBackground(Color.lightGray);
		 dygbutton.setBackground(Color.yellow);
		 sygbutton.setBackground(Color.lightGray);
		 xygbutton.setBackground(Color.lightGray);
		 xgbutton.setBackground(Color.cyan);
		 zzgbutton.setBackground(Color.lightGray);
		 yybutton.setBackground(Color.lightGray);
		 yykgbutton.setBackground(Color.orange);

		 
		 c.add(clbutton);
		 c.add(hybbutton);
		 c.add(dygbutton);
		 c.add(sygbutton);
		 c.add(xygbutton);
		 c.add(xgbutton);
		 c.add(zzgbutton);
		 c.add(yykgbutton);
		 c.add(yylabel);
		 c.add(yybox);

	}
	public void cjcd(){
		   bar = new JMenuBar();
		   oping = new JMenu("选项");
		   
		   help = new JMenu("帮助");
		   
		   back = new JMenuItem("悔一步");
		   reset = new JMenuItem("重置");
		   last = new JMenuItem("上一关");
		   next = new JMenuItem("下一关");
		   choice = new JMenuItem("选关");
		   exit = new JMenuItem("退出");
		   
		   set = new JMenu("设置");
		   
		   nor = new JRadioButtonMenuItem(music[0]);
		   popo = new JRadioButtonMenuItem(music[1]);
		   guang = new JRadioButtonMenuItem(music[2]);
		   qin = new JRadioButtonMenuItem(music[3]);
		   eyes = new JRadioButtonMenuItem(music[4]);
		   
			setMenuState(0);
			set.add(nor);
			set.add(popo);
			set.add(guang);
			set.add(qin);
			set.add(eyes);
			
			
		   about = new JMenuItem("关于我们");
		   yxbj = new JMenuItem("游戏背景");
		   
		   oping.add(back);
		   oping.add(reset);
		   oping.add(last);
		   oping.add(next);
		   oping.add(choice);
		   oping.add(exit);
		 
		   all = new ButtonGroup();
		  
		   all.add(nor);
		   all.add(popo);
		   all.add(guang);
		   all.add(qin);
		   all.add(eyes);
		   
		   set.add(nor);
		   set.add(popo);
		   set.add(guang);
		   set.add(qin);
		   set.add(eyes);
		   
		   
		   help.add(about);
		   help.add(yxbj);
		   
		   bar.add(oping);
		   bar.add(set);
		   bar.add(help);
		   
		   setJMenuBar(bar); 
		   
		   sygbutton.addActionListener(this);
		   xygbutton.addActionListener(this);
		   xgbutton.addActionListener(this);
		   exit.addActionListener(this);
		   about.addActionListener(this);
		   zzgbutton.addActionListener(this);
		   dygbutton.addActionListener(this);
		   choice.addActionListener(this);
		   last.addActionListener(this);
		   next.addActionListener(this);
		   hybbutton.addActionListener(this);
		   yxbj.addActionListener(this);
		   clbutton.addActionListener(this);
		   back.addActionListener(this);
		   reset.addActionListener(this);
		  
		   yybox.addItemListener(this);
		  
		   yykgbutton.addActionListener(this);
		 
		   nor.addActionListener(this);
		   popo.addActionListener(this);
		   guang.addActionListener(this);
		   qin.addActionListener(this);
		   eyes.addActionListener(this);
		   
	}

	public static void main(String[] args) {
		new Grame();
		
	}
	public void setMenuState(int index){
		switch(index){
		case 0:
			nor.setSelected(true);
			nor.setEnabled(false);
			popo.setEnabled(true);
			guang.setEnabled(true);
			qin.setEnabled(true);
			eyes.setEnabled(true);
			
			break;
		case 1:
			popo.setSelected(true);
			popo.setEnabled(false);
			nor.setEnabled(true);
			guang.setEnabled(true);
			qin.setEnabled(true);
			eyes.setEnabled(true);
			break;
		case 2:
			guang.setSelected(true);
			guang.setEnabled(false);
			nor.setEnabled(true);
			popo.setEnabled(true);
			qin.setEnabled(true);
			eyes.setEnabled(true);
			break;
		case 3:
			qin.setSelected(true);
			qin.setEnabled(false);
			nor.setEnabled(true);
			guang.setEnabled(true);
			popo.setEnabled(true);
			eyes.setEnabled(true);
			break;
		case 4:
			eyes.setSelected(true);
			eyes.setEnabled(false);
			nor.setEnabled(true);
			guang.setEnabled(true);
			popo.setEnabled(true);
			qin.setEnabled(true);
			break;
		}

	}
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==sygbutton || e.getSource()==last){
			mb.sygLevel();
		
		}else if(e.getSource()==xygbutton || e.getSource()==next){
			mb.xygLevel();
		
		}else if(e.getSource()==xgbutton ||e.getSource()==choice){
			
			String str = JOptionPane.showInputDialog("请输入您要选择的关，1-50");
			if(str==null){return;};
			try{
					
					level = new Integer(str).intValue();
			}catch(Exception e2){
					JOptionPane.showMessageDialog(this,"请输入1-50之间的数字");
					return;
			}
			if(level>50 || level<1){
					JOptionPane.showMessageDialog(this,"您选择的关不存在，请输入1-50之间的数字");
					return;
			}  
			mb.xglevel();
			
		}else if(e.getSource()==exit){
			System.exit(0);
		}else if(e.getSource()==about){
			String str = "作者:无聊的程序员\n";
			str += "Q Q:321244572\n";
			str += "版本:逃出危险世界\n";
			str += "游戏攻略\n";
			str += "用冰块冻住炸弹即可过关\n";
			str += "更多:www.Gramebox.com\n";
			JOptionPane.showMessageDialog(this,str,"帮助",JOptionPane.INFORMATION_MESSAGE);
			

		}else if(e.getSource()==zzgbutton){
			mb.zzglevel();
		
		}else if(e.getSource()==dygbutton){
			mb.dyglevel();
			
		}
		else if(e.getSource()==hybbutton ||e.getSource()==back){
			mb.goBack();
			
		}
		else if(e.getSource()==yxbj){
			String str1 = "在很久以前,有一位探险者,\n";
			str1 += "有一天他来到一个充满危险的世界里\n";
			str1 +="他发现了一个可以逃出这个世界的方法\n";
			str1 += "但是这里到处充满着炸弹,淘宝者利用地上的冰块冻住炸弹\n";
			str1 += "当通完这50关后\n";
			str1 += "他发现................\n";		
			JOptionPane.showMessageDialog(this,str1,"背景介绍",JOptionPane.INFORMATION_MESSAGE);
			
		}
		else if(e.getSource()==clbutton  || e.getSource()==reset){
				mb.cllevel();
				
		}
		else if(e.getSource() == yykgbutton){
			String title = yykgbutton.getText();
			if(title.equals("音乐关")){
				if(sound.isplay()){
					sound.mystop();
				}
				yykgbutton.setText("音乐开");
			}else{
				yykgbutton.setText("音乐关");
				sound.loadSound();
				mb.requestFocus();
			}
		}else if(e.getSource()== nor){
			yybox.setSelectedIndex(0);
		}else if(e.getSource()== popo){
			yybox.setSelectedIndex(1);
		}else if(e.getSource()== guang){
			yybox.setSelectedIndex(2);
		}else if(e.getSource()== qin){
			yybox.setSelectedIndex(3);
		}else if(e.getSource()== eyes){
			yybox.setSelectedIndex(4);
		}
		mb.requestFocus();
	}

class mbpanel extends JPanel implements KeyListener{
	int[][] map;
	
	int[][] tdmap;
	
	int rx,ry;
	
	
	Toolkit t = Toolkit.getDefaultToolkit();

	Image[] images = {
			t.getImage("pic/0.gif"),
			t.getImage("pic/1.gif"),
			t.getImage("images2/2.gif"),
			t.getImage("images2/3.gif"),
			t.getImage("images2/4.gif"),
			t.getImage("pic/5.gif"),
			t.getImage("pic/6.gif"),
			t.getImage("pic/7.gif"),
			t.getImage("pic/8.gif"),
			t.getImage("images2/9.gif")
	};
	
	Stack<Integer> stack = new Stack<Integer>();
	
	mbpanel(){
		setSize(600,600);
		read(level);
		this.addKeyListener(this);	
		
	}

	public void paint(Graphics g){
		for(int i=0;i<20;i++){
			for(int s=0;s<20;s++){
					g.drawImage(images[tdmap[s][i]],i*30,s*30,30,30,this);
					
			}	
		}
		g.setColor(Color.cyan);
		g.setFont(new Font("楷体",Font.ITALIC,60));
		g.drawString("现在是第" +level + "关", 140, 100);
	}
	public Boolean isWin(){
		Boolean win = true;
		for(int i=0;i<20;i++){
			for(int j=0;j<20;j++){
				if(tdmap[j][i]!=9 && map[j][i]==4){
					win = false;
					break;
				}
			}  
			if(!win){
				break;
			}
		}
		return win;
	}
	
public void goBack(){
	if(stack.isEmpty()){
		JOptionPane.showMessageDialog(this,"已经退到了最开始,不能退了");
		return;
	}
	int i = stack.pop();
	
	switch (i) {
		case 1:
		case 11:
			backshang(i);	
			break;
		
		case 2:
		case 22:
			backxia(i);
			break;
		case 3:
		case 33:
			backzuo(i);
			break;
		case 4:
		case 44:
			backyou(i);
			break;
	}
}
public void backshang(int i){
	if(i==1){
		tdmap[rx+1][ry] = 8;
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 4;
		}else{
			tdmap[rx][ry] = 2;
		}
		rx++;
	}

	else{
		tdmap[rx+1][ry] = 8;
	
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 9;
		}else{
			tdmap[rx][ry] = 3;
		}
		
		if(map[rx-1][ry]==4||map[rx-1][ry]==9){
			tdmap[rx-1][ry]= 4;
		}else{
			tdmap[rx-1][ry]= 2;
		}
		rx++;
	}
	repaint(); 
	
}
public void backxia(int i){
	if(i==2){
		tdmap[rx-1][ry] = 5;
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 4;
		}else{
			tdmap[rx][ry] = 2;
		}
		rx--;
	}
	else{
		tdmap[rx-1][ry] = 5;
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 9;
		}else{
			tdmap[rx][ry] = 3;
		}
		if(map[rx+1][ry]==4 || map[rx+1][ry]==9){
			tdmap[rx+1][ry] = 4;
		}else{
			tdmap[rx+1][ry] = 2;
		}
		rx--;
	}
	repaint();
}
public void backzuo(int i){
	if(i==3){
		tdmap[rx][ry+1] = 6;
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 4;
		}else{
			tdmap[rx][ry] = 2;
		}
		ry++;
	}
	else{
		tdmap[rx][ry+1] = 6;
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 9;
		}else{
			tdmap[rx][ry] = 3;
		}
		if(map[rx][ry-1]==4 || map[rx][ry-1]==9){
			tdmap[rx][ry-1] = 4;
		}else{
			tdmap[rx][ry-1] = 2;
		}
		ry++;
	}
	repaint();
}
public void backyou(int i){
	if(i==4){
		tdmap[rx][ry-1] = 7;
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 4;
		}else{
			tdmap[rx][ry] = 2;
		}
		ry--;
	}
	else{
		tdmap[rx][ry-1] = 7;
		if(map[rx][ry]==4 || map[rx][ry]==9){
			tdmap[rx][ry] = 9;
		}else{
			tdmap[rx][ry] = 3;
		}
		if(map[rx][ry+1]==4 || map[rx][ry+1]==9){
			tdmap[rx][ry+1] = 4;
		}else{
			tdmap[rx][ry+1] = 2;
		}
		ry--;
	}
	repaint();
}
public void read(int level){
		dqmaps r= new dqmaps(level);
		r.readmaps();
		map = r.getMap();
		
		dqmaps r2= new dqmaps(level);
		
		r2.readmaps();
		
		tdmap = r2.getMap();
		
		rx = r.getMx();
		ry = r.getMy();
		
		repaint();
	}
	public void xygLevel(){
		level = level + 1;
		if(level>50){
			JOptionPane.showMessageDialog(this,"你太厉害了,把关卡的过掉了,所以再来一次吧");
			level = 1;
		}  
		read(level);
		stack.clear();
	}
	public void sygLevel(){
		level = level-1;
		if(level<1){
			JOptionPane.showMessageDialog(this,"这已经是第一关了哦");
		
			level = 1;
		}
		read(level);
		
	}
	public void zzglevel(){
		level = 50;
		read(level);
	}
	public void cllevel(){
		
		read(level);
	}
	public void xglevel(){
		
		int i = level;
		read(i);
		
	}
	public void dyglevel(){
		level = 1;
		read(level);
	}
	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==37){
			//mb.requestFocus();
			zuo();
			
		}
		else if(e.getKeyCode()==38){
			//mb.requestFocus();
			shang();
			
		}
		else if(e.getKeyCode()==39){
			//mb.requestFocus();
			you();
		}
		else if(e.getKeyCode()==40){
			//mb.requestFocus();
			xia();
		}else if(e.getKeyCode()==27){
			System.exit(0);
		}
		if(isWin()){
			JOptionPane.showMessageDialog(this,"恭喜通过");
			this.xygLevel();  
		}
		
	}
		public void shang(){
			
			if(tdmap[rx-1][ry]==3 || tdmap[rx-1][ry]==9){
				
				if(tdmap[rx-2][ry]==4 || tdmap[rx-2][ry]==2){
					
					if(tdmap[rx-2][ry]==4){
						tdmap[rx-2][ry] =9;
						tdmap[rx-1][ry] =8;
					}
					
					if(tdmap[rx-2][ry]==2){
						tdmap[rx-2][ry] =3;
						tdmap[rx-1][ry] =8;
					}
					
					if(map[rx][ry]==4 || map[rx][ry]==9){
						tdmap[rx][ry] = 4;
					}
					
					else{
						tdmap[rx][ry] = 2;
					}
					rx--;
					stack.add(11);
				}
			}
			else if(tdmap[rx-1][ry]==2 || tdmap[rx-1][ry]==4){
					
					tdmap[rx-1][ry] = 8;
				
				if(map[rx][ry]==4 || map[rx][ry]==9 ){
					tdmap[rx][ry] = 4;
				}
				else{
					tdmap[rx][ry] = 2;
				}
					rx--;	
					stack.add(1);
			}
			repaint();
	
		}
		public void xia(){
		if(tdmap[rx+1][ry]==3 || tdmap[rx+1][ry]==9){
				
				if(tdmap[rx+2][ry]==4 || tdmap[rx+2][ry]==2){
					
					if(tdmap[rx+2][ry]==4){
						tdmap[rx+2][ry] =9;
						tdmap[rx+1][ry] =5;
					}
					
					if(tdmap[rx+2][ry]==2){
						tdmap[rx+2][ry] =3;
						tdmap[rx+1][ry] =5;
					}
					
					if(map[rx][ry]==4 || map[rx][ry]==9){
						tdmap[rx][ry] = 4;
					}
					
					else{
						tdmap[rx][ry] = 2;
					}
					rx++;
					stack.add(22);
					
				}
			}
			else if(tdmap[rx+1][ry]==2 || tdmap[rx+1][ry]==4){
					
					tdmap[rx+1][ry] = 6;
				
				if(map[rx][ry]==4 || map[rx][ry]==9 ){
					tdmap[rx][ry] = 4;
				}
				else{
					tdmap[rx][ry] = 2;
				}
					rx++;	
					stack.add(2);
			}
			repaint();
	
		}
		public void zuo(){
			if(tdmap[rx][ry-1]==3 || tdmap[rx][ry-1]==9){
				
				if(tdmap[rx][ry-2]==4 || tdmap[rx][ry-2]==2){
					
					if(tdmap[rx][ry-2]==4){
						tdmap[rx][ry-2] =9;
						tdmap[rx][ry-1] =6;
					}
					
					if(tdmap[rx][ry-2]==2){
						tdmap[rx][ry-2] =3;
						tdmap[rx][ry-1] =6;
					}
					
					if(map[rx][ry]==4 || map[rx][ry]==9){
						tdmap[rx][ry] = 4;
					}
					
					else{
						tdmap[rx][ry] = 2;
					}
					ry--;
					stack.add(33);
				}
			}
			else if(tdmap[rx][ry-1]==2 || tdmap[rx][ry-1]==4){
					
					tdmap[rx][ry-1] = 6;
				
				if(map[rx][ry]==4 || map[rx][ry]==9 ){
					tdmap[rx][ry] = 4;
				}
				else{
					tdmap[rx][ry] = 2;
				}
					ry--;
					stack.add(3);
			}
			repaint();
		}
		public void you(){
			if(tdmap[rx][ry+1]==3 || tdmap[rx][ry+1]==9){
				
				if(tdmap[rx][ry+2]==4 || tdmap[rx][ry+2]==2){
					
					if(tdmap[rx][ry+2]==4){
						tdmap[rx][ry+2] =9;
						tdmap[rx][ry+1] =7;
					}
					
					if(tdmap[rx][ry+2]==2){
						tdmap[rx][ry+2] =3;
						tdmap[rx][ry+1] =7;
					}
					
					if(map[rx][ry]==4 || map[rx][ry]==9){
						tdmap[rx][ry] = 4;
					}
					
					else{
						tdmap[rx][ry] = 2;
					}
					ry++;
					stack.add(44);
				}
			}
			else if(tdmap[rx][ry+1]==2 || tdmap[rx][ry+1]==4){
					
					tdmap[rx][ry+1] = 7;
				
				if(map[rx][ry]==4 || map[rx][ry]==9 ){
					tdmap[rx][ry] = 4;
				}
				else{
					tdmap[rx][ry] = 2;
				}
					ry++;
					stack.add(4);
			}
			repaint();
		}
}
class dqmaps{
	int map[][];
	int level;

	int rx,ry;

	public int getMx() {
		return rx;
	}

	public int getMy() {
		return ry;
	}  
	dqmaps(int level) {
		this.level = level;
	}
	public int[][] getMap() {
		return map;
	}
	public void readmaps(){
		try {
			FileReader fi = new FileReader("maps/"+level+".map");
			@SuppressWarnings("resource")
			BufferedReader bf = new BufferedReader(fi);
			String c = "";
			String t = "";
			
			while ((t=bf.readLine())!=null) {
				c = c + t;	
			}
			int d = 0;
			byte[] b = c.getBytes();
			
			map = new int[20][20];
			for(int i=0;i<20;i++){
				for(int y=0;y<20;y++){
					map[i][y] = b[d] -48;
					if(b[d]-48==5){
						rx = i;
						ry = y;
					}
					d++;
				}
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	public void itemStateChanged(ItemEvent e) {
	
		int index = yybox.getSelectedIndex();
		setMenuState(index);
		//利用索引，从数组中获取背景音乐文件名称
		String sfilename = sMusicFile[index];
		sound.setMusic(sfilename);
		//如果已经在播放背景音乐，则先停止播放
		if(sound.isplay()){
			sound.mystop();
		}
		//播放音乐
		sound.loadSound();

		}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1){
			//JOptionPane.showMessageDialog(this,"瞎点是不能通过关卡的,看帮助吧");
			mb.requestFocus();
		}if(e.getClickCount()==2){
			JOptionPane.showMessageDialog(this,"调戏屏幕是不能通过关卡的,看帮助吧");
			mb.requestFocus();
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		
		
	}
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	public void mousePressed(MouseEvent arg0) {
		
	}
	public void mouseReleased(MouseEvent arg0) {
		
		
	}
	public void windowActivated(WindowEvent arg0) {
		
	}
	public void windowClosed(WindowEvent arg0) {
		
	}
	public void windowClosing(WindowEvent arg0) {
		dispose();
	}
	public void windowDeactivated(WindowEvent arg0) {
		
	}
	public void windowDeiconified(WindowEvent arg0) {
		
	}
	public void windowIconified(WindowEvent arg0) {
		
	}
	public void windowOpened(WindowEvent arg0) {
		
	}
 }
class Sound {
	String path = new String("musics2\\"); 

	String file = new String("卡农.mid"); 

	Sequence seq; 

	Sequencer midi; 

	boolean sign;

	void loadSound() {
		try {
			seq = MidiSystem.getSequence(new File(path + file)); 
			midi = MidiSystem.getSequencer(); 
			midi.open(); 
			midi.setSequence(seq); 
			midi.start(); 
			midi.setLoopCount(Sequencer.LOOP_CONTINUOUSLY); 
		}
		catch ( Exception ex ) {
			ex.printStackTrace();
		}
		sign = true;
	}

	void mystop() { 
		midi.stop(); 
		midi.close();
		sign = false;
	}

	boolean isplay() { 
		return sign;
	}

	void setMusic(String e) { 
		file = e;
	}
}