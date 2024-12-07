/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitarios;

import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author filip
 */
public class TimeSpinner extends JSpinner {
   public TimeSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.MINUTE);
        setModel(model);
        setEditor(new JSpinner.DateEditor(this, "HH:mm"));
    }
}
