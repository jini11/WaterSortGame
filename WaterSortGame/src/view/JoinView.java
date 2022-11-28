package view;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import utils.ImageUtil;

public class JoinView extends JFrame {
	private final JLabel joinLabel = new JLabel("회원가입");
    private final JLabel id = new JLabel("아이디");     
    private final JTextField inputId = new JTextField(20);
    private final JLabel password = new JLabel("비밀번호");
    private final JPasswordField inputPassword2 = new JPasswordField(20);
    private final JLabel checkPassword = new JLabel("비밀번호 확인");
    private final JPasswordField inputPassword = new JPasswordField(20);
    private JButton duplicateBtn;
    private JButton createBtn;
    private JButton backBtn;
    private ImageUtil util = new ImageUtil();
    boolean isDuplicateId = true;
    boolean overlapCheck = false;        //아이디 중복되었는지 체크
    
    public void printLayout() {
    	this.setTitle("Join");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(600, 530);
        setLocationRelativeTo(null);
        
        duplicateBtn = util.makeUI("image/Check.png", 100);
        createBtn = util.makeUI("image/Create.png", 100);
        backBtn = util.makeUI("image/Back.png", 100);
        
        joinLabel.setFont(new Font("Gothic", Font.BOLD, 50));
        joinLabel.setBounds(200, 80, 300, 50);
        id.setBounds(80, 200, 70, 40);
        inputId.setBounds(200, 200, 200, 40);
        duplicateBtn.setBounds(420, 195, 100, 50);  
        password.setBounds(80, 270, 70, 40);
        inputPassword.setBounds(200, 270, 200, 40);
        checkPassword.setBounds(80, 340, 100, 40);
        inputPassword2.setBounds(200, 340, 200, 40);
        createBtn.setBounds(190, 410, 100, 50);
        backBtn.setBounds(310, 410, 100, 50);
        
        add(joinLabel);
        add(id);           
        add(inputId);
        add(duplicateBtn);
        add(password);
        add(inputPassword);
        add(checkPassword);
        add(inputPassword2);   
        add(createBtn);        
        add(backBtn);
    }
    
    public JButton getDuplicate() {
    	return duplicateBtn;
    }
    
    public JButton getCreate() {
    	return createBtn;
    }
    
    public JButton getBack() {
    	return backBtn;
    }
    
    public void setView() {
    	setVisible(false);
    }
    
    public String getId() {
    	return inputId.getText();
    }
    
    public void setDuplicate() {
    	duplicateBtn.setEnabled(false);
    	inputId.setEditable(false);
    }
    
    public List<String> joinInfo() {
    	return List.of(inputId.getText(), new String(inputPassword.getPassword()), new String(inputPassword2.getPassword()));
    }
}
