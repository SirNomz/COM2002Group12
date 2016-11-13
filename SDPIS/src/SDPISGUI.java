import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

/**
 * Created by Josh on 08/11/2016.
 */
public class SDPISGUI extends JFrame {



    public void WelcomeGUI() {
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JButton btnSec = new JButton("Secretary");
        btnSec.addActionListener(
             new ActionListener(){
                 public void actionPerformed(ActionEvent e){
                     new SDPISGUI().RegisterPage();
                     setVisible(false);
                 }
             }
        );

        JButton btnDen = new JButton("Dentist");
        btnDen.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Go to Dentist view
                }
            }
        );

        JButton btnHyg = new JButton("Hygenist");
        btnHyg.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Go to Hygenist view
                }
            }
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnSec);
        buttonPanel.add(btnDen);
        buttonPanel.add(btnHyg);

        JLabel title = new JLabel("Welcome to Sheffield Dental Practice");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);
        mPanel.add(buttonPanel, BorderLayout.CENTER);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        Container contentPane = getContentPane();
        contentPane.add(mPanel);

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void SecretaryGUI(){

    }

    public void RegisterPage(){
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JTextField txtTitle = new JTextField(20);
        JTextField txtFName = new JTextField(20);
        JTextField txtLName = new JTextField(20);

        //comboboxes date of birth
        JComboBox days = new JComboBox();
        JComboBox months = new JComboBox();
        JComboBox years = new JComboBox();

        days.addItem("Day");
        for(int i=1;i<=31;i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for(int i=1;i<=12;i++) {
            months.addItem(new Integer(i));
        }

        years.addItem("Year");
        for(int i=1900;i<=2100;i++) {
            years.addItem(new Integer(i));
        }

        JTextField txtPhone = new JTextField(20);

        //combobox subscription types
        String[] subTypes = { "None", "NHS", "Maintenance", "Oral", "Repair"};
        JComboBox subList = new JComboBox(subTypes);

        JTextField txtHousenum = new JTextField(20);
        JTextField txtStreet = new JTextField(20);
        JTextField txtAddressCity = new JTextField(20);
        JTextField txtAddressRegion = new JTextField(20);
        JTextField txtPostCode = new JTextField(20);
        JButton submit = new JButton("Submit");

        JPanel inputsPanel = new JPanel();
        inputsPanel.add(txtTitle);
        inputsPanel.add(txtFName);
        inputsPanel.add(txtLName);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));

        JPanel inputsPanel2 = new JPanel();
        inputsPanel2.add(txtPhone);
        inputsPanel2.add(subList);
        inputsPanel2.add(txtHousenum);
        inputsPanel2.add(txtStreet);
        inputsPanel2.add(txtAddressCity);
        inputsPanel2.add(txtAddressRegion);
        inputsPanel2.add(txtPostCode);
        inputsPanel2.add(submit);
        inputsPanel2.setLayout(new BoxLayout(inputsPanel2, BoxLayout.Y_AXIS));

        JPanel dobPanel = new JPanel();
        dobPanel.add(days);
        dobPanel.add(months);
        dobPanel.add(years);



        JLabel title = new JLabel("Please enter your details:");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title,BorderLayout.NORTH);

        JPanel cPanel = new JPanel();
        cPanel.add(inputsPanel);
        cPanel.add(dobPanel);
        cPanel.add(inputsPanel2);
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        mPanel.add(cPanel,BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        Container contentPane = getContentPane();
        contentPane.add(mPanel);



        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
