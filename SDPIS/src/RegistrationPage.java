import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by User on 14/11/2016.
 */
public class RegistrationPage extends JFrame {

    public void RegisterPage(){
        //Set title and size of registration frame
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //Create registration form
        //Create titled text fields
        JLabel patientTitle = new JLabel("Title:");
        final JTextField txtTitle = new JTextField(20);
        JLabel fName = new JLabel("Forename:");
        final JTextField txtFName = new JTextField(20);
        JLabel lName = new JLabel("Surname:");
        final JTextField txtLName = new JTextField(20);

        //comboboxes date of birth
        JLabel dob = new JLabel("Date of Birth:");
        final JComboBox days = new JComboBox();
        final JComboBox months = new JComboBox();
        final JComboBox years = new JComboBox();

        //Use for loops to create the date options
        days.addItem("Day");
        for(int i=1;i<=31;i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for(int i=1;i<=12;i++) {
            months.addItem(new Integer(i));
        }

        years.addItem("Year");
        for(int i=1900;i<=2016;i++) {
            years.addItem(new Integer(i));
        }

        //Titled text field for phone number
        JLabel phone = new JLabel("Phone Number:");
        final JTextField txtPhone = new JTextField(20);

        //combobox subscription types
        JLabel sub = new JLabel("Subscription:");
        String[] subTypes = { "None", "NHS", "Maintenance", "Oral", "Repair"};
        final JComboBox subList = new JComboBox(subTypes);

        //create text fields to collect address information
        JLabel houseNum = new JLabel("House Number:");
        final JTextField txtHousenum = new JTextField(20);
        JLabel street = new JLabel("Street Name:");
        final JTextField txtStreet = new JTextField(20);
        JLabel city = new JLabel("City:");
        final JTextField txtAddressCity = new JTextField(20);
        JLabel region = new JLabel("Region:");
        final JTextField txtAddressRegion = new JTextField(20);
        JLabel postcode = new JLabel("Postcode:");
        final JTextField txtPostCode = new JTextField(20);

        //create submit button
        JLabel submit = new JLabel("Submit:");
        JButton bSubmit = new JButton("Submit");

        //create a panel and add title and name input fields.
        JPanel inputsPanel = new JPanel();
        inputsPanel.add(patientTitle);
        inputsPanel.add(txtTitle);
        inputsPanel.add(fName);
        inputsPanel.add(txtFName);
        inputsPanel.add(lName);
        inputsPanel.add(txtLName);
        inputsPanel.setLayout(new BoxLayout(inputsPanel, BoxLayout.Y_AXIS));

        //create panel and add registration form input fields.
        JPanel inputsPanel2 = new JPanel();
        inputsPanel2.add(phone);
        inputsPanel2.add(txtPhone);
        inputsPanel2.add(sub);
        inputsPanel2.add(subList);
        inputsPanel2.add(houseNum);
        inputsPanel2.add(txtHousenum);
        inputsPanel2.add(street);
        inputsPanel2.add(txtStreet);
        inputsPanel2.add(city);
        inputsPanel2.add(txtAddressCity);
        inputsPanel2.add(region);
        inputsPanel2.add(txtAddressRegion);
        inputsPanel2.add(postcode);
        inputsPanel2.add(txtPostCode);
        inputsPanel2.add(submit);
        inputsPanel2.add(bSubmit);
        inputsPanel2.setLayout(new BoxLayout(inputsPanel2, BoxLayout.Y_AXIS));

        //create a panel and add D.O.B comboBox
        JPanel dobPanel = new JPanel();
        dobPanel.add(dob);
        dobPanel.add(days);
        dobPanel.add(months);
        dobPanel.add(years);

        JLabel title = new JLabel("Please enter your details:");

        //add action listener to submit button
        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String[] newPatient = {txtTitle.getText(), txtFName.getText(), txtLName.getText(),
                                years.getSelectedItem()+"-"+months.getSelectedItem()+"-"+days.getSelectedItem(), txtPhone.getText(), (String)subList.getSelectedItem(),
                                txtHousenum.getText(), txtStreet.getText(), txtAddressCity.getText(), txtAddressRegion.getText(), txtPostCode.getText()};

                        System.out.print(Arrays.toString(newPatient));

                    }
                }
        );













        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        //create ints to represent borders for contents of the panel
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);



        //create a new panel to contain the title and the input field panels
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

        //add the mpanel to the contentPane
        Container contentPane = getContentPane();
        contentPane.add(mPanel);
        contentPane.add(btnBack, BorderLayout.SOUTH);



        //Don't forget to pack! and setVisible to true.
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
