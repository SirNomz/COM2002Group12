import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Created by User on 14/11/2016.
 */

/*
public int bookVacation(String partner, String date){
                        String updateAppointments = "UPDATE Appointment SET State = 'Canceled' WHERE (State = 'Active' "
                            + "And ADate = '" + date + "' AND Partner = '"+ partner +"')";
                        reg.updateData(updateAppointments);
                        String bookVac = "INSERT INTO Appointment VALUES( 0, 'CheckUp', '" + partner + "', '" +
                                date + "', '09:00:00', '18:00:00', 'Vacation', 0)";
                        return reg.updateData(bookVac);
                    }

 */

public class DentistGUI extends JFrame {

    public void DentistGUI(){
        setTitle("Sheffield Dental Practice");
        setSize(500,600);
        JButton btnChk
                = new JButton("Finish Appointment");
        btnChk.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new DentistGUI().FinishAppointmment();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        JButton btnView = new JButton("View Appointments");
        btnView.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new ViewAppointment().startDate("Dentist");
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new WelcomeGUI().WelcomeGUI();
                    }
                }
        );


        //create a buttonPanel and add all buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnChk);
        buttonPanel.add(btnView);

        JLabel title = new JLabel("Dentist Page");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set frame visibility to true.
        setVisible(true);
    }

    public void FinishAppointmment()throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        final DataAccessBase reg = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JLabel title = new JLabel("Enter Appointment Time To Log Visit:");
        JLabel type = new JLabel("Treatment type:");
        String[] tType = {"Checkup","","","",""};
        final JComboBox Type = new JComboBox(tType);
        JLabel sTime = new JLabel("Start Time:");
        final String[] hour = {"Hour","09","10","11","12","14","15","16","17"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);
        JButton bSubmit = new JButton("Submit");

        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        JPanel mPanel = new JPanel();
        mPanel.add(timePanel);
        mPanel.add(bSubmit);

        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        mPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        final String today = dtf.format(localDate);

        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //this checks if an appointment exists
                        boolean appointmentExists = true;
                        String visitType= "";
                        String getVT = "SELECT TypeOfVisit FROM Appointment Where (State = 'Active' "
                                + "And ADate = '" + today + "' AND StartTime = '" + hr.getSelectedItem().toString()
                                +":"+ min.getSelectedItem().toString()+":00' AND Partner = 'Dentist')";
                        ResultSet rVT = reg.getData(getVT);
                        try {
                            while(rVT.next()){
                                if(!rVT.wasNull()) {
                                    visitType = rVT.getString("TypeOfVisit");
                                }
                                else
                                    appointmentExists = false;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        //if it does then it wil first check in what category it is
                        if(appointmentExists) {
                            int cost = 0;
                            String getCost = "SELECT Cost FROM VisitType Where TypeOfVisit = '" + visitType + "'";
                            ResultSet rCost = reg.getData(getCost);
                            try {
                                while (rCost.next()) {
                                    cost = rCost.getInt("Cost");
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                            //update the appointment to waiting and assign a cost to it
                            String updateAppointment = "UPDATE Appointment SET State = 'Waiting',Cost = " + cost + " WHERE (State = 'Active' "
                                    + "And ADate = '" + today + "' AND StartTime = '" + hr.getSelectedItem().toString()
                                    + ":" + min.getSelectedItem().toString() + ":00' AND Partner = 'Dentist' )";
                            reg.updateData(updateAppointment);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "This Appointment time is unavailable. Please Select another.");

                        }
                        dispose();
                        new DentistGUI().DentistGUI();
                    }
                }
        );

        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new DentistGUI().DentistGUI();
                    }
                }
        );

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentPane = getContentPane();
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(mPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);


        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
