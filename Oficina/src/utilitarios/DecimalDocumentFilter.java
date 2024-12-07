/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitarios;
import java.text.DecimalFormat;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
/**
 *
 * @author filip
 */
public class DecimalDocumentFilter extends DocumentFilter {
    private DecimalFormat format = new DecimalFormat("#,##0.00");
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String current = fb.getDocument().getText(0, fb.getDocument().getLength());
        String before = current.substring(0, offset);
        String after = current.substring(offset + length);
        String newText = before + text + after;
        
        try {
            if (!newText.isEmpty()) {
                newText = newText.replace(",", ".");
                Double.parseDouble(newText);
            }
            super.replace(fb, offset, length, text, attrs);
        } catch (NumberFormatException e) {
            // Only allow valid decimal numbers
        }
    }
}

