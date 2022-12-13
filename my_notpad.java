import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
public class my_notpad implements ActionListener{

    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;
    JMenuItem CloseEditor;

    my_notpad()
    {
        frame=new JFrame("Simple Text Editor");
        frame.setBounds(0,0,1000,1000);

        jTextArea=new JTextArea("Welcome To The Text Editor");
        frame.add(jTextArea);
        jMenuBar=new JMenuBar();

        File=new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");

        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

        OpenFile=new JMenuItem("Open");
        OpenFile.addActionListener(this);
        SaveFile=new JMenuItem("Save");
        SaveFile.addActionListener(this);
        NewFile=new JMenuItem("New");
        NewFile.addActionListener(this);
        PrintFile=new JMenuItem("Print");
        PrintFile.addActionListener(this);



        File.add(OpenFile);
        File.add(SaveFile);
        File.add(NewFile);
        File.add(PrintFile);

        Cut =new JMenuItem("Cut");
        Cut.addActionListener(this);
        Copy=new JMenuItem("Copy");
        Copy.addActionListener(this);
        Paste=new JMenuItem("Paste");
        Paste.addActionListener(this);

        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        CloseEditor=new JMenuItem("Close Editor");
        CloseEditor.addActionListener(this);
        Close.add(CloseEditor);

        frame.setJMenuBar(jMenuBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String args[]){

        my_notpad editor=new my_notpad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy"))
        {
            jTextArea.copy();
        }else if(s.equals("Cut"))
        {
            jTextArea.cut();
        }else if(s.equals("Paste"))
        {
            jTextArea.paste();
        }else if(s.equals("Print"))
        {
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }else if(s.equals("New"))
        {
            jTextArea.setText("");
        }else if(s.equals("Close Editor"))
        {
            System.exit(1);
        }else if(s.equals("Open"))
        {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1="",s2="";
                try {
                     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null)
                    {
                        s2=s1+"\n";
                    }
                    jTextArea.setText(s2);
                }catch (FileNotFoundException ex) {
                    throw new RuntimeException((ex));
                }catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(s.equals("Save"))
        {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showSaveDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file=new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try {
                    writer=new BufferedWriter(new FileWriter(file,false));
                    writer.write(jTextArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
