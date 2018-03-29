package com.QQ.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.QQ.DAO.AccountDAO;
import com.QQ.DAO.impl.AccountDaoImpl;
import com.QQ.beans.Account;



@SuppressWarnings("serial")
public class ZhPassword extends JFrame implements ActionListener,MouseListener{
	JLabel bg;
	JLabel b1,b2,b3,QQbel,provingbel,lbname;
	JLabel passwordbel,cspasswordbel,sjnumbel,secretkeybel;
	JButton obtainbtn,ok;
	JTextField qqnum,obtainnum,provingnum,txtname;
	JPasswordField password,cspassword;
	static Point origin= new Point();	//放在构造方法前面，定义成全局
	ZhPassword(){
		super("找回密码");
		Toolkit tool=Toolkit.getDefaultToolkit();
		Image image=tool.getImage("tubiao/9.png");
		setIconImage(image);
		setUndecorated(true);
		bg=new JLabel(new ImageIcon("images/bgreg5.jpg"));
		add(bg);
		
		b1=new JLabel("-");
		b1.setFont(new Font("黑体",Font.PLAIN,28));
		b1.setForeground(Color.white);
		b1.setBounds(358,0,30,30);
		bg.add(b1);
		
		b2=new JLabel("x");
		b2.setFont(new Font("黑体",Font.PLAIN,28));
		b2.setForeground(Color.white);
		b2.setBounds(388,0,30,30);
		bg.add(b2);
		
		QQbel=new JLabel("QQ账号",JLabel.RIGHT);
		QQbel.setFont(new Font("黑体", Font.PLAIN, 18));
		QQbel.setBounds(40,100,90,25);
		bg.add(QQbel);
		qqnum=new JTextField();
		qqnum.setBounds(140,100,200,25);
		bg.add(qqnum);
		
//		lbname = new JLabel("昵称",JLabel.RIGHT);
//		lbname.setFont(new Font("黑体",Font.PLAIN,18));
//		lbname.setBounds(40,150,90,25);
//		bg.add(lbname);
//		txtname = new JTextField();
//		txtname.setBounds(140,150,200,25);
//		bg.add(txtname);
		
		
		//获取验证码
		obtainnum=new JTextField();
		obtainnum.setBounds(140,205,100,25);
		obtainnum.setEditable(false);
		bg.add(obtainnum);
		obtainbtn=new JButton("获取验证码");
		obtainbtn.setForeground(Color.red);
		obtainbtn.setContentAreaFilled(false);
		//obtainbtn.setEnabled(false);
		obtainbtn.setBounds(250,205,100,25);
		bg.add(obtainbtn);
		
		//输入验证码
		provingbel=new JLabel("验证码",JLabel.RIGHT);
		provingbel.setFont(new Font("黑体",Font.PLAIN,18));
		provingbel.setBounds(40,240,90,25);
		bg.add(provingbel);
		provingnum=new JTextField();
		provingnum.setBounds(140,240,100,25);
		bg.add(provingnum);
					
		passwordbel=new JLabel("新密码",JLabel.RIGHT);
		passwordbel.setFont(new Font("黑体",Font.PLAIN,18));
		passwordbel.setBounds(40,275,90,25);
		bg.add(passwordbel);
		
		password=new JPasswordField();
		password.setBounds(140,275,200,25);
		bg.add(password);
		
		cspasswordbel=new JLabel("确认密码",JLabel.RIGHT);
		cspasswordbel.setFont(new Font("黑体",Font.PLAIN,18));
		cspasswordbel.setBounds(40,310,90,25);
		bg.add(cspasswordbel);
		
		cspassword=new JPasswordField();
		cspassword.setBounds(140,310,200,25);
		bg.add(cspassword);
		
		//更改
		ok=new JButton("确  定");
		ok.setFont(new Font("黑体",Font.PLAIN,18));
		ok.setBounds(150,380,150,30);
		ok.setContentAreaFilled(false);
		bg.add(ok);
		ok.addActionListener(this);
		
		//监听
		obtainbtn.addActionListener(this);
		
		b1.addMouseListener(this);
		b2.addMouseListener(this);

		//实现窗口拖动
		addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					origin.x = e.getX();
					origin.y = e.getY();
				}
			});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
		
		setVisible(true);
		setSize(411,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//动作事件（获取验证码）（确定）
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==obtainbtn){
			int obtainnumber = getobtain();
			obtainnum.setText(new Integer(obtainnumber).toString());
			//System.out.println("随机");
		}else if(e.getSource()==ok){
			AccountDAO ac=new AccountDaoImpl();
			Account a=new Account();
			//System.out.println("Account");
			if(qqnum.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this,"请输入QQ账号");
			}else{
				if(!qqnum.getText().trim().equals("")){
					try{
						a.setQqCode(Integer.parseInt(qqnum.getText().toString()));
						return;
					}catch(NumberFormatException n){
						JOptionPane.showMessageDialog(this,"请输入正确QQ号");
						return;
					}
				}
				if(provingnum.getText().trim().equals("")){
					JOptionPane.showMessageDialog(this,"请输入验证码");
					return;
				}
				if(!(provingnum.getText().trim().equals(obtainnum.getText().trim()))){
					JOptionPane.showMessageDialog(this,"验证码输入错误");
					return;
				}
				if(password.getText().trim().equals("")){
					JOptionPane.showMessageDialog(this,"密码不能为空");
					return;
				}
				if(password.getText().trim().length()<6){
					JOptionPane.showMessageDialog(this,"密码不能小于6位");
					return;
				}
				a.setQqPassword(password.getText().toString().trim());
				a = ac.zhmm(a);
				if(a!=null){
					//dispose();
					JOptionPane.showMessageDialog(this,"找回成功");
					new QQLonging();
				}else{
					JOptionPane.showMessageDialog(this,"你输入的个人信息有误");
				}
			}
		}
	}
	public int getobtain(){
		int obtain=0;
		Random r=new Random();
		obtain=r.nextInt(8999)+1000;
		return obtain;
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==b1){
			setState(JFrame.ICONIFIED);
		}else if(e.getSource()==b2){
			System.exit(0);;
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


