package com.QQ.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.QQ.beans.Account;

public class LookinfoUI extends JFrame implements MouseListener,MouseMotionListener{
	private JLabel lblQQcode,lblnickName,lblAge,lblSex,lbgb;
	private JLabel lblNation,lblStar,lblBlood,lblHobit,lblRemark;
	private JLabel lblHeadimg;

	int x,y;
	public LookinfoUI(){}
	public LookinfoUI(Account myInfo) {
		super("好友资料");
		Toolkit tool =Toolkit.getDefaultToolkit();
		Image icon = tool.getImage("image/QQ.png");
		setIconImage(icon);
		JLabel lblBg = new JLabel(new ImageIcon("MyImages/bj.jpeg"));
		add(lblBg);
	    lblBg.setLayout(null);
	  
	
		JLabel lbltitle = new JLabel("好友资料",JLabel.CENTER);
		lbltitle.setFont(new Font("楷体",Font.BOLD,36));
		lbltitle.setForeground(Color.CYAN);
		lbltitle.setBounds(0,30,260,40);
		lblBg.add(lbltitle);
		lblQQcode = new JLabel("QQ号码:"+myInfo.getQqCode());
		lblQQcode.setFont(new Font("楷体",Font.BOLD,18));
		lblQQcode.setForeground(Color.RED);
		lblQQcode.setBounds(30, 100, 120, 40);
		lblBg.add(lblQQcode);
		
		
		lblnickName = new JLabel("QQ昵称:"+myInfo.getNickName());
		lblnickName.setFont(new Font("楷体",Font.BOLD,18));
		lblnickName.setForeground(Color.RED);
		lblnickName.setBounds(30, 140, 130, 40);
		lblBg.add(lblnickName);
		
		lblHeadimg = new JLabel(new ImageIcon(myInfo.getHeadimg()));
		lblHeadimg.setFont(new Font("楷体",Font.BOLD,18));
		lblHeadimg.setForeground(Color.RED);
		lblHeadimg.setBounds(270, 100, 80, 60);
		lblBg.add(lblHeadimg);
		
		lblAge = new JLabel("年龄:"+myInfo.getAge());
		lblAge.setFont(new Font("楷体",Font.BOLD,18));
		lblAge.setForeground(Color.RED);
		lblAge.setBounds(30, 180, 100, 20);
		lblBg.add(lblAge);
		
		lblSex = new JLabel("性别:"+myInfo.getSex());
		lblSex.setFont(new Font("楷体",Font.BOLD,18));
		lblSex.setForeground(Color.RED);
		lblSex.setBounds(280, 180, 80, 20);
        lblBg.add(lblSex);
		
        lblNation = new JLabel("民族:"+myInfo.getNation());
        lblNation.setFont(new Font("楷体",Font.BOLD,18));
        lblNation.setForeground(Color.RED);
		lblNation.setBounds(30, 220, 100, 20);
		lblBg.add(lblNation);
        
        lblStar = new JLabel("星座:"+myInfo.getStar());
        lblStar.setFont(new Font("楷体",Font.BOLD,18));
        lblStar.setForeground(Color.RED);
		lblStar.setBounds(280, 220, 120, 40);
		lblBg.add(lblStar);
        
        lblBlood = new JLabel("血型:"+myInfo.getBlood()+"型");
        lblBlood.setFont(new Font("楷体",Font.BOLD,18));
        lblBlood.setForeground(Color.RED);
		lblBlood.setBounds(30, 260, 100, 20);
		lblBg.add(lblBlood);
		
        lblHobit = new JLabel("爱好:"+myInfo.getHobit());
        lblHobit.setFont(new Font("楷体",Font.BOLD,18));
        lblHobit.setForeground(Color.RED);
		lblHobit.setBounds(30, 300, 150, 20);
		lblBg.add(lblHobit);
        
        lblRemark = new JLabel("心情:"+myInfo.getRemark());
        lblRemark.setFont(new Font("楷体",Font.BOLD,18));
        lblRemark.setForeground(Color.RED);
		lblRemark.setBounds(30, 310, 300, 60);
		lblBg.add(lblRemark);
		
		lbgb = new JLabel("X");
		lbgb.setToolTipText("关闭");
		lbgb.setForeground(Color.RED);
		lbgb.setFont(new Font("黑体",Font.BOLD,30));
		lbgb.setBounds(380,10,30,20);
		lblBg.add(lbgb);
		lbgb.addMouseListener(this);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		setUndecorated(true);
        setResizable(false);
        setSize(420,360);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new LookinfoUI();
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==lbgb){
			dispose();
		}
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		//dispose();		
	}
	public void mousePressed(MouseEvent e) {
		 x=e.getX();
		 y=e.getY();
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void mouseDragged(MouseEvent e) {
		int jiex = this.getLocation().x;
		int jiey = this.getLocation().y;
		this.setLocation(jiex+e.getX()-x, jiey+e.getY()-y);
	}
	public void mouseMoved(MouseEvent e) {
		
	}
}
