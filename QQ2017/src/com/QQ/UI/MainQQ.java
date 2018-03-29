package com.QQ.UI;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

import com.QQ.DAO.AccountDAO;
import com.QQ.DAO.impl.AccountDaoImpl;
import com.QQ.base.Cmd;
import com.QQ.base.SendMSG;
import com.QQ.base.Sendcmd;
import com.QQ.base.Sund;
import com.QQ.base.WebApp;
import com.QQ.beans.Account;

@SuppressWarnings("serial")
public class MainQQ extends JFrame implements ActionListener,MouseListener,MouseMotionListener,ItemListener{
	
	private Desktop desktop = Desktop.getDesktop();
	/** 定义统一资源标识符对象 */
	private URI uri;
	private TrayIcon traycon;//托盘图标  
	JLabel kongjian,youxiang,huiyuan,huiyuan2,shiping;
	JLabel gupiao,jindong,yingyong,caidan,shezhi,xiaoxigl,wenjian2,shoucang,chazhao,yingbao;
	JLabel youxi,yifu,liulan,yinyue,chazhao2,huanfu,buluo,tianqi,qita;
	JLabel close,zuixiao;
	JLabel imag,lblmyinfo;
	JTabbedPane tablepane;
	JList lifriend,lifamily,limate,lihmd;
	JComboBox cbstatu;
	JPopupMenu popupmenu;
	JTextField shousuo;
	JMenuItem miChat,milookinfo,midel;
	JMenuItem mifrienf,mifamily,mimate,mihmd;
	Account myInfo,friendinfo;
	Vector<Account> Vmyinfo,Vfriend,Vfamily,Vmate,Vhmd;
	AccountDAO accountdao = new AccountDaoImpl(); 
	//托盘对应的菜单
	PopupMenu popTray;
	MenuItem miOpen,miExit,miOnline,miLevel,miBuys;
	int x,y;
	Hashtable<Integer, ChatUI> chatWin = new Hashtable<Integer, ChatUI>();
	public MainQQ(){}
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public MainQQ(Account myInfo) {
		
	    this.myInfo=myInfo;
	    setIconImage(new ImageIcon(myInfo.getHeadimg()).getImage());
		setResizable(false);
		imag=new JLabel(new ImageIcon("image/q1.jpg"));
		imag.setLayout(null);
		imag.setOpaque(false);
		 Vmyinfo = new Vector<Account>();
		 Vfriend = new Vector<Account>();
		 Vfamily = new Vector<Account>();
		 Vmate = new Vector<Account>();
		 Vhmd = new Vector<Account>();
		 lifriend = new JList();
		 lifamily = new JList();
		 limate = new JList();
		 lihmd = new JList();
		
		 refresh();
	
		lifriend.setOpaque(false);
	    lifamily.setOpaque(false);
	    limate.setOpaque(false);
	    lihmd.setOpaque(false);
		UIManager.put("TabbedPane.contentOpaque", false);
		tablepane = new JTabbedPane();
		tablepane.setOpaque(false);
		tablepane.add("   好友   ",lifriend);
		tablepane.add("   家人   ",lifamily);
		tablepane.add("   同学   ",limate);
		tablepane.add("   黑名单   ",lihmd);
		tablepane.setBounds(0 ,140, 300, 460);
		imag.add(tablepane);
		add(imag);
		
		lifriend.addMouseListener(this);
		lifamily.addMouseListener(this);
		limate.addMouseListener(this);
		lihmd.addMouseListener(this);
		
	    zuixiao = new JLabel(new ImageIcon("tubiao1/QQzxh.png"));
		zuixiao.setBounds(250, 0, 22, 20);
		imag.add(zuixiao);
		zuixiao.setToolTipText("最小化");
		zuixiao.addMouseListener(this);
		
		close = new JLabel(new ImageIcon("tubiao1/QQgb.png"));
		close.setBounds(278, 0, 22, 20);
		close.addMouseListener(this);
		close.setToolTipText("关闭");
		imag.add(close);
		
		ImageIcon icn = new ImageIcon(myInfo.getHeadimg());
		String nickname = myInfo.getNickName()+"("+myInfo.getQqCode()+")";
		lblmyinfo = new JLabel(nickname,icn,JLabel.LEFT);
		lblmyinfo.setBounds(2,27,150,70);
		lblmyinfo.setOpaque(false);
		imag.add(lblmyinfo);
		lblmyinfo.setToolTipText(myInfo.getNickName()+"\n"+myInfo.getQqCode()+"【"+myInfo.getRemark()+"】");
		lblmyinfo.addMouseListener(this);
		
		cbstatu = new JComboBox(Cmd.STATUS);
		cbstatu.removeItemAt(1);
		cbstatu.setBounds(160, 27, 60, 20);
		cbstatu.setBackground(new Color(0,0,0,0));
		cbstatu.setOpaque(false);
		imag.add(cbstatu);
		cbstatu.addItemListener(this);
	
		JLabel xiaoqq = new JLabel(new ImageIcon("tubiao1/QQtubiao1.png"));
		xiaoqq.setBounds(2,2, 43, 21);
		xiaoqq.setToolTipText("QQ2017");
		imag.add(xiaoqq);
		
		//天气
		tianqi = new JLabel(new ImageIcon("QQbj/qqtq.png"));
		tianqi.setBounds(230,27, 80, 75);
		tianqi.setToolTipText("天气");
		tianqi.setToolTipText("天气");
		imag.add(tianqi);
		tianqi.addMouseListener(this);
		
		//空间
		kongjian = new JLabel(new ImageIcon("tubiao1/kongjiang.png"));
		kongjian.setBounds(75,100, 22, 20);
		imag.add(kongjian);
		kongjian.setToolTipText("空间");
		kongjian.addMouseListener(this);

		//邮箱
	    youxiang = new JLabel(new ImageIcon("tubiao1/youxinag.png"));
		youxiang.setBounds(115,100, 22, 20);
		imag.add(youxiang);
		youxiang.setToolTipText("邮箱");
		youxiang.addMouseListener(this);
		
		//部落
		buluo = new JLabel(new ImageIcon("tubiao1/bulu.png"));
		buluo.setBounds(160,100,22,20);
		buluo.setToolTipText("部落");
		buluo.addMouseListener(this);
		imag.add(buluo);
		
		//会员
		huiyuan = new JLabel(new ImageIcon("tubiao1/vip.png"));
		huiyuan.setBounds(205, 100, 22, 20);
		imag.add(huiyuan);
		huiyuan.setToolTipText("会员");
		huiyuan.addMouseListener(this);
		
		huiyuan2 = new JLabel(new ImageIcon("QQbj/j10.jpg"));
		huiyuan2.setBounds(149,55,24,10);
		imag.add(huiyuan2);
		huiyuan2.setToolTipText("您是尊贵的年费超级会员,等级正在超级加速中...");
		huiyuan2.addMouseListener(this);
		
		//更多
		qita = new JLabel(new ImageIcon("tubiao1/jia.png"));
		qita.setBounds(226, 100, 22, 20);
		qita.setToolTipText("更多");
		qita.addMouseListener(this);
		imag.add(qita);
		
		//衣服(换肤)
		yifu = new JLabel(new ImageIcon("tubiao1/huanfu.png"));
		yifu.setBounds(220,0, 22, 20);
		yifu.setToolTipText("换肤");
		imag.add(yifu);
		yifu.addMouseListener(this);
				
		//浏览器
		liulan = new JLabel(new ImageIcon("tubiao/liulan.png"));
		liulan.setBounds(1,610, 20, 22);
		liulan.setToolTipText("浏览器");
		imag.add(liulan);
		liulan.addMouseListener(this);
		
		//游戏                        
		youxi = new JLabel(new ImageIcon("QQbj/qqyx2.png"));
		youxi.setBounds(24,610, 20, 22);
		youxi.setToolTipText("游戏");
		imag.add(youxi);
		youxi.addMouseListener(this);
		
		//音乐
		yinyue = new JLabel(new ImageIcon("tubiao/yinyue.png"));
		yinyue.setBounds(47,610, 20, 22);
		imag.add(yinyue);
		yinyue.setToolTipText("音乐");
		yinyue.addMouseListener(this);
		
		JLabel zhibo = new JLabel(new ImageIcon("QQbj/qqzb.png"));
		zhibo.setBounds(70,610, 20, 22);
		imag.add(zhibo);
		
		//视频
		shiping = new JLabel(new ImageIcon("tubiao/shiping.png"));
		shiping.setBounds(93,610, 20, 22);
		imag.add(shiping);
		shiping.setToolTipText("视频");
		shiping.addMouseListener(this);

		//查找（上）
		chazhao2 = new JLabel(new ImageIcon("tubiao1/chazhao.png"));
		chazhao2.setBounds(202, 118, 60, 24);
		chazhao2.addMouseListener(this);
		chazhao2.setToolTipText("添加好友");
		imag.add(chazhao2);
		
		
		gupiao = new JLabel(new ImageIcon("tubiao/gupiao.png"));
		gupiao.setBounds(116,610, 20, 22);
		gupiao.setToolTipText("股票");
		imag.add(gupiao);
		
		jindong= new JLabel(new ImageIcon("tubiao/jingdong.png"));
		jindong.setBounds(139,610, 20, 22);
		imag.add(jindong);
		jindong.setToolTipText("京东");
		jindong.addMouseListener(this);
		
		//管理
		caidan= new JLabel(new ImageIcon("QQbj/j15.png"));
		caidan.setBounds(261,634, 60, 22);
		caidan.addMouseListener(this);
		caidan.setToolTipText("管理");
		imag.add(caidan);
		
		//设置
		shezhi= new JLabel(new ImageIcon("tubiao/shezhi.png"));
		shezhi.setBounds(30,634, 20, 22);
		shezhi.addMouseListener(this);
		shezhi.setToolTipText("设置");
		imag.add(shezhi);
		
		//消息
		xiaoxigl= new JLabel(new ImageIcon("tubiao/xiaoxigl.png"));
		xiaoxigl.setBounds(60,634, 20, 22);
		xiaoxigl.addMouseListener(this);
		xiaoxigl.setToolTipText("消息");
		imag.add(xiaoxigl);
		
		//文件
		wenjian2= new JLabel(new ImageIcon("tubiao/wenjian.png"));
		wenjian2.setBounds(90,634, 20, 22);
		wenjian2.addMouseListener(this);
		wenjian2.setToolTipText("文件");
		imag.add(wenjian2);
		
		//收藏
		shoucang= new JLabel(new ImageIcon("tubiao/shoucang.png"));
		shoucang.setBounds(120,634, 20, 22);
		shoucang.addMouseListener(this);
		shoucang.setToolTipText("收藏");
		imag.add(shoucang);
		
		//查找(下)
		chazhao= new JLabel(new ImageIcon("QQbj/hy.png"));
		chazhao.setBounds(180,634, 60, 22);
		chazhao.addMouseListener(this);
		chazhao.setToolTipText("添加好友");
		imag.add(chazhao);
		
		//电脑管家
		yingbao= new JLabel(new ImageIcon("QQbj/j14.png"));
		yingbao.setBounds(150,634, 20,20);
		yingbao.addMouseListener(this);
		yingbao.setToolTipText("电脑管家");
		imag.add(yingbao);
		
		//搜索框
		shousuo = new JTextField(15);
		shousuo.setOpaque(false);
		shousuo.setBounds(0, 118, 200, 24);
		shousuo.addMouseListener(this);
		imag.add(shousuo);
		
		 addMouseMotionListener(this);
         addMouseListener(this);
		cerenMen();
		
		new ReceiveThreah().start();
		
		Sendcmd.Sendall(Vmyinfo, myInfo, Cmd.CMD_ONLINE);
		setUndecorated(true);
		setSize(300,670);
		setVisible(true);
		Vector acc2 = new Vector();
		acc2=accountdao.readfriend(myInfo.getQqCode());
        if(acc2.size()!=0){
        	int qq=((Integer)acc2.get(0));
        	Account friendinfo = accountdao.getSelectedFriend(qq);
        	ChatUI chat=chatWin.get(friendinfo.getQqCode());
        	if(chat==null){
        		chat=new ChatUI(myInfo,friendinfo);
        		chatWin.put(friendinfo.getQqCode(), chat);
        	}
        	chat.show();
			String str2=acc2.get(3).toString();
			String name=friendinfo.getNickName();
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time=sf.format(date);
			String s=name+""+time+"\n"+str2+"\n";
			chat.txtReceive.setText(s);
        }
	    setLocation(700, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accountdao.deloffmsg(myInfo.getQqCode());
	}
	public void cerenMen() {
		 popupmenu = new JPopupMenu();
		 miChat = new JMenuItem("聊天");
		 milookinfo = new JMenuItem("查看好友资料");
		 midel = new JMenuItem("删除好友");
		 mifrienf = new JMenuItem("移动到好友");
		 mifamily = new JMenuItem("移动到家人");
		 mimate = new JMenuItem("移动到同学");
		 mihmd = new JMenuItem("移动到黑名单");
		 
		 miChat.addActionListener(this);
		 milookinfo.addActionListener(this);
		 midel.addActionListener(this);
		 mifrienf.addActionListener(this);
		 mifamily.addActionListener(this);
		 mimate.addActionListener(this);
		 mihmd.addActionListener(this);
		 popupmenu.add(miChat);
		 popupmenu.add(milookinfo);
		 popupmenu.add(midel);
		 popupmenu.add(mifrienf);
		 popupmenu.add(mifamily);
		 popupmenu.add(mimate);
		 popupmenu.add(mihmd);
	
	}
	public void refresh(){
		//获取所有的好友信息（好友，家人，同学，黑名单）
		Vmyinfo = accountdao.getmyfriend(myInfo.getQqCode());
//		"同学","好友","家人","黑名单"
		//清空Vector的值
		Vmate.clear();
		Vfriend.clear();
		Vfamily.clear();
		Vhmd.clear();
		for (Account acc : Vmyinfo) {
			String groupName = acc.getGropName();
			if(groupName.equals(Cmd.GROUPNAME[0])){
				Vmate.add(acc);
			}else if(groupName.equals(Cmd.GROUPNAME[1])){
				Vfriend.add(acc);
			}else if(groupName.equals(Cmd.GROUPNAME[2])){
				Vfamily.add(acc);
			}else if(groupName.equals(Cmd.GROUPNAME[3])){
				Vhmd.add(acc);
			}
		}
		lifriend.setModel(new DataModel(Vfriend));
		limate.setModel(new DataModel(Vmate));
		lifamily.setModel(new DataModel(Vfamily));
		lihmd.setModel(new DataModel(Vhmd));
		
		lifriend.setCellRenderer(new MyHeadImg(Vfriend));
		limate.setCellRenderer(new MyHeadImg(Vmate));
		lifamily.setCellRenderer(new MyHeadImg(Vfamily));
		lihmd.setCellRenderer(new MyHeadImg(Vhmd));
		
	}
	public void actionPerformed(ActionEvent e) {
	   if(e.getSource()==milookinfo){
		    new LookinfoUI(friendinfo);
       }else if(e.getSource()==miChat){
    	  openwinds();
       }else if(e.getSource()==mifrienf){
    	   accountdao.moveGroup(myInfo.getQqCode(), friendinfo.getQqCode(), Cmd.GROUPNAME[1]);
		   refresh();
       }else if(e.getSource()==mifamily){
    	   accountdao.moveGroup(myInfo.getQqCode(), friendinfo.getQqCode(), Cmd.GROUPNAME[2]);
    	   refresh();
       }else if(e.getSource()==mimate){
    	   accountdao.moveGroup(myInfo.getQqCode(), friendinfo.getQqCode(), Cmd.GROUPNAME[0]);
    	   refresh();
       }else if(e.getSource()==mihmd){
    	   accountdao.moveGroup(myInfo.getQqCode(), friendinfo.getQqCode(), Cmd.GROUPNAME[3]);
    	   refresh();
       }else if(e.getSource()==midel){
    		 accountdao.delFriend(myInfo.getQqCode(), friendinfo.getQqCode());
    		 refresh();
    		 SendMSG msg = new SendMSG();
    		 msg.cmd = Cmd.CMD_DELFRIEND;
    		 msg.myInfo=myInfo;
    		 msg.friendInfo=friendinfo;
    		 Sendcmd.Send(msg);
    	 }else if(e.getSource()==miOpen){
        	  MainQQ.this.setVisible(true);
          }else if(e.getSource()==miExit){
        	  accountdao.changeStatus(myInfo.getQqCode(), Cmd.STATUS[1]);
        	  System.exit(0);
          }else if(e.getSource()==miOnline){
        	  accountdao.changeStatus(myInfo.getQqCode(), Cmd.STATUS[0]);
        	  updatestatu2(Cmd.STATUS[0]);
        	  Sendcmd.Sendall(Vmyinfo, myInfo, Cmd.CMD_CHANGESTATE);
        	  refresh();
          }else if(e.getSource()==miLevel){
        	  accountdao.changeStatus(myInfo.getQqCode(), Cmd.STATUS[3]);
        	  updatestatu2(Cmd.STATUS[3]);
        	  Sendcmd.Sendall(Vmyinfo, myInfo, Cmd.CMD_CHANGESTATE);
        	  refresh();
          }else if(e.getSource()==miBuys){
        	  accountdao.changeStatus(myInfo.getQqCode(),Cmd.STATUS[2]);
        	  updatestatu2(Cmd.STATUS[2]);
        	  Sendcmd.Sendall(Vmyinfo, myInfo, Cmd.CMD_CHANGESTATE);
        	  refresh();
          }			
	}
	class DataModel extends AbstractListModel {
		Vector<Account> data;
		public DataModel() {}
		public DataModel(Vector<Account> data) {
			this.data = data;
		}
	    //系统自动运行，下标从0开始计算
	    public Object getElementAt(int index) {
	    	Account info = data.get(index);
	    	return info.getNickName()+"("+info.getQqCode()+")【"+info.getRemark()+"]";
	    }

	    public int getSize() {
	        return data.size();
	    }
	}
	// 获取好友头像
	class MyHeadImg extends DefaultListCellRenderer {
		Vector<Account> datas;
		public MyHeadImg(Vector<Account> datas) {
			this.datas = datas;
		}
		@SuppressWarnings("unused")
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			Component c = super.getListCellRendererComponent(list, value,
					index, isSelected, cellHasFocus);
			if (index >= 0 && index < datas.size()) {
				Account user =  datas.get(index);
				String status = user.getOnlinestatus();
				String headImg = user.getHeadimg();
//				STATUS={"在线","离线","忙碌","隐身"};
				String filename = "";
				if(status.equals(Cmd.STATUS[0])){
					filename = headImg;
				}else if(status.equals(Cmd.STATUS[1])){
					int pos = headImg.indexOf('.');
					String pre = headImg.substring(0,pos);
					String fix = headImg.substring(pos,headImg.length());
					filename = pre + "_h"+fix;
				}else if(status.equals(Cmd.STATUS[2])){
					int pos = headImg.indexOf('.');
					String pre = headImg.substring(0,pos);
					String fix = headImg.substring(pos,headImg.length());
					filename = pre + "_l"+fix;
				}else if(status.equals(Cmd.STATUS[3])){
					int pos = headImg.indexOf('.');
					String pre = headImg.substring(0,pos);
					String fix = headImg.substring(pos,headImg.length());
					filename = pre + "_w"+fix;
				}
				// 给列表中好友状态设置头像
				setIcon(new ImageIcon(filename));
				//设置文本内容
				setText(user.getNickName()+"("+user.getQqCode()+")【"+user.getRemark()+"】");
			}
			// 设置字体颜色
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			//设置有效
			setEnabled(list.isEnabled());
			//设置默认字体
			setFont(list.getFont());
//			setFont(new Font("宋体",Font.BOLD,14));
			//设置透明背景
			setOpaque(false);
			return this;
		}
	}
	//打开聊天窗口
	@SuppressWarnings("deprecation")
	public ChatUI openwinds(){
		//从Hashtable中获取根该朋友聊天的窗口信息
		ChatUI chat = chatWin.get(friendinfo.getQqCode());
		if(chat==null){
			chat = new ChatUI(myInfo,friendinfo);
			chatWin.put(friendinfo.getQqCode(), chat);
		}
		//显示
		chat.show();
		return chat;
	}
	@SuppressWarnings("static-access")
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==lblmyinfo){
			if(e.getClickCount()==2){
				new MYinfoRegUI(myInfo,this);
			}
		}else if(e.getSource()==lifriend){
			if(lifriend.getSelectedIndex()>=0){
				friendinfo = Vfriend.get(lifriend.getSelectedIndex());
			}
			//右键
			if(e.getButton()==3){
				if(lifriend.getSelectedIndex()>=0){
					popupmenu.show(lifriend, e.getX(), e.getY());
				}
			}else if(e.getClickCount()==2){//双击
				if(lifriend.getSelectedIndex()>=0){
					openwinds();
				}
			}
		}else if(e.getSource()==lifamily){
			if(lifamily.getSelectedIndex()>=0){
				friendinfo = Vfamily.get(lifamily.getSelectedIndex());
			}
			if(e.getButton()==3){
				if(lifamily.getSelectedIndex()>=0){
					popupmenu.show(lifamily, e.getX(), e.getY());
				}
			}else if(e.getClickCount()==2){//双击
				if(lifamily.getSelectedIndex()>=0){
					openwinds();
				}
			}
		}else if(e.getSource()==limate){
			if(limate.getSelectedIndex()>=0){
				friendinfo = Vmate.get(limate.getSelectedIndex());
			}
			if(e.getButton()==3){
				if(limate.getSelectedIndex()>=0){
					popupmenu.show(limate, e.getX(), e.getY());
				}
			}else if(e.getClickCount()==2){//双击
				if(limate.getSelectedIndex()>=0){
					friendinfo = Vmate.get(limate.getSelectedIndex());
					openwinds();
				}
			}
		}else if(e.getSource()==lihmd){
			if(e.getButton()==3){
				if(lihmd.getSelectedIndex()>=0){
					friendinfo = Vhmd.get(lihmd.getSelectedIndex());
					popupmenu.show(lihmd, e.getX(), e.getY());
				}
			}
		}else if(e.getSource()==jindong){
			String webSite = "https://www.jd.com/?cu=true&utm_source=sogou-pinzhuan&utm_medium=cpc&utm_campaign=t_288551095_sogoupinzhuan&utm_term=72c3e74a359c48598c6fabe6c1169112_0_469719f9013d43938324bc866dd37ebc"; 
			webb(webSite);
		}else if(e.getSource()==yinyue){
			String webSite = "https://y.qq.com/"; 
			webb(webSite);
		}else if(e.getSource()==shiping){
			String webSite = "https://v.qq.com/"; 
			webb(webSite);
		}else if(e.getSource()==tianqi){
			String webSite = "http://www.weather.com.cn/weather/101240701.shtml"; 
			webb(webSite);
		}else if(e.getSource()==qita){
			String webSite = "http://www.qq.com/?pgv_ref=junshidao1"; 
			webb(webSite);
		}else if(e.getSource()==youxiang){
			String webSite = "https://mail.qq.com/cgi-bin/loginpage"; 
			webb(webSite);
		}else if(e.getSource()==buluo){
			String webSite = "https://buluo.qq.com/p/"; 
			webb(webSite);
		}else if(e.getSource()==kongjian){
			//进入空间
			String webSite = "http://user.qzone.qq.com/"; 
			webb(webSite);
		}else if(e.getSource()==huiyuan | e.getSource()==huiyuan2){
			String webSite = "http://vip.qq.com/";
			webb(webSite);
		}else if(e.getSource()==youxi){
			String webSite = "http://qqgame.qq.com/";
			webb(webSite);
			//new Grame();
		}else if(e.getSource()==yingbao){
			new WebApp("电脑管家.lnk");
		}else if(e.getSource()==liulan){
			String webSite = "https://123.sogou.com/";
			webb(webSite);
		}else if(e.getSource()==zuixiao){
            Text();
            refresh();
		}else if(e.getSource()==close){
			accountdao.changeStatus(myInfo.getQqCode(), Cmd.STATUS[1]);
 			myInfo.setOnlinestatus(Cmd.STATUS[1]);
 			Sendcmd.Sendall(Vmyinfo, myInfo, Cmd.CMD_OFFLINE);
 			System.exit(0);
		} else if(e.getSource()==huanfu | e.getSource()==yifu){		
				JFileChooser jfc = new JFileChooser();				
				jfc.setDialogType(JFileChooser.OPEN_DIALOG);
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.setDialogTitle("更换皮肤");
			if (jfc.showOpenDialog(this) == jfc.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				String path = file.getPath();
				imag.setIcon(new ImageIcon(path));
			}
		}else if(e.getSource()==chazhao | e.getSource()==chazhao2){
	    	  new FindUI(myInfo);
	    }else if(e.getSource()==shousuo){
	    	new FindUI(myInfo);
	    }
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		x=e.getX();
		y=e.getY();
	}
	public void mouseReleased(MouseEvent e) {}
	class ReceiveThreah extends Thread{
		public ReceiveThreah() {
		}
		@SuppressWarnings({ "deprecation", "static-access" })
		@Override
		public void run() {
			try {
				DatagramSocket socket = new DatagramSocket(myInfo.getPort());
				while(true){
					refresh();
					byte b[] = new byte[1024*512];
				DatagramPacket packet = new DatagramPacket(b,0,b.length);
				socket.receive(packet);
				ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
				ObjectInputStream ois = new ObjectInputStream(bis);
				SendMSG msg = (SendMSG)ois.readObject();
				myInfo=msg.friendInfo;
				friendinfo=msg.myInfo;
				switch (msg.cmd) {
				case Cmd.CMD_ONLINE:
					refresh();
					new Sund(msg.cmd);
					new TipUI(friendinfo);
					break;
				case Cmd.CMD_LEVEL:
					refresh();
					break;
				case Cmd.CMD_BUYS:
					refresh();
					break;
				case Cmd.CMD_DELFRIEND:
					refresh();
					break;
				case Cmd.CMD_SEND:
					new Sund(msg.cmd);
					ChatUI chat=openwinds();
					
					try {
							chat.appenView(msg.myInfo.getNickName(), msg.doc);
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					break;
					case Cmd.CMD_OFFLINE:
						refresh();
						new TipUI(friendinfo);
						break;
				case Cmd.CMD_CHANGESTATE:
						refresh();
						break;
				case Cmd.CMD_SHAKE:
					new Sund(msg.cmd);
				 chat = chatWin.get(friendinfo.getQqCode());
					if(chat==null){
						chat = new ChatUI(myInfo,friendinfo);
						chatWin.put(friendinfo.getQqCode(), chat);
					}
					//显示
					chat.show();
					chat.snake();
					break;
				case Cmd.CMD_ADDFRI:
					new Sund(msg.cmd);
					String str = "【"+friendinfo.getNickName()+"】请求添加你为好友，是否同意？";
					SendMSG msg2 = new SendMSG();
					if(JOptionPane.showConfirmDialog(null, str,"添加好友",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                        					
						msg2.cmd=Cmd.CMD_ARGEE;
						accountdao.addFriend(myInfo.getQqCode(), friendinfo.getQqCode());
						refresh();
					}else{
						msg2.cmd=Cmd.CMD_REFUSE;
					}
						msg2.myInfo=myInfo;
						msg2.friendInfo=friendinfo;
						Sendcmd.Send(msg2);
					    break;
				case Cmd.CMD_ARGEE:
						refresh();
					break;
				case Cmd.CMD_REFUSE:
						str = "【"+friendinfo.getNickName()+"】拒绝了你的好友请求。";
						JOptionPane.showMessageDialog(null, str);
					break;
				case Cmd.CMD_FILE:
					new Sund(msg.cmd);
					str = friendinfo.getNickName()+"发送了一个【"+msg.filename+"文件】，是否接收？";
					if(JOptionPane.showConfirmDialog(null, "是否接收文件","接收文件",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
						JFileChooser chooser = new JFileChooser(" ");
			            chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			            chooser.setDialogTitle("保存文件");
			            if (chooser.showOpenDialog(null) == chooser.APPROVE_OPTION) {
			            	String ext = msg.filename.substring(msg.filename.indexOf('.'),msg.filename.length());
			            	String filename = chooser.getSelectedFile().getAbsolutePath()+ext;
			            	FileOutputStream fos = new FileOutputStream(filename);
			            	fos.write(msg.b);
			            	fos.flush();
			            	fos.close();
			            }
					}
				}
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public void mouseDragged(MouseEvent e) {
		int jiex = this.getLocation().x;
		int jiey = this .getLocation().y;
		this.setLocation(jiex+e.getX()-x, jiey+e.getY()-y);
		
	}
	public void mouseMoved(MouseEvent e) {
		
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==cbstatu){
			updatestatu();
			String statu = cbstatu.getSelectedItem().toString();
			accountdao.changeStatus(myInfo.getQqCode(), statu);
			Sendcmd.Sendall(Vmyinfo, myInfo, Cmd.CMD_CHANGESTATE);
		}
	}
	public void updatestatu(){
		String statu = cbstatu.getSelectedItem().toString();
		String filename = myInfo.getHeadimg();
		String headimg = myInfo.getHeadimg();
		if(statu.equals(Cmd.STATUS[0])){
			filename = headimg;
		}else if(statu.equals(Cmd.STATUS[2])){
			int pow = headimg.indexOf('.');
			String fil = headimg.substring(0, pow);
			String fix = headimg.substring(pow,headimg.length());
			filename = fil + "_l"+fix;
		}else if(statu.equals(Cmd.STATUS[3])){
			int pow = headimg.indexOf('.');
			String fil = headimg.substring(0,pow);
			String fix = headimg.substring(pow,headimg.length());
			filename = fil + "_w"+fix;
		}
		lblmyinfo.setIcon(new ImageIcon(filename));
	}
	public void updatestatu2(String statu){
		String filename = myInfo.getHeadimg();
		String headimg = myInfo.getHeadimg();
		if(statu.equals(Cmd.STATUS[0])){
			filename = headimg;
		}else if(statu.equals(Cmd.STATUS[2])){
			int pow = headimg.indexOf('.');
			String fil = headimg.substring(0, pow);
			String fix = headimg.substring(pow,headimg.length());
			filename = fil + "_l"+fix;
		}else if(statu.equals(Cmd.STATUS[3])){
			int pow = headimg.indexOf('.');
			String fil = headimg.substring(0,pow);
			String fix = headimg.substring(pow,headimg.length());
			filename = fil + "_w"+fix;
		}
		lblmyinfo.setIcon(new ImageIcon(filename));
	}	//创建托盘菜单
	public void Text(){
		popTray = new PopupMenu();
		miOpen = new MenuItem("打开");
		miExit = new MenuItem("退出");
		miOnline = new MenuItem("在线");
		miLevel = new MenuItem("隐身");
		miBuys = new MenuItem("忙碌");
		miOpen.addActionListener(this);
		miExit.addActionListener(this);
		miOnline.addActionListener(this);
		miLevel.addActionListener(this);
		miBuys.addActionListener(this);
		
		popTray.add(miOpen);
		popTray.add(miExit);
		popTray.add(miOnline);
		popTray.add(miLevel);
		popTray.add(miBuys);
	
		//获取系统托盘
		SystemTray tray = SystemTray.getSystemTray();
		if(traycon!=null){
			tray.remove(traycon);
		}
		try {
			Image image = new ImageIcon("tubiao1/tubiao.png").getImage();
			String nickname = myInfo.getNickName()+"("+myInfo.getQqCode()+")";
			traycon = new TrayIcon(image,nickname,popTray);
			//系统自动调整图标的大小
			traycon.setImageAutoSize(true);
			tray.add(traycon);
			//隐藏当前窗口
			this.dispose();
			traycon.addMouseListener(  
	                new MouseAdapter(){  
	                    public void mouseClicked(MouseEvent e){  
	                        if(e.getClickCount() == 2)//双击托盘窗口再现  
	                            setExtendedState(Frame.NORMAL);  
	                            setVisible(true);  
	                    }  
	                });   
			refresh();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}
	public void webb(String webSite){
		try {
			// 定义网址为webSite的内容
			uri = new URI(webSite);
			desktop.browse(uri);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}catch (IOException e2) {
				e2.printStackTrace();
			}
	}
}