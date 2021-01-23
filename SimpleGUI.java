import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//import jdk.internal.platform.Container;
@SuppressWarnings("serial")
public class SimpleGUI extends JFrame {
    private JButton buttom = new JButton("Press");
    private JTextField input = new JTextField("",5);
    private  JLabel label = new JLabel("input");
    private JRadioButton radio1 = new JRadioButton("select this");
    private JRadioButton radio2 = new JRadioButton("select that");
    private JCheckBox check = new JCheckBox("Check",false);
    public SimpleGUI (){
        super("Simple Example");
        this.setBounds(100,100,250,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        container.add(label);
        container.add(input);
        
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        container.add(radio1);
        radio1.setSelected(true);
        container.add(radio2);
        container.add(check);

        buttom.addActionListener(new ButtonEventListener());
        container.add(buttom);
    }
    class ButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String mess= "";
            mess += "Button was pressed\n";
            mess += "text is " + input.getText()+ "\n";
            mess += (radio1.isSelected() ? "radio 1 " : "radio 2") + "is selected";
            mess += "Checkbox is " + (check.isSelected()? "checked" : "unchecked");
            JOptionPane.showMessageDialog(null,mess,"output", JOptionPane.PLAIN_MESSAGE);

        }
    }
}
