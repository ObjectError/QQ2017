package com.QQ.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.QQ.DAO.impl.AccountDaoImpl;
import com.QQ.beans.Account;

@SuppressWarnings("serial")
public class QQLonging extends JFrame implements MouseListener,MouseMotionListener,ItemListener{
	JLabel lbmin,lbClose,lbHead,lbzc,lbwjma,lbjia;
	JButton btnLogin;
	JComboBox cbQQzh;
	JPasswordField txtpassword;
	JCheckBox cbjzmm,cbzdlogi;
	
	JLabel lblicon2,lbabout;
	HashMap<Integer, Account> user =null;
	
	int x;
	int y;
	
	public QQLonging(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("heads/touxiang1.png");
		setIconImage(img);
		
		JLabel bg = new JLabel(new ImageIcon("images/login.jpg"));
		
		bg.setLayout(null);
		
		add(bg);
		
		//动态效果
		JLabel lblicon3 = new JLabel(new ImageIcon("image/qqname.gif"));
		lblicon3.setBounds(0, 0, 430, 175);
		bg.add(lblicon3); 
		
		//二维码
		lblicon2 = new JLabel(new ImageIcon("image/qqma (1).png"));
		lblicon2.setBounds(355, 284, 100, 30);
		bg.add(lblicon2);
		
		//左下
		lbjia = new JLabel(new ImageIcon("image/qqlng.png"));
		lbjia.setBounds(10,284,30,30);
		bg.add(lbjia);
		
		
		lbmin = new JLabel(" ");
		lbmin.setToolTipText("最小化");
		lbmin.setForeground(Color.WHITE);
		lbmin.setFont(new Font("黑体",Font.BOLD,20));
		
		lbClose = new JLabel("");
		lbClose.setToolTipText("关闭");
		lbClose.setForeground(Color.WHITE);
		lbClose.setFont(new Font("黑体",Font.BOLD,18));
	
		lbmin.setBounds(380,0,20,20);
		lbClose.setBounds(400,0,20,20);
		lblicon3.add(lbmin);
		lblicon3.add(lbClose);
		
		lbabout = new JLabel("");
		lbabout.setToolTipText("关于我们");
		lbabout.setBounds(350,0,20,20);
		lblicon3.add(lbabout);
		
		lbHead = new JLabel(new ImageIcon("heads/touxiang1.png"));
		cbQQzh = new JComboBox();
		txtpassword = new JPasswordField();
		lbzc = new JLabel("     ");
		lbwjma = new JLabel("     ");
		btnLogin = new JButton(new ImageIcon("image/qqlogin.png") );
		cbjzmm = new JCheckBox("记住密码",true);
		cbzdlogi = new JCheckBox("自动登入");
				
		cbQQzh.setBounds(130,185,194,30);
		
		cbQQzh.setEditable(true);
		cbQQzh.setToolTipText("账号");
		txtpassword.setBounds(130,215,195,30);
		txtpassword.setToolTipText("密码");
		cbQQzh.addItemListener(this);
		
		
		lbzc.setBounds(335,183,80,30);
		lbwjma.setBounds(335,214,80,30);
		
		lbHead.setBounds(70,185,60,60);
		
		btnLogin.setBounds(130,278,195,30);
		
		cbjzmm.setBounds(127,243,80,30);
		cbzdlogi.setBounds(254,243,80,30);
		
		//透明多选框控件
		cbjzmm.setOpaque(false);
		cbzdlogi.setOpaque(false);
		
		bg.add(cbQQzh);
		bg.add(txtpassword);
		bg.add(lbzc);
		bg.add(lbwjma);
		bg.add(lbHead);
		bg.add(btnLogin);
		bg.add(cbjzmm);
		bg.add(cbzdlogi);
		
		readFile();
		
		lbmin.addMouseListener(this);
		lbClose.addMouseListener(this);
		lbzc.addMouseListener(this);
		lbwjma.addMouseListener(this);
		lblicon2.addMouseListener(this);
		lbzc.addMouseListener(this);
		lbabout.addMouseListener(this);
		btnLogin.addMouseListener(this);
		lbjia.addMouseListener(this);
		
		//让窗口移动
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		
		setUndecorated(true);
		this.setVisible(true);
		this.setSize(427,321);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new QQLonging();
	}
	@SuppressWarnings("deprecation")
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==lbmin){
			//最小化
			this.setState(JFrame.HIDE_ON_CLOSE);
		}else if(e.getSource()==lbClose){
			System.exit(0);
		}else if(e.getSource()==lbzc){
			new RegUI();
		}else if(e.getSource()==lbwjma){
			//JOptionPane.showMessageDialog(this,"此功能还在开发中,请耐心等候....");
			//dispose();
			new ZhPassword();
		}else if(e.getSource()==lblicon2){
			JOptionPane.showMessageDialog(this,"哎呀，二维码君旅游去了,请稍后再试吧");
		}else if(e.getSource()==lbabout){			
			new QQaboutUI();
		}else if(e.getSource()==btnLogin){
			String qqcode="";
			String pwd = txtpassword.getText().trim();
			if(cbQQzh.getSelectedItem()==null || cbQQzh.getSelectedItem().toString().equals("") ){
				JOptionPane.showMessageDialog(this,"请输入QQ号码！");
				return;
			}
			if(pwd.equals(" ")){
				JOptionPane.showMessageDialog(this, "请输入QQ密码！");
				return;
			}
			qqcode=cbQQzh.getSelectedItem().toString();
			Account account = new Account();
	        account.setQqCode(Integer.parseInt(qqcode));
			account.setQqPassword(pwd);
			account = new AccountDaoImpl().lonin(account); 
			if(account==null){
					JOptionPane.showMessageDialog(this, "登入失败，账号或者是密码错误！");
					return;
				}else{
					Save(account);
					dispose();
					new MainQQ(account);
				}	
			}else if(e.getSource()==lbjia){
				JOptionPane.showMessageDialog(this,"加载中....");
		}
}
		@SuppressWarnings("unchecked")
		private void Save(Account account) {
	           HashMap<Integer, Account>user = null;
			   File file = new File("account.dat");
			   try {
				   if(!file.exists()){
				file.createNewFile();
				user = new HashMap<Integer, Account>();
				   }else{
					   ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					   user = (HashMap<Integer, Account>)ois.readObject();
					   ois.close();
				   }
				   user.put(account.getQqCode(), account);
				   ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				   oos.writeObject(user);
				   oos.flush();
				   oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		@SuppressWarnings({ "unchecked", "resource" })
		public void readFile(){
			File file = new File("account.dat");
			if(!file.exists()){
				return;
			}
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				user = (HashMap<Integer, Account>)ois.readObject();
				Set<Integer>set = user.keySet();
				Iterator<Integer>it =set.iterator();
				while(it.hasNext()){
					cbQQzh.addItem(it.next());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {
		 x=e.getX();
		 y=e.getY();
	}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent e) {
		int jiex = this.getLocation().x;
		int jiey = this.getLocation().y;
		this.setLocation(jiex+e.getX()-x, jiey+e.getY()-y);
	}
	public void mouseMoved(MouseEvent e) {}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==cbQQzh){
			if(!cbQQzh.getSelectedItem().toString().equals("") && user!=null){
				int qqcode = Integer.parseInt(cbQQzh.getSelectedItem().toString());
				Account account = user.get(qqcode);
				if(account!=null){
					txtpassword.setText(account.getQqPassword());
					lbHead.setIcon(new ImageIcon(account.getHeadimg()));
				}
			}
		}
	}
}
