package com.QQ.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class QQaboutUI extends JFrame implements MouseListener{
	
	@SuppressWarnings("unused")
	private JLabel lblBg,lbgb,lbtx,lbtx2;
	
	@SuppressWarnings("unused")
	private JTextArea taabout;
	
	private JTextArea taziti;
	
	public QQaboutUI(){
		
		setIconImage(new ImageIcon("images/tubiao.jpg").getImage());
		
		lblBg = new JLabel(new ImageIcon("MyImages/1.jpg"));
		
		add(lblBg);
		
		lblBg.setLayout(null);
		
		String str ="\tQQ2016\n";
		str +="---------------------\n";
		str +="QQ介绍:\n";
		str +="   一款经典的聊天软件\n";
		str +="---------------------\n";
		str +="软件功能\n";
		str +="   完美实现经典聊天功能\n";
		str +="---------------------\n";
		str +="关于我们\n";
		str +="www.MyQQ.com";
		
		taziti = new JTextArea(500,500);
		taziti.setText(str);
		taziti.setForeground(Color.CYAN);
		taziti.setFont(new Font("黑体",Font.BOLD,20));
		taziti.setBounds(100,20,300,300);
		taziti.setOpaque(false);
		taziti.setEditable(false);
		
		lblBg.add(taziti);
		
		lbtx = new JLabel(new ImageIcon("image/tubiao.png"));
		lbtx.setBounds(30,70,60,60);
		lbtx.setOpaque(false);
		lblBg.add(lbtx);
		
		
		lbgb = new JLabel("X");
		lbgb.setToolTipText("关闭");
		lbgb.setForeground(Color.RED);
		lbgb.setFont(new Font("黑体",Font.BOLD,30));
		
		lbgb.setBounds(400,0,20,20);
		
		lblBg.add(lbgb);
		
		lbgb.addMouseListener(this);
		lbtx.addMouseListener(this);
		
		setSize(427,321);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new QQaboutUI();
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==lbgb){
			dispose();
		}else if(e.getSource()==lbtx){
			JOptionPane.showMessageDialog(this,"宝宝欢迎你哦");
		}
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	
}
