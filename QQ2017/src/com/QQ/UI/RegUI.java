package com.QQ.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;





import com.QQ.DAO.AccountDAO;
import com.QQ.DAO.impl.AccountDaoImpl;
import com.QQ.beans.Account;

@SuppressWarnings("serial")
public class RegUI extends JFrame implements MouseListener,FocusListener,MouseMotionListener{
	private JLabel lbqqCode,lbnickName,lbheadImg,lbpassword,lbQrPassword,lbAge,lbSex;
	private JLabel lbXingzuo,lbXuexing,lbMingzu,lbHobit,lbIpAddr,lbPort;
	private JLabel lbGxsm;
	
	//背景
	JLabel lblBg;
	
	int x;
	int y;
	
	private JTextField txtqqCode,txtNickName,txtIpAddr,txtPort,txtHobit;
	private JPasswordField txtPassword,txtQrPassword;
	private JRadioButton nan,niu;
	private JComboBox cbMingzu,cbXingzuo,cbXuexing,cbHeadImg;
	private JTextArea taGxsm;
	//性别组
	private ButtonGroup bgSex;
	
	private JLabel lbgb;
	
	private JLabel btZc;
	
	//年龄下拉框
	private JComboBox cbAge;
	
	String sXingzu[] ={
			"双子座","金牛座","摩羯座","天蝎座","处女座","狮子座",
			"白羊座","水瓶座","射手座","天秤座","巨蟹座","双鱼座",		
	};
	String sXuexing[] = {"A","B","O","AB",};
	
	private String sMingzu[]={
			"汉族","苗族","壮族","高山族","回族","侗族",
			"傣族","藏族","朝鲜族","其他"
	};
	
	String sHeadImage[] = {
			 "face/0.png","face/1.png","face/2.png","face/3.png",
			 "face/4.png", "face/5.png", "face/6.png","face/7.png",
			 "face/8.png","face/9.png", "face/10.png", "face/11.png",
			 
	};
	
	private ImageIcon[] headIcon = {
			new ImageIcon(sHeadImage[0]),
			new ImageIcon(sHeadImage[1]),
			new ImageIcon(sHeadImage[2]),
			new ImageIcon(sHeadImage[3]),
			new ImageIcon(sHeadImage[4]),
			new ImageIcon(sHeadImage[5]),
			new ImageIcon(sHeadImage[6]),
			new ImageIcon(sHeadImage[7]),
			new ImageIcon(sHeadImage[8]),
			new ImageIcon(sHeadImage[9]),
			new ImageIcon(sHeadImage[10]),
			new ImageIcon(sHeadImage[11])
	};
public RegUI(){
		setIconImage(new ImageIcon("images/tubiao.jpg").getImage());
		
		lblBg = new JLabel(new ImageIcon("MyImages/6.jpeg"));
		add(lblBg);
		
		lblBg.setLayout(null);
		
		
		JLabel title = new JLabel("用户注册",JLabel.CENTER);
		title.setFont(new Font("楷体",Font.BOLD,36));
		title.setForeground(Color.RED);
		title.setBounds(170,30,160,40);
		lblBg.add(title);
		
		//按键
		lbgb = new JLabel("X");
		lbgb.setToolTipText("关闭");
		lbgb.setForeground(Color.RED);
		lbgb.setFont(new Font("黑体",Font.BOLD,30));
		
		lbgb.setBounds(520,10,30,20);
		
		lblBg.add(lbgb);
		
		//设置下拉框的背景颜色
		//UIManager.put("ComboBox.background", new Color(0,0,0,0));
		
		//年龄
		cbAge = new JComboBox();
		for(int i=0;i<=200;i++){
			cbAge.addItem(i);
		}
		cbAge.setBounds(100, 220, 150, 20);
		//cbAge.setOpaque(false);
		//cbAge.setEditable(true);
		lblBg.add(cbAge);
		
		lbqqCode = new JLabel("QQ号码:",JLabel.RIGHT);
		lbqqCode.setFont(new Font("楷体",Font.BOLD,15));
		lbqqCode.setForeground(Color.BLACK);
		
		lbnickName = new JLabel("昵称:",JLabel.RIGHT);
		lbnickName.setFont(new Font("楷体",Font.BOLD,15));
		lbnickName.setForeground(Color.BLACK);
		
		lbheadImg = new JLabel("头像:",JLabel.RIGHT);
		lbheadImg.setFont(new Font("楷体",Font.BOLD,15));
		lbheadImg.setForeground(Color.BLACK);
		
		lbpassword = new JLabel("登录密码:",JLabel.RIGHT);
		lbpassword.setFont(new Font("楷体",Font.BOLD,15));
		lbpassword.setForeground(Color.BLACK);
		
		lbQrPassword = new JLabel("确认密码:",JLabel.RIGHT);
		lbQrPassword.setFont(new Font("楷体",Font.BOLD,15));
		lbQrPassword.setForeground(Color.BLACK);
		
		
		lbAge = new JLabel("年龄:",JLabel.RIGHT);
		lbAge.setFont(new Font("楷体",Font.BOLD,15));
		lbAge.setForeground(Color.BLACK);
		
		lbSex = new JLabel("性别:",JLabel.RIGHT);
		lbSex.setFont(new Font("楷体",Font.BOLD,15));
		lbSex.setForeground(Color.BLACK);
		
		lbMingzu = new JLabel("民族:",JLabel.RIGHT);
		lbMingzu.setFont(new Font("楷体",Font.BOLD,15));
		lbMingzu.setForeground(Color.BLACK);
		
		lbXingzuo = new JLabel("星座:",JLabel.RIGHT);
		lbXingzuo.setFont(new Font("楷体",Font.BOLD,15));
		lbXingzuo.setForeground(Color.BLACK);
		
		lbXuexing = new JLabel("血型:",JLabel.RIGHT);
		lbXuexing.setFont(new Font("楷体",Font.BOLD,15));
		lbXuexing.setForeground(Color.BLACK);
		
		lbHobit = new JLabel("爱好:",JLabel.RIGHT);
		lbHobit.setFont(new Font("楷体",Font.BOLD,15));
		lbHobit.setForeground(Color.BLACK);
		
		lbIpAddr = new JLabel("IP地址:",JLabel.RIGHT);
		lbIpAddr.setFont(new Font("楷体",Font.BOLD,15));
		lbIpAddr.setForeground(Color.BLACK);
		
		lbPort = new JLabel("端口:",JLabel.RIGHT);
		lbPort.setFont(new Font("楷体",Font.BOLD,15));
		lbPort.setForeground(Color.BLACK);
		
		lbGxsm = new JLabel("个人说明:",JLabel.RIGHT);
		lbGxsm.setFont(new Font("楷体",Font.BOLD,15));
		lbGxsm.setForeground(Color.BLACK);
		
		
		txtqqCode = new JTextField(10);
		txtqqCode.setText("系统自动生成");
		txtqqCode.setEditable(false);
		txtNickName = new JTextField(10);
		cbHeadImg = new JComboBox(headIcon);
		txtPassword = new JPasswordField(10);
		txtPassword.setEchoChar('*');
		txtQrPassword = new JPasswordField(10);
		txtQrPassword.setEchoChar('*');
		 
		
		nan = new JRadioButton("男",true);
		niu = new JRadioButton("女");
		bgSex = new ButtonGroup();
		bgSex.add(nan);
		bgSex.add(niu);
		cbMingzu = new JComboBox(sMingzu);
		cbXingzuo = new JComboBox(sXingzu);
		cbXuexing = new JComboBox(sXuexing);
		txtHobit = new JTextField(20);
		InetAddress addr=null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		txtIpAddr = new JTextField(addr.getHostAddress());
		txtIpAddr.setFont(new Font("楷体",Font.ITALIC,15));
		txtIpAddr.setForeground(Color.BLACK);
		
		txtPort = new JTextField(5);
		txtPort.setEditable(false);
		txtPort.setText("这个系统自动生成哦!");
		txtPort.setFont(new Font("楷体",Font.ITALIC,15));
		txtPort.setForeground(Color.BLACK);
		taGxsm  = new JTextArea(3,80);
		
		lbqqCode.setBounds(0, 100, 100, 20);
		txtqqCode.setBounds(100, 100, 150, 20);
		lbnickName.setBounds(0, 140, 100, 20);
		txtNickName.setBounds(100, 140, 150, 20);
		
		lbheadImg.setBounds(280, 100, 80, 60);
		cbHeadImg.setBounds(360, 100, 80, 60);
		
		lbpassword.setBounds(0, 180, 100, 20);
		txtPassword.setBounds(100, 180, 150, 20);
		lbQrPassword.setBounds(280, 180, 80, 20);
		txtQrPassword.setBounds(360, 180, 150, 20);
		
		txtPassword.addMouseListener(this);
		txtQrPassword.addMouseListener(this);
		

		lbAge.setBounds(0, 220, 100, 20);
		lbSex.setBounds(280, 220, 80, 20);
		nan.setBounds(360, 220, 40, 20);
		nan.setOpaque(false);
		niu.setBounds(400, 220, 40, 20);
		niu.setOpaque(false);
		
		lbMingzu.setBounds(0, 260, 100, 20);
		cbMingzu.setBounds(100, 260, 150, 20);
		lbXingzuo.setBounds(280, 260, 80, 20);
		cbXingzuo.setBounds(360, 260, 150, 20);
		
		lbXuexing.setBounds(0, 300, 100, 20);
		cbXuexing.setBounds(100, 300, 150, 20);
		lbHobit.setBounds(0, 340, 100, 20);
		txtHobit.setBounds(100, 340, 410, 20);

		lbIpAddr.setBounds(0, 380, 100, 20);
		txtIpAddr.setBounds(100, 380, 150, 20);
		lbPort.setBounds(280, 380, 80, 20);
		txtPort.setBounds(360, 380, 150, 20);
		
		lbGxsm.setBounds(0, 420, 100, 20);
		taGxsm.setBounds(100, 420, 410, 80);
		
		/*设置透明**/
		
		txtNickName.setOpaque(false);
		txtPassword.setOpaque(false);
		txtQrPassword.setOpaque(false);
		cbAge.setOpaque(false);
		taGxsm.setOpaque(false);
		txtqqCode.setOpaque(false);
		txtIpAddr.setOpaque(false);
		txtPort.setOpaque(false);
		txtHobit.setOpaque(false);
		cbMingzu.setOpaque(false);
		
		
		lblBg.add(lbqqCode);
		lblBg.add(txtqqCode);
		lblBg.add(lbnickName);
		lblBg.add(txtNickName);
		lblBg.add(lbheadImg);
		lblBg.add(cbHeadImg);
		lblBg.add(lbpassword);
		lblBg.add(txtPassword);
		lblBg.add(lbQrPassword);
		lblBg.add(txtQrPassword);
		lblBg.add(lbAge);
		lblBg.add(lbSex);
		lblBg.add(nan);
		lblBg.add(niu);
		lblBg.add(lbMingzu);
		lblBg.add(cbMingzu);
		lblBg.add(lbXingzuo);
		lblBg.add(cbXingzuo);
		lblBg.add(lbXuexing);
		lblBg.add(cbXuexing);
		lblBg.add(lbHobit);
		lblBg.add(txtHobit);
		lblBg.add(lbIpAddr);
		lblBg.add(txtIpAddr);
		lblBg.add(lbPort);
		lblBg.add(txtPort);
		lblBg.add(lbGxsm);
		lblBg.add(taGxsm);
		
		btZc = new JLabel("【立即注册】");
		btZc.setFont(new Font("楷体",Font.BOLD,30));
		btZc.setForeground(Color.CYAN);
		
		btZc.setBounds(180, 500, 200, 40);
		lblBg.add(btZc);
		
		btZc.addMouseListener(this);
		lbgb.addMouseListener(this);
		
		txtQrPassword.addFocusListener(this);
		
		//让窗口移动
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		setSize(550, 550);
		setUndecorated(true);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		new RegUI();
	}
	@SuppressWarnings("deprecation")
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==btZc){
			if(txtNickName.getText().equals("") || txtHobit.getText().equals("")
				|| txtNickName.getText().equals("")|| txtPassword.getText().equals("")
				|| txtQrPassword.getText().equals("")){
				JOptionPane.showMessageDialog(this,"信息未填写完整,请继续编写");
				return;
			}
			if(txtPassword.getText().length()<6){
				JOptionPane.showMessageDialog(this,"密码长度不能低于六位哦");	
				return;
			}else{	
			}
				Account a = new Account();    
			    a.setNickName(txtNickName.getText());
			    a.setQqPassword(txtPassword.getText());
			    a.setHobit(txtHobit.getText());
			    a.setRemark(taGxsm.getText());
			    a.setIpAddr(txtIpAddr.getText());
			    
			    a.setAge(cbAge.getSelectedIndex());
			    a.setNation(sMingzu[cbMingzu.getSelectedIndex()]);
			    a.setStar(sXingzu[cbXingzuo.getSelectedIndex()]);
			    a.setBlood(sXuexing[cbXuexing.getSelectedIndex()]);
			    a.setHeadimg(sHeadImage[cbHeadImg.getSelectedIndex()]);
			    
			    if(nan.isSelected()){
			    	a.setSex("男");
			    }else{
			    	a.setSex("女");
			    }
			   AccountDAO accountDao = new AccountDaoImpl();
			   String qqCode = accountDao.saveAccount(a);
			   JOptionPane.showMessageDialog(this,"您注册的qq号码是"+qqCode+"\n请妥善保管你的账号和密码");
			   
			   txtNickName.setText("");
			   txtHobit.setText("");
			   txtNickName.setText("");
			   txtPassword.setText("");
			   txtQrPassword.setText("");
			   taGxsm.setText("");

			    
		}else if(e.getSource()==lbgb){
			dispose();
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {
		 x=e.getX();
		 y=e.getY();
	}
	public void mouseReleased(MouseEvent arg0) {}
	public void focusGained(FocusEvent e) {}
	@SuppressWarnings("deprecation")
	public void focusLost(FocusEvent e) {
		String p = txtPassword.getText().trim();
		String c = txtQrPassword.getText().trim();
		if(p.equals(c)){
			
		}else{
			JOptionPane.showMessageDialog(null, "密码重输错误");
			txtQrPassword.setText("");	
			return;
		}
	}
	public void mouseDragged(MouseEvent e) {
		int jiex = this.getLocation().x;
		int jiey = this.getLocation().y;
		this.setLocation(jiex+e.getX()-x, jiey+e.getY()-y);
	}
	public void mouseMoved(MouseEvent e) {}
}
