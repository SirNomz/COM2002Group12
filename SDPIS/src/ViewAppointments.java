import javafx.util.converter.LocalDateStringConverter;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by jodi on 16/11/2016.
 */
public class ViewAppointments extends JFrame {

    DataAccessBase view = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");

    public ViewAppointments() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public void ViewDentistAppointments() {
        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300, 600);

        //get content pane
        Container container = getContentPane();

        //get current date and store it as a string
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);

        //Title of panel
        JLabel title = new JLabel("Dentist Appointments: " + date);
        //create string to represent sql query
        String sql = "SELECT ID,TypeOfVisit,StartTime FROM Appointment WHERE ADate = '" + date + "' AND Partner = 'Dentist' AND State = 'Active'";
        //store result set of executing Query
        ResultSet rs = view.getData(sql);

        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);

        //create a text area to house appointment details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        try {
            //loop through result set appending each appointment to the textArea
            if (!rs.next()) {
                textArea.append("No Appointments today");
            } else {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String type = rs.getString("TypeOfVisit");
                    Time startTime = rs.getTime("StartTime");
                    String appoint = "Start Time: " + startTime + " Customer ID: " + id + " Visit Type: " + type;
                    System.out.println(" " + appoint);
                    textArea.append(appoint + newLine);
                }
            }
            //add textArea to the panel
            mPanel.add(textArea, BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //create go back button
        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new DentistGUI().DentistGUI();
                    }
                }
        );

        int bHeight = (int) (this.getHeight() * 0.05);
        int bWidth = (int) (this.getWidth() * 0.05);
        //add back button to panel
        mPanel.add(btnBack, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));
        container.add(mPanel);

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void ViewHygienistAppointments() {
        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300, 600);

        //get content pane
        Container container = getContentPane();

        //get current date and store it as a string
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);

        //Title of panel
        JLabel title = new JLabel("Hygienist Appointments: " + localDate);
        //create string to represent sql query
        String sql = "SELECT ID,TypeOfVisit,StartTime FROM Appointment WHERE ADate = '" + date + "' AND Partner = 'Hygienist' AND State = 'Active'";
        //store result set of executing Query
        ResultSet rs = view.getData(sql);

        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();
        mPanel.setLayout(new BorderLayout());
        mPanel.add(title, BorderLayout.NORTH);

        //create a text area to house appointment details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        //loop through result set appending each appointment to the textArea
        try {
            if (!rs.next()) {
                textArea.append("No Appointments today");
            } else {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String type = rs.getString("TypeOfVisit");
                    Time startTime = rs.getTime("StartTime");
                    String appoint = "Start Time: " + startTime + " Customer ID: " + id + " Visit Type: " + type;
                    System.out.println(" " + appoint);
                    textArea.append(appoint + newLine);
                }
            }
            //add textArea to the panel
            mPanel.add(textArea, BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //create go back button
        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new HygienistGUI().HygienistGUI();
                    }
                }
        );

        int bHeight = (int) (this.getHeight() * 0.05);
        int bWidth = (int) (this.getWidth() * 0.05);
        //add back button to panel
        mPanel.add(btnBack, BorderLayout.SOUTH);
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));
        container.add(mPanel);

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startDate() {
        JTabbedPane tabbedPane = new JTabbedPane();

        //Set title and size of frame
        setTitle("Sheffield Dental Practice");
        setSize(1300, 600);


        //Title of panel
        JLabel title = new JLabel("Select Calendar Start Date");
        //current date
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);

        //combobox for date
        final JComboBox days = new JComboBox();
        final JComboBox months = new JComboBox();
        final JComboBox years = new JComboBox();
        JButton bSubmit = new JButton("Submit");

        //Use for loops to create the date options
        days.addItem("Day");
        for (int i = 1; i <= 31; i++) {
            days.addItem(new Integer(i));
        }

        months.addItem("Month");
        for (int i = 1; i <= 12; i++) {
            months.addItem(new Integer(i));
        }

        years.addItem("Year");
        for (int i = year; i <= year + 1; i++) {
            years.addItem(new Integer(i));
        }
        //panel to store dates and submit button.
        JPanel dpanel = new JPanel();
        dpanel.add(days);
        dpanel.add(months);
        dpanel.add(years);
        dpanel.add(bSubmit);

        //create a panel set its layout, add a title
        JPanel vaPanel = new JPanel();
        vaPanel.setLayout(new BorderLayout());
        vaPanel.add(title, BorderLayout.NORTH);
        vaPanel.add(dpanel, BorderLayout.CENTER);


        bSubmit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        try {
                            String startDate = years.getSelectedItem() + "-" + months.getSelectedItem() + "-" + days.getSelectedItem();
                            new ViewAppointments().ViewSecretaryAppointments(startDate);
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
        //create go back button
        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new HygienistGUI().HygienistGUI();
                    }
                }
        );

        int bHeight = (int) (this.getHeight() * 0.05);
        int bWidth = (int) (this.getWidth() * 0.05);
        //add back button to panel
        vaPanel.add(btnBack, BorderLayout.SOUTH);
        vaPanel.setBorder(BorderFactory.createEmptyBorder(bHeight, bWidth, bHeight, bWidth));

        tabbedPane.addTab("Partner Appointments", null, vaPanel, null);

        JLabel pID = new JLabel("PatientID:");
        final JTextField txtPID = new JTextField(20);

        JLabel partner = new JLabel("Partner:");
        String[] partners = {"Dentist","Hygienist"};
        final JComboBox Partner = new JComboBox(partners);

        JPanel pPanel = new JPanel();
        pPanel.add(pID);
        pPanel.add(txtPID);

        JPanel partPanel = new JPanel();
        partPanel.add(partner);
        partPanel.add(Partner);

        JLabel sTime = new JLabel("Appointment Start Time:");
        final String[] hour = {"Hour","09","10","11","12","14","15","16"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);

        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        //comboboxes date of appointment
        JLabel date2 = new JLabel("Date of Appointment:");
        final JComboBox days2 = new JComboBox();
        final JComboBox months2 = new JComboBox();
        final JComboBox years2 = new JComboBox();

        //Use for loops to create the date options
        days2.addItem("Day");
        for(int i=1;i<=31;i++) {
            days2.addItem(new Integer(i));
        }

        months2.addItem("Month");
        for(int i=1;i<=12;i++) {
            months2.addItem(new Integer(i));
        }

        years2.addItem("Year");
        for(int i = (int)(Calendar.getInstance().get(Calendar.YEAR)); i<=
                (int)(Calendar.getInstance().get(Calendar.YEAR))+1; i++) {
            years2.addItem(new Integer(i));
        }

        //create a panel and add date comboBox
        JPanel dPanel2 = new JPanel();
        dPanel2.add(date2);
        dPanel2.add(days2);
        dPanel2.add(months2);
        dPanel2.add(years2);

        JButton btnBack2 = new JButton("Go Back");
        btnBack2.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        JPanel subPanel = new JPanel();
        subPanel.add(pPanel);
        subPanel.add(partPanel);
        subPanel.add(timePanel);
        subPanel.add(dPanel2);

        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));

        //create a panel for the appointment search
        JPanel sePanel = new JPanel();
        sePanel.setLayout(new BorderLayout());
        sePanel.add(title, BorderLayout.NORTH);
        sePanel.add(subPanel, BorderLayout.CENTER);
        sePanel.add(btnBack2, BorderLayout.SOUTH);



        int bHeight2 = (int) (this.getHeight() * 0.05);
        int bWidth2 = (int) (this.getWidth() * 0.05);
        sePanel.add(btnBack2, BorderLayout.SOUTH);
        sePanel.setBorder(BorderFactory.createEmptyBorder(bHeight2, bWidth2, bHeight2, bWidth2));

        tabbedPane.addTab("Search Appointments", null, sePanel, null);



        //get content pane
        Container container = getContentPane();
        container.add(tabbedPane);

        //Don't forget to pack! and setVisible to true
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void ViewSecretaryAppointments(String startDate) {
        JTabbedPane tabbedPane = new JTabbedPane();

        //start date of calendar
        String startDay = startDate;
        LocalDate appointDate = LocalDate.parse(startDay);
        String endDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(appointDate.plusWeeks(1));

        JLabel denTitle = new JLabel("Dentist Weekly Calendar from: " + startDay);
        JLabel hygTitle = new JLabel("Hygienist Weekly Calendar from: " + startDay);

        //dentist appointments
        //create string to represent sql query
        String sql = "SELECT ID,TypeOfVisit,ADate,StartTime FROM Appointment WHERE ADate BETWEEN '" + startDay + "' AND '" +
                endDate + "' AND Partner = 'Dentist' AND State = 'Active'";
        //store result set of executing Query
        ResultSet rs = view.getData(sql);

        //create a panel set its layout, add a title
        JPanel mPanel = new JPanel();

        //create a text area to house appointment details
        JTextArea textArea = new JTextArea();
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setEditable(false);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 300));
        String newLine = "\n";

        try {
            //loop through result set appending each appointment to the textArea
            if (!rs.next()) {
                textArea.append("No Appointments today");
            } else {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String type = rs.getString("TypeOfVisit");
                    Date appDay = rs.getDate("ADate");
                    Time startTime = rs.getTime("StartTime");
                    String appoint = "Date: "+appDay+" Start Time: " + startTime + " Customer ID: " + id +
                            " Visit Type: " + type;
                    System.out.println(" " + appoint);
                    textArea.append(appoint + newLine);
                }
            }
            //add textArea to the panel
            mPanel.add(textArea, BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton btnBackDen = new JButton("Go Back");
        btnBackDen.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        //Hygienist calendar
        //dentist appointments
        //create string to represent sql query
        sql = "SELECT ID,TypeOfVisit,ADate,StartTime FROM Appointment WHERE ADate BETWEEN '" + startDay + "' AND '" +
                endDate + "' AND Partner = 'Hygienist' AND State = 'Active'";
        //store result set of executing Query
        rs = view.getData(sql);

        //create a panel set its layout, add a title
        JPanel hPanel = new JPanel();

        //create a text area to house appointment details
        JTextArea textArea1 = new JTextArea();
        textArea1.setMargin(new Insets(10, 10, 10, 10));
        textArea1.setEditable(false);
        JScrollPane areaScrollPane1 = new JScrollPane(textArea1);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane1.setPreferredSize(new Dimension(300, 300));


        try {
            //loop through result set appending each appointment to the textArea
            if (!rs.next()) {
                textArea1.append("No Appointments today");
            } else {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String type = rs.getString("TypeOfVisit");
                    Date appDay = rs.getDate("ADate");
                    Time startTime = rs.getTime("StartTime");
                    String appoint = "Date: "+appDay+" Start Time: " + startTime + " Customer ID: " + id +
                            " Visit Type: " + type;
                    System.out.println(" " + appoint);
                    textArea1.append(appoint + newLine);
                }
            }
            //add textArea to the panel
            hPanel.add(textArea1, BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //go back button
        JButton btnBackHyg = new JButton("Go Back");
        btnBackHyg.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new SecretaryGUI().SecretaryGUI();
                    }
                }
        );

        //add details to the dentist panel
        JPanel dentist = new JPanel();
        dentist.add(denTitle, BorderLayout.NORTH);
        dentist.add(textArea, BorderLayout.CENTER);
        dentist.add(btnBackDen, BorderLayout.SOUTH);

        tabbedPane.addTab("Dentist Appointments", null, dentist, null);

        //add details to the hygienist panel
        JPanel hygienist = new JPanel();
        hygienist.add(hygTitle, BorderLayout.NORTH);
        hygienist.add(textArea1, BorderLayout.CENTER);
        hygienist.add(btnBackHyg, BorderLayout.SOUTH);

        tabbedPane.addTab("Hygienist Appointments", null, hygienist, null);

        JFrame frame = new JFrame("View Appointments");

        //Add content to the window.
        add(tabbedPane, BorderLayout.CENTER);

        //Display the window.
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}